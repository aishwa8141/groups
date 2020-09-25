package utils.module;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sunbird.util.JsonKey;
import play.mvc.Http;

@RunWith(PowerMockRunner.class)
@PrepareForTest({})
@PowerMockIgnore({"javax.management.*", "javax.net.ssl.*", "javax.security.*"})
public class OnRequestHandlerTest extends BaseRequestHandlerTest {
  private ObjectMapper mapper = new ObjectMapper();

  @Test
  public void initializeContextTest() {
    Http.RequestBuilder requestBuilder = getHttpRequestBuilder();
    requestBuilder.header("msgId", "hd9933-e3-e3m3-343d-dsds");
    Http.Request req = requestBuilder.build();
    try {
      OnRequestHandler onRequestHandler = new OnRequestHandler();
      onRequestHandler.initializeContext(req, "userid");
    } catch (Exception ex) {
      Assert.assertTrue(false);
    }
    Assert.assertTrue(true);
  }

  @Test
  public void initializeContextTestWithUnauthorizedUser() throws JsonProcessingException {
    Http.RequestBuilder requestBuilder = getHttpRequestBuilder();
    requestBuilder.header("msgId", "hd9933-e3-e3m3-343d-dsds");
    Http.Request req = requestBuilder.build();
    try {
      OnRequestHandler onRequestHandler = new OnRequestHandler();
      onRequestHandler.initializeContext(req, JsonKey.UNAUTHORIZED);
    } catch (Exception ex) {
      Assert.assertTrue(false);
    }
    Assert.assertTrue(true);
    String contextStr = null;
    if (req.attrs() != null && req.attrs().containsKey(Attrs.CONTEXT)) {
      contextStr = (String) req.attrs().get(Attrs.CONTEXT);

      Map<String, Object> contextObject = mapper.readValue(contextStr, Map.class);

      Assert.assertEquals(
          JsonKey.DEFAULT_CONSUMER_ID,
          ((Map<String, Object>) contextObject.get(JsonKey.CONTEXT)).get(JsonKey.ACTOR_ID));
    }
  }
}
