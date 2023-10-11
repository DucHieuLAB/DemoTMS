package com.tms.api.consts;

public class MessageConst {
    public static final String NOT_MATCH_VALUE_IN_URL = "Not match with value in url";
    public static final String NOT_FOUND_WITH_OBJECT_PARAMS = "Not found with object params: ";
    public static final String ERROL_INVALID_PHONE_MESSAGE = "Phone not match (+84|84|0)?[0-9]{9}";
    public static final String ERROL_IN_BLACKLIST_MESSAGE = "Lead is in blacklist";
    public static final String ERROL_DUPLICATE_MESSAGE = "Lead is duplicate";
    public static final String STATUS_NEW_IS_NOT_VALID = "Server not expected status ID NEW";
    public static final String SO_REASON_IS_INVALID = "Reason can't not empty when cacel or pending";
    public static final String SO_INVALID_STATUS = "Invalid status for SO";
    public static final String ERROL_NULL_DB_RESPONSE = "DBResponse is null";
    public static final String ERROL_LEAD_UPDATE_IS_EMPTY = "Lead update is empty";
    public static final String UNABLE_TO_CHANGE_STATUS_CANCEL_TO_PENDING = "Unable to Change Status: This Sale Order has been marked as 'Cancelled' and cannot be reverted to 'Pending' status";
    public static final String UNABLE_TO_CHANGE_STATUS_VALIDATE_TO_CANCEL_PENDING = "Status Change Not Allowed: Sale Orders that have been marked as 'Validated' cannot be reverted to 'Cancel' or 'Pending' status";
    public static final String STATUS_RESTRICTION_VALIDATE = "Status Change Restriction: Sale Orders that have been marked as 'Validated' can only be transitioned to the 'Unassigned' status";
    public static final String ERROR_CANNOT_CHANGE_STATUS_DELAY = "Status Change Not Allowed: Sale Orders with 'Delay' status can only be transitioned to 'Cancel' or 'Unassigned' status. To make changes, please choose one of these valid statuses.";
    public static final String ERROR_MESSAGE_VALIDATED_DELAY_CREATION_DATE_REQUIRED = "VALIDATED and DELAY statuses require a valid creation date.";
    public static final String ERROR_MESSAGE_INFORMATION_NULL = "Haven't entered all the necessary information:";
    public static final String ERROR_MESSAGE_INFORMATION = "The information entered is invalid";


}