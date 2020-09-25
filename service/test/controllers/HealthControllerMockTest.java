package controllers;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import org.junit.Ignore;
import org.junit.Test;
import org.sunbird.exception.BaseException;
import play.mvc.Result;

// @RunWith(PowerMockRunner.class)
// @PrepareForTest({org.sunbird.Application.class, BaseController.class, ActorRef.class,
// Await.class})
// @PowerMockIgnore({"javax.management.*", "javax.net.ssl.*", "javax.security.*"})
@Ignore
public class HealthControllerMockTest extends TestHelper {

  @Test
  public void testOnServerHandlerPasses() throws BaseException {
    Result result = performTest("/service/health", "GET", null, headerMap);
    System.out.println(getResponseStatus(result));
    assertEquals(Response.Status.fromStatusCode(500).getStatusCode(), getResponseStatus(result));
  }
}
