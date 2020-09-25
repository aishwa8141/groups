package controllers;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import org.junit.Test;
import org.sunbird.util.JsonKey;
import play.mvc.Result;

public class SearchGroupControllerTest extends TestHelper {

  @Test
  public void searchGroupSuccessResponse() {
    Map<String, Object> reqMap = new HashMap<>();
    Map<String, Object> filters = new HashMap<>();
    reqMap.put(JsonKey.FILTERS, filters);
    Map<String, Object> request = new HashMap<>();
    request.put("request", reqMap);
    Result result = performTest("/v1/group/list", "POST", request, headerMap);
    assertTrue(getResponseStatus(result) == Response.Status.OK.getStatusCode());
  }
}
