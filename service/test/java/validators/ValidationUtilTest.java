package validators;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.util.JsonKey;

public class ValidationUtilTest {

  @Test
  public void validateRequestObjectThrowValidationException() {
    Request request = createRequestObject();
    try {
      ValidationUtil.validateRequestObject(request);
    } catch (BaseException ex) {
      Assert.assertTrue(true);
      Assert.assertEquals("INVALID_REQUESTED_DATA", ex.getCode());
    }
  }

  @Test
  public void validateRequestObjectSuccess() {
    Request request = createRequestObject();
    request.put("name", "group1");
    try {
      ValidationUtil.validateRequestObject(request);
    } catch (BaseException ex) {
      Assert.assertTrue(false);
    }
    Assert.assertTrue(true);
  }

  /** Test ValidateMandatoryParamsWithType with String type parameter */
  @Test
  public void validateMandatoryParamsWithTypeString() {
    Request request = createRequestObject();
    request.put("name", "group1");
    try {
      ValidationUtil.validateMandatoryParamsWithType(
          request.getRequest(), Lists.newArrayList("name"), String.class, true, JsonKey.REQUEST);
    } catch (BaseException ex) {
      Assert.assertTrue(false);
    }
    Assert.assertTrue(true);
  }

  @Test
  public void validateMandatoryParamsWithTypeStringWithIntegerValue() {
    Request request = createRequestObject();
    request.put("name", 1);
    try {
      ValidationUtil.validateMandatoryParamsWithType(
          request.getRequest(), Lists.newArrayList("name"), String.class, true, JsonKey.REQUEST);
    } catch (BaseException ex) {
      Assert.assertTrue(true);
      Assert.assertEquals("request.name PARAM SHOULD BE OF TYPE java.lang.String", ex.getMessage());
    }
  }

  @Test
  public void validateMandatoryParamsWithTypeStringWithEmptyValue() {
    Request request = createRequestObject();
    request.put("name", "");
    try {
      ValidationUtil.validateMandatoryParamsWithType(
          request.getRequest(), Lists.newArrayList("name"), String.class, true, JsonKey.REQUEST);
    } catch (BaseException ex) {
      Assert.assertTrue(true);
      Assert.assertEquals("MANDATORY PARAM request.name IS MISSING", ex.getMessage());
    }
  }

  /** Test ValidateMandatoryParamsWithType with Integer type parameter */
  @Test
  public void validateMandatoryParamsWithTypeInteger() {
    Request request = createRequestObject();
    request.put("id", 1);
    try {
      ValidationUtil.validateMandatoryParamsWithType(
          request.getRequest(), Lists.newArrayList("id"), Integer.class, true, JsonKey.REQUEST);
    } catch (BaseException ex) {
      Assert.assertTrue(false);
    }
    Assert.assertTrue(true);
  }

  /** Test ValidateMandatoryParamsWithType with Map type parameter */
  @Test
  public void validateMandatoryParamsWithTypeMap() {
    Request request = createRequestObject();
    request.getRequest().put(JsonKey.FILTERS, new HashMap<>());
    try {
      ValidationUtil.validateMandatoryParamsWithType(
          request.getRequest(),
          Lists.newArrayList(JsonKey.FILTERS),
          Map.class,
          false,
          JsonKey.REQUEST);
    } catch (BaseException ex) {
      Assert.assertTrue(false);
    }
    Assert.assertTrue(true);
  }

  @Test
  public void validateMandatoryParamsWithTypeMapWithValue() {
    Request request = createRequestObject();
    Map<String, String> filterMap = new HashMap<>();
    filterMap.put("userId", "id1");
    request.getRequest().put(JsonKey.FILTERS, filterMap);
    try {
      ValidationUtil.validateMandatoryParamsWithType(
          request.getRequest(),
          Lists.newArrayList(JsonKey.FILTERS),
          Map.class,
          true,
          JsonKey.REQUEST);
    } catch (BaseException ex) {
      Assert.assertTrue(false);
    }
    Assert.assertTrue(true);
  }

