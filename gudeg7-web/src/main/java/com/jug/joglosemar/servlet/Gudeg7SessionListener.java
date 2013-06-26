/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jug.joglosemar.servlet;

import com.jug.joglosemar.jsf.Gudeg7Config;
import javax.inject.Inject;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author Yosi Pramajaya
 */
@WebListener
public class Gudeg7SessionListener implements HttpSessionListener {

    @Inject
    private Gudeg7Config config;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //DO NOTHING,
        //EXCEPT YOU'RE SIGNING IN (Gudeg7Session)
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (config.getSessions().contains(se.getSession())) {
            config.getSessions().remove(se.getSession());
            System.out.println("Session removed");
        } else {
            System.out.println("NO SESSION MADE LIKE THIS");
        }
    }
}
