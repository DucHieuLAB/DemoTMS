package com.tms.api.scheduled;

import com.google.gson.*;
import lombok.Getter;
import lombok.Setter;
import com.tms.dto.response.ClBasket;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Getter
@Setter
public class DuplicateLeadChecker {
    private static final String FILE_NAME = "cache.json";
    private static final String PATH = "src/main/java/com/tms/api/scheduled/data/";
    private static Map<String, Set<String>> records = new HashMap<>();

    public static void addRecord(ClBasket basket) {
        if (!records.containsKey(basket.getPhone())) {
            records.put(basket.getPhone(), new HashSet<>());
        }
        if (!records.get(basket.getPhone()).contains(basket.getProdName())) {
            records.get(basket.getPhone()).add(basket.getProdName());
        }
    }

    public static void addRecord(List<ClBasket> datas) {
        for (ClBasket data : datas) {
            addRecord(data);
        }
        saveCacheToFile();
    }

    public static boolean isEmpty() {
        return records.size() == 0 ? true : false;
    }

    public static boolean isDuplicate(String phone, String productName) {
        return records.containsKey(phone) && records.get(phone).contains(productName);
    }

    public static void saveCacheToFile() {
        File file = new File(PATH + FILE_NAME);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonCache = new JsonObject();
        for (Map.Entry<String, Set<String>> entry : records.entrySet()) {
            String phone = entry.getKey();
            Set<String> productNames = entry.getValue();
            JsonArray productNameArray = new JsonArray();
            for (String productName : productNames) {
                productNameArray.add(productName);
            }
            jsonCache.add(phone, productNameArray);
        }

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(jsonCache, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadCacheFromFile() {
        Gson gson = new Gson();
        File file = new File(PATH + FILE_NAME);
        try (FileReader reader = new FileReader(file)) {
            JsonObject jsonCache = gson.fromJson(reader, JsonObject.class);

            for (Map.Entry<String, JsonElement> entry : jsonCache.entrySet()) {
                String phone = entry.getKey();
                JsonArray productNameArray = entry.getValue().getAsJsonArray();

                for (JsonElement element : productNameArray) {
                    String productName = element.getAsString();

                    if (!records.containsKey(phone)) {
                        records.put(phone, new HashSet<>());
                    }

                    records.get(phone).add(productName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