  @Test
  public void validateMandatoryParamsWithTypeMapWithEmptyValueThrowsException() {
    Request request = createRequestObject();
    request.getRequest().put(JsonKey.FILTERS, new HashMap<>());
    try {
      ValidationUtil.validateMandatoryParamsWithType(
          request.getRequest(),
          Lists.newArrayList(JsonKey.FILTERS),
          Map.class,
          true,
          JsonKey.REQUEST);
    } catch (BaseException ex) {
      Assert.assertTrue(true);
      Assert.assertEquals("MANDATORY PARAM request.filters IS MISSING", ex.getMessage());
    }
  }

  /** Test ValidateMandatoryParamsWithType with List type parameter */
  @Test
  public void validateMandatoryParamsWithTypeList() {
    Request request = createRequestObject();
    request.getRequest().put(JsonKey.FILTERS, new ArrayList<>());
    try {
      ValidationUtil.validateMandatoryParamsWithType(
          request.getRequest(),
          Lists.newArrayList(JsonKey.FILTERS),
          List.class,
          false,
          JsonKey.REQUEST);
    } catch (BaseException ex) {
      Assert.assertTrue(false);
    }
    Assert.assertTrue(true);
  }

  @Test
  public void validateMandatoryParamsWithTypeListWithValue() {
    Request request = createRequestObject();
    request.getRequest().put(JsonKey.FILTERS, Lists.newArrayList("group1", "group2"));
    try {
      ValidationUtil.validateMandatoryParamsWithType(
          request.getRequest(),
          Lists.newArrayList(JsonKey.FILTERS),
          List.class,
          true,
          JsonKey.REQUEST);
    } catch (BaseException ex) {
      Assert.assertTrue(false);
    }
    Assert.assertTrue(true);
  }

  @Test
  public void validateMandatoryParamsWithTypeListWithEmptyValueThrowsBaseException() {
    Request request = createRequestObject();
    request.getRequest().put(JsonKey.FILTERS, new ArrayList<>());
    try {
      ValidationUtil.validateMandatoryParamsWithType(
          request.getRequest(),
          Lists.newArrayList(JsonKey.FILTERS),
          List.class,
          true,
          JsonKey.REQUEST);
    } catch (BaseException ex) {
      Assert.assertTrue(true);
      Assert.assertEquals("MANDATORY PARAM request.filters IS MISSING", ex.getMessage());
    }
  }

  @Test
  public void validateParamValue() {
    Map<String, List<String>> paramValue = new HashMap<>();
    paramValue.put(JsonKey.STATUS, Lists.newArrayList(JsonKey.ACTIVE, JsonKey.INACTIVE));
    paramValue.put(JsonKey.ROLE, Lists.newArrayList(JsonKey.ADMIN, JsonKey.MEMBER));
    Map<String, Object> member = new HashMap<>();
    member.put(JsonKey.STATUS, JsonKey.ACTIVE);
    member.put(JsonKey.ROLE, JsonKey.ADMIN);
    try {
      ValidationUtil.validateParamValue(
          member, Lists.newArrayList(JsonKey.STATUS, JsonKey.ROLE), paramValue, JsonKey.MEMBERS);
    } catch (BaseException ex) {
      Assert.assertTrue(false);
    }
    Assert.assertTrue(true);
  }

  @Test
  public void validateInvalidParam() {
    Map<String, List<String>> paramValue = new HashMap<>();
    paramValue.put(JsonKey.STATUS, Lists.newArrayList(JsonKey.ACTIVE, JsonKey.INACTIVE));
    Map<String, Object> member = new HashMap<>();
    member.put(JsonKey.STATUS, "notValid");
    try {
      ValidationUtil.validateParamValue(
          member, Lists.newArrayList(JsonKey.STATUS, JsonKey.ROLE), paramValue, JsonKey.MEMBERS);
    } catch (BaseException ex) {
      Assert.assertTrue(true);
      Assert.assertEquals("INVALID_PARAMETER_VALUE", ex.getCode());
    }
  }

  private Request createRequestObject() {
    Request request = new Request();
    Map<String, Object> map = new HashMap<>();
    request.setRequest(map);
    return request;
  }
}
