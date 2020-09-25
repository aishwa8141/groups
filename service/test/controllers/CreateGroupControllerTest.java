package controllers;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import javax.ws.rs.core.Response;
import org.junit.Test;
import org.sunbird.util.JsonKey;
import play.mvc.Result;

public class CreateGroupControllerTest extends TestHelper {

  @Test
  public void testCreateGroupPasses() throws Exception {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("name", "group");
    Map<String, Object> request = new HashMap<>();
    request.put("request", reqMap);
    Map context = new WeakHashMap();
    context.put(JsonKey.USER_ID, "3424345");
    request.put("context", context);
    Result result = performTest("/v1/group/create", "POST", request, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.OK.getStatusCode());
  }

  @Test
  public void testMandatoryParamGroupName() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("description", "group");
    Map<String, Object> request = new HashMap<>();
    request.put("request", reqMap);
    Result result = performTest("/v1/group/create", "POST", request, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void testGroupNameType() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("name", 123);
    Map<String, Object> request = new HashMap<>();
    request.put("request", reqMap);
    Result result = performTest("/v1/group/create", "POST", request, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void testCreateGroupWithEmptyRequestObject() {
    Map<String, Object> request = new HashMap<>();
    request.put("name", "groupName");
    Result result = performTest("/v1/group/create", "POST", request, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.BAD_REQUEST.getStatusCode());
  }
}
