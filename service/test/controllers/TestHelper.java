package controllers;

import static play.test.Helpers.route;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.commons.lang3.StringUtils;
import org.powermock.api.mockito.PowerMockito;
import org.sunbird.exception.BaseException;
import org.sunbird.util.JsonKey;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import utils.module.Attrs;

/** This a helper class for All the Controllers Test */
public class TestHelper {
  protected org.sunbird.Application sbApp;

  // Let test cases create one if needed. This will be private.
  private final ObjectMapper mapperObj = new ObjectMapper();

  // Only for derivations
  protected Map<String, String> headerMap;

  static {
    EmbeddedCassandra.getInstance().start();
  }

  public TestHelper() {
    Map userAuthentication = new HashMap<String, String>();
    userAuthentication.put(JsonKey.USER_ID, "userId");

    headerMap = new WeakHashMap<>();
    headerMap.put(JsonKey.VER, "1.0");
    headerMap.put(JsonKey.ID, "api.test.id");
    headerMap.put(JsonKey.REQUEST_MESSAGE_ID, this.getClass().getSimpleName());
    headerMap.put(JsonKey.USER_ID, "2345");
  }

  /**
   * This method will perform a request call.
   *
   * @param url
   * @param method
   * @param requestMap
   * @param headerMap
   * @return Result
   */
  public Result performTest(String url, String method, Map requestMap, Map headerMap) {
    ObjectMapper mapper = new ObjectMapper();
    String data = mapToJson(requestMap);
    Http.RequestBuilder req;
    if (StringUtils.isNotBlank(data) && !requestMap.isEmpty()) {
      JsonNode json = Json.parse(data);
      req = new Http.RequestBuilder().bodyJson(json).uri(url).method(method);
    } else {
      req = new Http.RequestBuilder().uri(url).method(method);
    }
    req.header("Content-Type", "application/json");
    Map<String, Object> map = new WeakHashMap<>();
    Map context = new WeakHashMap();
    context.put(JsonKey.USER_ID, "3424345");
    map.put(JsonKey.CONTEXT, context);
    try {
      req.attr(Attrs.CONTEXT, mapper.writeValueAsString(map));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    Result result = route(EmbeddedCassandra.getInstance().getApp(), req);
    return result;
  }

  public final void setupMock() throws BaseException {
    sbApp = PowerMockito.mock(org.sunbird.Application.class);
    PowerMockito.mockStatic(org.sunbird.Application.class);
    PowerMockito.when(org.sunbird.Application.getInstance()).thenReturn(sbApp);
    sbApp.init();
  }

  /**
   * This method is responsible for converting map to json
   *
   * @param map
   * @return String
   */
  public String mapToJson(Map map) {
    String jsonResp = "";

    if (map != null) {
      try {
        jsonResp = mapperObj.writeValueAsString(map);
      } catch (IOException e) {
      }
    }
    return jsonResp;
  }

  /**
   * This method is used to return the status Code for the perform request
   *
   * @param result
   * @return
   */
  public int getResponseStatus(Result result) {
    return result.status();
  }
}
