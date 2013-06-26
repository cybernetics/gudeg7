/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jug.joglosemar.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
    @NamedQuery(name = "Notification.findById", query = "SELECT n FROM Notification n WHERE n.id = :notifyId"),
    @NamedQuery(name = "Notification.findByMember", query = "SELECT n FROM Notification n WHERE n.member = :notifyMember"),
    @NamedQuery(name = "Notification.countUnread", query = "SELECT COUNT(n.id) FROM Notification n WHERE n.unread = TRUE AND n.member = :notifyMember"),
    @NamedQuery(name = "Notification.countAll", query = "SELECT COUNT(n.id) FROM Notification n WHERE n.member = :notifyMember"),
    @NamedQuery(name="Notification.markRead", query = "UPDATE Notification n SET n.unread = FALSE WHERE n.id = :notifyId AND n.member = :notifyMember"),
    @NamedQuery(name="Notification.markReadAll", query = "UPDATE Notification n SET n.unread = FALSE WHERE n.member = :notifyMember"),
    @NamedQuery(name="Notification.deleteAll", query = "DELETE FROM Notification n WHERE n.member = :notifyMember")
})
public class Notification implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    @NotNull
    private String title;
    
    @Column(nullable = false)
    @NotNull
    private String description;
    
    @ManyToOne
    private Member member;
    
    @Column(nullable = false)
    private boolean unread = true; //default is true
    
    @Transient
    private String shortDesc;

    public String getShortDesc() {
        this.shortDesc = this.description.length()<=50? 
                this.description : this.description.substring(0, 50) + "...";
        return shortDesc;
    }
    
    public Notification() { }

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
