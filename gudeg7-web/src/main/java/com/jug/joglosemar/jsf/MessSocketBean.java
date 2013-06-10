/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jug.joglosemar.jsf;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author User
 */

@Named
@RequestScoped
public class MessSocketBean {
    
    private String latestMessage;

    public String getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(String latestMessage) {
        this.latestMessage = latestMessage;
    }
}
