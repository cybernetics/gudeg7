/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jug.joglosemar.jsf;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Yosi Pramajaya
 */
@ApplicationScoped
@Named("config")
public class Gudeg7Config {
    
    public String getRegisterSocket() {
        return "ws://localhost/gudeg7-web/register/email";
    }
}
