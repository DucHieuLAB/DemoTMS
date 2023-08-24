package com.tms.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties
public class DataSourceConfig {

    public static final String DB_JDBC_TEMPLATE = "firstJdbcTemplate";
    public static final String DB_TRANSACTION_MANAGER = "firstTransactionManager";

    public static final String LOG_DB_JDBC_TEMPLATE = "logJdbcTemplate";
    public static final String LOG_DB_TRANSACTION_MANAGER = "logFirstTransactionManager";

    public static final String MM_JDBC_TEMPLATE = "memJdbcTemplate";
    public static final String MM_TRANSACTION_MANAGER = "secondTransactionManager";

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "app.datasource.hikari-config")
    public HikariDataSource hikariDataSource() {
        return firstDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public JdbcTemplate firstJdbcTemplate(DataSource hikariDataSource) {
        return new JdbcTemplate(hikariDataSource);
    }


    @Bean(name = "memDataSource")
    @ConfigurationProperties(prefix = "inmemory.datasource")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = MM_JDBC_TEMPLATE)
    @Autowired
    public JdbcTemplate secondJdbcTemplate(@Qualifier("memDataSource") DataSource secondDataSource) {
        return new JdbcTemplate(secondDataSource);
    }
}
