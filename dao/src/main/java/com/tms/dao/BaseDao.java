package com.tms.dao;

import com.tms.commons.FutureTask;
import com.tms.commons.*;
import com.tms.config.DBConfig;
import com.tms.config.DBConfigMap;
import com.tms.exception.ConfigNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.function.Supplier;

@Service
public abstract class BaseDao {
    private final ExecutorService executor = Executors.newCachedThreadPool();
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DBConfigMap dbConfigMap;

    @Autowired
    private BasicDao basicDao;

    public <P, R> DBResponse<R> dbGet(String sessionId, String funcName, P params, Class<?> resultClass) {
        try {
            final DBConfig config = getConfig(funcName);
            FutureTask<DBResponse<R>> memTask = (MemFutureTask<DBResponse<R>>) () -> null;
            FutureTask<DBResponse<R>> dbTask =
                    (DBFutureTask<DBResponse<R>>) () -> basicDao.dbGet(config, params, resultClass);
            return query(sessionId, config, memTask, dbTask);
        } catch (ConfigNotFoundException e) {
            return DBResponse.qConfigError(sessionId, e.getMessage());
        }
    }

    protected DBConfig getConfig(String fucName) throws ConfigNotFoundException {
        DBConfig lookup = dbConfigMap.lookup(fucName);
        if (lookup == null) {
            throw new ConfigNotFoundException(fucName, "Can not get config for method: " + fucName);
        }
        return lookup;
    }

    protected <T> DBResponse<T> query(String sessionId, DBConfig config, FutureTask<DBResponse<T>> memTask, FutureTask<DBResponse<T>> dbTask) {
        if (config.isDBQuery() || !config.isFallBack()) {
            // Execute 1 task with no fallback
            return executeTask(sessionId, config, dbTask, null);

        } else {
            // Execute fallback in case can not get from first task
            return executeTask(sessionId, config, memTask, dbTask);
        }
    }

    private <T> DBResponse<T> executeTask(String sessionId, DBConfig cf, FutureTask<DBResponse<T>> firstTask, FutureTask<DBResponse<T>> fallbackTask) {
        long firstTime = cf.getMemTimeout();
        long lastTime = cf.getDBTimeout();
        long totalTime = firstTime + lastTime;
        long currTime = System.currentTimeMillis();
        // Execute first task
        CompletableFuture<DBResponse<T>> firstFutureTask = CompletableFuture.supplyAsync(firstTask, executor);
        DBResponse<T> result = waitAndGetResult(sessionId, firstFutureTask, firstTask.type(), firstTime);

        // Execute fallback task if first task is null and fallback exist
        if (result == null && fallbackTask != null) {
            long remainTimeout = totalTime - (System.currentTimeMillis() - currTime);
            CompletableFuture<DBResponse<T>> secondFutureTask = CompletableFuture.supplyAsync(fallbackTask, executor);
            result = waitAndGetResult(sessionId, secondFutureTask, fallbackTask.type(), remainTimeout);
        }

        return result;
    }

    private <T> DBResponse<T> waitAndGetResult(String sessionId, CompletableFuture<DBResponse<T>> task, String type, long timeout) {
        DBResponse<T> result = null;
        try {
            result = task.get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            task.cancel(true);
            logger.error("{}: {} task is timeout", sessionId, type, e);
        } catch (Exception e) {
            logger.error("{}: {} task has exception ", sessionId, type, e);
            Thread.currentThread().interrupt();
        }
        return result;
    }

    public <T> DBResponse<String> dbInsOrUpd(String sessionId, String funcName, T params) {
        try {
            final DBConfig config = getConfig(funcName);
            FutureTask<DBResponse<String>> fileTask =
                    (FileFutureTask<DBResponse<String>>) () -> basicDao.insOrUpdFile(config, params);
            FutureTask<DBResponse<String>> dbTask =
                    (DBFutureTask<DBResponse<String>>) () -> basicDao.insOrUpdDb(config, params);
            return insert(sessionId, config, fileTask, dbTask);
        } catch (ConfigNotFoundException e) {
            return DBResponse.qConfigError(sessionId, e.getMessage());
        }
    }

    protected <T> DBResponse<T> insert(String sessionId, DBConfig config,
                                       Supplier<DBResponse<T>> fileTask, Supplier<DBResponse<T>> dbTask) {
        try {
            logger.info("{}: Call insert to database", sessionId);
            DBResponse<T> result = dbTask.get();
            if (Boolean.TRUE.equals(config.getAlwaysWriteFile())) {
                tryWriteFile(sessionId, fileTask);
            }
            return result;
        } catch (Exception e) {
            logger.error(sessionId, "Fail write on database -> Fallback", e);
            return fallbackInsert(sessionId, dbTask);
        }
    }

    private <T> void tryWriteFile(String sessionId, Supplier<DBResponse<T>> fileTask) {
        try {
            logger.info("{}: Row is updated on db, so we write to file", sessionId);
            DBResponse<T> fileResult = fileTask.get();
            if (fileResult.getErrorCode() > 0) {
                logger.error("{}: Fail write on file: {}", sessionId, fileResult.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error(sessionId, "Fail write on file", e);
        }
    }

    protected <T> DBResponse<T> fallbackInsert(String sessionId, Supplier<DBResponse<T>> dbTask) {
        try {
            return dbTask.get();
        } catch (Exception e) {
            logger.error(sessionId, "Fail write on database", e);
            return new DBResponse<>(1, e.getMessage());
        }
    }
}