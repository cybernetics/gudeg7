/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jug.joglosemar.wsocket;

import com.jug.joglosemar.jsf.MessSocketBean;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author User
 */

@ServerEndpoint("/socket")
public class MyEndPoint {
    
    @Inject
    private MessSocketBean messSocketBean;
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("[INFO] WebSocket opened with SessionID: " + session.getId());
    }
    
    @OnMessage
    public void onMessage(String message) {
        System.out.println("[INFO] Message arrived: " + message);
        messSocketBean.setLatestMessage(message);
        System.out.println("[INFO] Message stored: " + messSocketBean.getLatestMessage());
    }
    
    @OnClose
    public void onClose(CloseReason reason) {
        System.out.println("[INFO] Session close with reason: " + reason.getReasonPhrase());
    }
}
