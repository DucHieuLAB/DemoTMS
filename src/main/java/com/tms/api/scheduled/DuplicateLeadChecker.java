package com.tms.api.scheduled;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.tms.dto.response.ClBasket;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class DuplicateLeadChecker {
    private static HashMap<String, String> records = new HashMap();

    public static void addRecord(ClBasket data) {
        records.put(data.getPhone(), data.getProdName());
    }

    public static void addRecord(List<ClBasket> datas) {
        for (ClBasket data : datas) {
            records.put(data.getPhone(), data.getProdName());
        }
    }

    public static void clearRecords() {
        records.clear();
    }

    public static String getRecord(String key){
        return records.get(key);
    }
}
