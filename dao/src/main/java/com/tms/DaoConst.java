package com.tms;

public class DaoConst {
    // Format: yyyyMMdd - Years 1900-2099
    public static final String DATE_REGEX = "^((19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01]))$";
    // Format: yyyyMMdd|yyyyMMdd - Years 1900-2099
    public static final String DATE_BETWEEN_REGEX = "^((19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01]))\\|((19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01]))$";
    // Format: yyyyMMddHHmmss - Years 1900-2099
    public static final String DATE_TIME_REGEX = "^((19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])([01]\\d|2[0-3])([0-5]\\d)([0-5]\\d))$";
    // Format: yyyyMMddHHmmss|yyyyMMddHHmmss - Years 1900-2099
    public static final String DATE_TIME_BETWEEN_REGEX = "^((19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])([01]\\d|2[0-3])([0-5]\\d)([0-5]\\d))\\|((19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])([01]\\d|2[0-3])([0-5]\\d)([0-5]\\d))$";
    public static final String ID_LIST_SPLIT_BY_COMMA_REGEX = "^[\\d]+(,[\\d]+)*$";
    // Format: +84123456789|0123456789|84123456789|123456789
    public static final String PHONE_REGEX = "^(\\+84|0|84)?[0-9]{9}$";
}
