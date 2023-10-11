package com.tms.api.helper;

import com.tms.DaoConst;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneHeper {
    public static boolean checkPhoneValid(String phonenumber) {
        Pattern pattern = Pattern.compile(DaoConst.PHONE_REGEX);
        Matcher matcher = pattern.matcher(phonenumber);
        return matcher.matches();
    }

}
