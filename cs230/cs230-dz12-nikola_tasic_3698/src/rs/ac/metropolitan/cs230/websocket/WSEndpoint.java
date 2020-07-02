package rs.ac.metropolitan.cs230.websocket;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws")
public class WSEndpoint {

    @OnMessage
    public String onMessage(String message) {
        String returnValue;

        if (message.equals("getDefaults")) {
            returnValue = "{" +
                    "\"firstName\":\"Generated First Name\"," +
                    "\"lastName\":\"Generated Last Name\"," +
                    "\"city\":\"Generated City Name\"" +
                    "}";
        } else {
            returnValue="";
        }
        return returnValue;
    }
}
