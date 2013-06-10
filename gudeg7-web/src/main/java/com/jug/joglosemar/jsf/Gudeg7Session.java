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

    public String signin() {
        //Check signinUsername and signinPassword
        String uname = this.signinUsername;
        String pword = this.signinPassword;
        Member m = memberEJB.findByEmail(uname);
        if (m != null) {
            if (m.getPassword().equals(pword)) {
                //Set ActiveMembers
                this.activeMembers = m;
                return "home?faces-redirect=true";
            }
        }
        this.activeMembers = null;
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Login failed!", "Username and Password combination is wrong!");
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
    private String memberOptions;

    public String getMemberOptions() {
        this.memberOptions = "<li class=\"dropdown\">"
                + "<a id=\"drop1\" href=\"#\" role=\"button\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"
                + "Options <b class=\"caret\"></b>"
                + "</a>"
                + "<ul class=\"dropdown-menu\" role=\"menu\" aria-labelledby=\"drop1\">"
                + "<li role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"/gudeg7-web/members/notifications.jsf\">Notifications</a></li>"
                + "<li role=\"presentation\" class=\"divider\"></li>"
                + "<li role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"#\">Other actions</a></li>"
                + "</ul>"
                + "</li>";
        return memberOptions;
    }
}
