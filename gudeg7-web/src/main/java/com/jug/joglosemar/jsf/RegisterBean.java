/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jug.joglosemar.jsf;

import com.jug.joglosemar.ejb.MemberEJB;
import com.jug.joglosemar.model.Member;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

/**
 *
 * @author Yosi Pramajaya
 */
@Named
@FlowScoped("registerFlow")
public class RegisterBean implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String city;
    private String province;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStatus() {
        return "OK";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @EJB
    private MemberEJB memberEJB;

    public void register() {
        System.out.println("[INFO] Register method is actually executed!");
        Member m = new Member();
        m.setFirstName(firstName);
        m.setLastName(lastName);
        m.setEmail(email);
        m.setPassword(password);
        m.setAddress(address);
        m.setCity(city);
        m.setProvince(province);
        try {
            this.errorMessage = null;
            memberEJB.registerNew(m);
            this.result = "registerFlowFin";
        } catch (Exception e) {
            this.errorMessage = e.getMessage();
            this.result = "registerFlowFail";
        }
    }
    
    public void reset() {
        setFirstName(null);
        setLastName(null);
        setEmail(null);
        setPassword(null);
        setAddress(null);
        setCity(null);
        setProvince(null);
        setErrorMessage(null);
    }
    
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    private String result;

    public String result() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
