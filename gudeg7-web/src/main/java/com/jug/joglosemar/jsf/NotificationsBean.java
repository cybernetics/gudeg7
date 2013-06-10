/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jug.joglosemar.jsf;

import com.jug.joglosemar.ejb.NotificationsEJB;
import com.jug.joglosemar.model.Member;
import com.jug.joglosemar.model.Notification;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author User
 */
@Named("notifications")
@RequestScoped
public class NotificationsBean implements Serializable {

    @EJB
    private NotificationsEJB notificationsEJB;
    
    @Inject
    private Gudeg7Session session;
    
    private long unreadCount;

    public long getUnreadCount() {
        this.unreadCount = notificationsEJB.count(session.getActiveMembers());
        return unreadCount;
    }
    private String unreadNotificationsText;

    public String getUnreadNotificationsText() {
        System.out.println("[INFO] RequestBean executed!");
        System.out.println("[INFO] Unread count: " + getUnreadCount());
        StringBuilder sb = new StringBuilder();
        sb.append("You have ");
        if (getUnreadCount() == 0) {
            sb.append("no new notification.");
        } else if (getUnreadCount() == 1) {
            sb.append("1 new notification.");
        } else if (getUnreadCount() > 0) {
            sb.append(getUnreadCount()).append(" new notifications.");
        } else {
            sb.append("Notification API has some error!");
        }

        this.unreadNotificationsText = sb.toString();

        return unreadNotificationsText;
    }
    
    private List<Notification> notifications;

    public List<Notification> getNotifications() {
        Member m = session.getActiveMembers();
        if (m != null) {
            notifications = notificationsEJB.readByMembers(m);
        } else {
            notifications = new ArrayList<>();
        }
        return notifications;
    }

    public void deleteAllNotifications() {
        notificationsEJB.deleteAll(session.getActiveMembers());
    }

    public void markAllAsRead() {
        notificationsEJB.markReadAll(session.getActiveMembers());
    }
    
    public void deleteSpecific() {
        Map<String,String> params = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        long id = Long.parseLong(params.get("id"));
        notificationsEJB.remove(id);
    }
    
    public void read() {
        Map<String,String> params = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        long id = Long.parseLong(params.get("id"));
        notificationsEJB.markRead(id, session.getActiveMembers());
    }
    
    private String notifToolbar;

    public String getNotifToolbar() {
        if(getUnreadCount() <= 0) {
            notifToolbar = "<i class=\"icon-bullhorn\"></i>";
        } else {
            notifToolbar = "<i class=\"icon-bullhorn icon-white\"></i>";
        }
        return notifToolbar;
    }
}
