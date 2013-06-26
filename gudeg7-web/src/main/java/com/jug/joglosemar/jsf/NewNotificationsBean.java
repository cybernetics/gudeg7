/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jug.joglosemar.jsf;

import com.jug.joglosemar.ejb.MemberEJB;
import com.jug.joglosemar.ejb.NotificationsEJB;
import com.jug.joglosemar.model.Member;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

/**
 *
 * @author Yosi Pramajaya
 */
@FlowScoped("addnotify")
@Named("newnotify")
public class NewNotificationsBean {

    private String title;
    private String description;
    private Collection<Member> destination;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Member> getDestination() {
        return destination;
    }

    public void setDestination(Collection<Member> destination) {
        this.destination = destination;
    }
    @EJB
    private NotificationsEJB notificationsEJB;

    public void add() {
        try {
            System.out.println("[INFO] Starting to add notifications");
            if (toAll) {
                System.out.println("Add to all members");
                notificationsEJB.addAll(title, description);
            } else {
                notificationsEJB.addMany(title, description, getSelectedMember());
            }
            System.out.println("[INFO] Finishing to add notifications");
            this.result = "addnotifyFin";
        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage());
            resultMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Add notification failed!", e.getMessage());
            this.result = "addnotifyErr";
        }
    }
    private FacesMessage resultMessage;
    private String result;

    public String result() {
        if (resultMessage != null) {
            FacesContext.getCurrentInstance().addMessage(null, resultMessage);
        }
        return result;
    }

    private boolean toAll = true;

    public boolean isToAll() {
        return toAll;
    }

    public void setToAll(boolean toAll) {
        this.toAll = toAll;
    }
    @EJB
    private MemberEJB memberEJB;
    private List<Member> memberList;

    public List<Member> getMemberList() {
        if (memberList == null) {
            memberList = memberEJB.findAll();
        }
        return memberList;
    }

    public String getStatus() {
        return "OK";
    }

    public List<Member> getSelectedMember() {
        List<Member> mList = new ArrayList<>();
        for (Member m : memberList) {
            if (m.isSelected()) {
                mList.add(m);
            }
        }

        return mList;
    }
    
    public void reset() {
        setTitle(null);
        setDescription(null);
        setDestination(null);
        this.result = null;
        setToAll(true);
    }
}
