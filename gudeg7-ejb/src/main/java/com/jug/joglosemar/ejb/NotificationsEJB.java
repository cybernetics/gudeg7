/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jug.joglosemar.ejb;

import com.jug.joglosemar.model.Member;
import com.jug.joglosemar.model.Notification;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Yosi Pramajaya
 */
@Stateless
@LocalBean
public class NotificationsEJB {
    
    @PersistenceContext
    private EntityManager em;
    
    public void addNew(Notification notify) {
        try {
            em.persist(notify);
            System.out.println("[INFO] " + "Notifications has been added!");
        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
    public long count(Member m) {
        try {
            long count = (long) em.createNamedQuery("Notification.countUnread")
                    .setParameter("notifyMember", m)
                    .getSingleResult();
            return count;
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            return 0;
        }
    }

    public List<Notification> readByMembers(Member m) {
        List<Notification> list = new ArrayList<>();
        for (Notification n : em.createNamedQuery("Notification.findByMember", Notification.class)
                .setParameter("notifyMember", m)
                .getResultList()) {
            list.add(n);
        }
        return list;
    }

    public void remove(long id) {
        try {
            Notification n = em.createNamedQuery("Notification.findById", Notification.class)
                    .setParameter("notifyId", id)
                    .getSingleResult();
            
            em.remove(n);
        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    public void markRead(long id, Member m) {
        try {
            em.createNamedQuery("Notification.markRead", Notification.class)
                    .setParameter("notifyId", id)
                    .setParameter("notifyMember", m)
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
    public void markReadAll(Member m) {
        try {
            em.createNamedQuery("Notification.markReadAll", Notification.class)
                    .setParameter("notifyMember", m)
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    public void deleteAll(Member m) {
        try {
            em.createNamedQuery("Notification.deleteAll", Notification.class)
                    .setParameter("notifyMember", m)
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
