/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jug.joglosemar.socket;

import com.jug.joglosemar.ejb.MemberEJB;
import java.io.IOException;
import javax.ejb.EJB;
import javax.websocket.OnMessage;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Yosi Pramajaya
 */
@ServerEndpoint("/register/email")
public class RegisterEmailSocket {

    @EJB
    private MemberEJB memberEJB;

    @OnMessage
    public void textMessage(Session session, String email) throws IOException {
        System.out.println("Email: " + email);
        String result;
        if (!patternEmail(email)) {
            result = "<b style='color:red'>This is not an email address!</b>";
        } else {
            if(memberEJB.findByEmail(email) == null) {
                result = "<b style='color:blue'>Available!</b>";
            } else {
                result = "<b style='color:red'>Email has already used!</b>";
            }
        }

        RemoteEndpoint.Basic basic = session.getBasicRemote();
        basic.sendText(result);
    }

    private boolean patternEmail(String msg) {
        boolean msg_match = msg.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
        System.out.println("Email match: " + msg_match);
        return msg_match;
    }
}
