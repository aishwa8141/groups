package org.sunbird.message;

/** This interface will hold all the response key and message */
public interface IResponseMessage {

  String INVALID_REQUESTED_DATA = "INVALID_REQUESTED_DATA";
  String INVALID_OPERATION_NAME = "INVALID_OPERATION_NAME";
  String INTERNAL_ERROR = "INTERNAL_ERROR";
  String SERVER_ERROR = "SERVER_ERROR";
  String ID_ALREADY_EXISTS = "ID_ALREADY_EXISTS";
  String MISSING_MANDATORY_PARAMS = "MISSING_MANDATORY_PARAMS";
  String DATA_TYPE_ERROR = "DATA_TYPE_ERROR";
  String EMPTY_MANDATORY_PARAM = "EMPTY_MANDATORY_PARAM";
  String INVALID_ID_PROVIDED = "INVALID_ID_PROVIDED";
  String INVALID_PROVIDED_URL = "INVALID_PROVIDED_URL";
  String INVALID_RELATED_TYPE = "INVALID_RELATED_TYPE";
  String INVALID_RECIPIENT_TYPE = "INVALID_RECIPIENT_TYPE";
  String INVALID_PROPERTY_ERROR = "INVALID_PROPERTY_ERROR";
  String DB_UPDATE_FAIL = "DB_UPDATE_FAIL";
  String DB_INSERTION_FAIL = "DB_INSERTION_FAIL";
  String INVALID_CONFIGURATION = "INVALID_CONFIGURATION";
  String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
  String SERVICE_UNAVAILABLE = "SERVICE UNAVAILABLE";
  String INVALID_PARAMETER_VALUE = "INVALID_PARAMETER_VALUE";
  String GROUP_NOT_FOUND = "GROUP_NOT_FOUND";

  interface Key {
    String UNAUTHORIZED = "UNAUTHORIZED";
    String KEY_CLOAK_DEFAULT_ERROR = "KEY_CLOAK_DEFAULT_ERROR";
    String UNABLE_TO_COMMUNICATE_WITH_ACTOR = "UNABLE_TO_COMMUNICATE_WITH_ACTOR";
    String EXCEEDED_MEMBER_MAX_LIMIT = "EXCEEDED_MEMBER_MAX_LIMIT";
    String EXCEEDED_GROUP_MAX_LIMIT = "EXCEEDED_GROUP_MAX_LIMIT";
    String EXCEEDED_ACTIVITY_MAX_LIMIT = "EXCEEDED_ACTIVITY_MAX_LIMIT";
    String SERVICE_UNAVAILABLE = "SERVICE UNAVAILABLE";
    String INVALID_OPERATION_NAME = "INVALID_OPERATION_NAME";
  }

  interface Message {
    String KEY_CLOAK_DEFAULT_ERROR = "server error at sso.";
    String UNABLE_TO_COMMUNICATE_WITH_ACTOR = "Unable to communicate with actor.";
    String EXCEEDED_MEMBER_MAX_LIMIT = "Exceeded the member max size limit";
    String EXCEEDED_ACTIVITY_MAX_LIMIT = "Exceeded the activity max size limit";
    String EXCEEDED_GROUP_MAX_LIMIT = "Exceeded the group max size limit";
    String INVALID_REQUESTED_DATA = "Requested data for this operation is not valid.";
    String DATA_TYPE_ERROR = "Data type of {0} should be {1}.";
    String GROUP_NOT_FOUND = "group does not exist with this group Id {0}.";
    String INVALID_PARAMETER_VALUE =
        "Invalid value {0} for parameter {1}. Please provide a valid value.";
    String MISSING_MANDATORY_PARAMS = "MANDATORY PARAM {0}.{1} IS MISSING";
    String INVALID_PROPERTY_ERROR = "Invalid property {0}.";
    String DB_INSERTION_FAIL = "DB insert operation failed.";
    String DB_UPDATE_FAIL = "Db update operation failed.";
    String INTERNAL_ERROR = "Process failed,please try again later.";
    String SERVICE_UNAVAILABLE = "SERVICE_UNAVAILABLE";
    String UNAUTHORIZED = "You are not authorized.";
    String INVALID_OPERATION_NAME =
        "Operation name is invalid. Please provide a valid operation name";
  }
}
