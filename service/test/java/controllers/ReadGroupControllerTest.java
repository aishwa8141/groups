package controllers;

import org.junit.Ignore;
import org.junit.Test;
import play.mvc.Result;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ReadGroupControllerTest extends TestHelper {

    // TODO - without inserting the group, how can this be read back here in a unit test
    @Ignore
    @Test
    public void testReadGroupPasses() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("groupId", "group1");
        Map<String, Object> request = new HashMap<>();
        request.put("request", reqMap);
        Result result = performTest("/v1/group/read/:group1", "GET", request, headerMap);
        assertTrue(getResponseStatus(result) == Response.Status.OK.getStatusCode());
    }

}
