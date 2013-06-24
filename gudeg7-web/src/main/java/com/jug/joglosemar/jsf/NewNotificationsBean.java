/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jug.joglosemar.jsf;

import com.jug.joglosemar.ejb.MemberEJB;
import com.jug.joglosemar.ejb.NotificationsEJB;
import com.jug.joglosemar.model.Member;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
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
            notificationsEJB.addMany(title, description, destination);
            System.out.println("[INFO] Finishing to add notifications");
        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage());
        }
    }
    
    private boolean toAll;

    public boolean isToAll() {
        return toAll;
    }

    public void setToAll(boolean toAll) {
        this.toAll = toAll;
    }
    
    public void toAllValueChange(ValueChangeEvent e) {
        Boolean toAll_local = (Boolean) e.getNewValue();
        if(toAll_local) {
            setToAll(true);
        } else {
            setToAll(false);
        }
    }
    
    @EJB
    private MemberEJB memberEJB;
    
    private List<Member> memberList;

    public List<Member> getMemberList() {
        memberList = memberEJB.findAll();
        return memberList;
    }
}
