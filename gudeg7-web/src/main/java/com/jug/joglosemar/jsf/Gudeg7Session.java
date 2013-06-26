/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jug.joglosemar.jsf;

import com.jug.joglosemar.ejb.MemberEJB;
import com.jug.joglosemar.model.Member;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yosi Pramajaya
 */
@Named(value = "gudeg7Session")
@SessionScoped
public class Gudeg7Session implements Serializable {

    /**
     * Creates a new instance of Gudeg7Session
     */
    public Gudeg7Session() {
    }
    private Member activeMembers;

    public Member getActiveMembers() {
        return activeMembers;
    }

    public void setActiveMembers(Member activeMembers) {
        this.activeMembers = activeMembers;
    }
    //Sign in method
    @EJB
    private MemberEJB memberEJB;
    @Inject
    private Gudeg7Config config;

    public String signin() {
        //Check signinUsername and signinPassword
        String uname = this.signinUsername;
        String pword = this.signinPassword;

        FacesMessage message;

        Member m = memberEJB.findByEmail(uname);
        if (m != null) {
            if (m.getPassword().equals(pword)) {
                //Check whether there was user with logged in username:
                for (HttpSession s : config.getSessions()) {
                    if (uname.equals(s.getAttribute("username"))) {
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Login failed!", "There's user with the same username");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return "signin";
                    }
                }

                //Set ActiveMembers
                this.activeMembers = m;

                FacesContext ctx = FacesContext.getCurrentInstance();
                HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
                try {
                    //Make sure you have registered user in your server
                    //check create.sql in ejb-module
                    //for backend module
                    req.login(uname, pword);
                } catch (ServletException ex) {
                    System.err.println("[ERROR] " + ex.getMessage());
                }

                HttpSession sess = (HttpSession) ctx.getExternalContext().getSession(false);
                sess.setAttribute("username", uname);
                config.getSessions().add(sess);
                System.out.println("Adding session to the list");

                return "home?faces-redirect=true";
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Login failed!", "Username and Password combination is wrong!");
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login failed!", "User with that email doesn't exist!");
        }
        this.activeMembers = null;
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "signin";
    }
    //Sign in username and password
    private String signinUsername;
    private String signinPassword;

    public String getSigninUsername() {
        return signinUsername;
    }

    public void setSigninUsername(String signinUsername) {
        this.signinUsername = signinUsername;
    }

    public String getSigninPassword() {
        return signinPassword;
    }

    public void setSigninPassword(String signinPassword) {
        this.signinPassword = signinPassword;
    }

    //Sign out algorithm
    public void signout() {
        this.activeMembers = null;
        this.signinPassword = null;
        this.signinUsername = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public boolean isBackendEnabled() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
        return req.getRemoteUser() != null;
    }
}
