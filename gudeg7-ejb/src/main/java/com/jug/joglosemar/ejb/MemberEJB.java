/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jug.joglosemar.ejb;

import com.jug.joglosemar.model.Member;
import com.jug.joglosemar.model.Notification;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Yosi Pramajaya
 */
@Stateless
@LocalBean
public class MemberEJB {

    @PersistenceContext
    private EntityManager em;

    public void registerNew(Member member) {
        try {
            em.persist(member);
            System.out.println("[INFO] " + "Member registered: " + member.getLastName().toUpperCase()
                    + ", " + member.getFirstName());
            
            Notification n = new Notification();
            n.setTitle("Welcome to Gudeg7");
            n.setDescription("Hi " + member.getFirstName() + ", we're very glad to have you join us!");
            n.setMember(member);
            
            em.persist(n);
            System.out.println("[INFO] " + "Notifications also added!");
            
        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    public void updateMember(Member member) {
        try {
            em.merge(member);
            System.out.println("[INFO] " + "Member updated: " + member.getLastName().toUpperCase()
                    + ", " + member.getFirstName());
        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage());
        }
    }

    public Member findById(Long id) {
        Member m2 = new Member();
        try {
            m2 = em.createNamedQuery("Member.findById", Member.class)
                    .setParameter("memberId", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.err.println("[ERROR] No result!");
            System.err.println("[ERROR] " + e.getMessage());
        }
        return m2;
    }

    public List<Member> findAll() {
        //Prevent NPE
        List<Member> list = new ArrayList<>();
        for (Member m : em.createNamedQuery("Member.findAll", Member.class).getResultList()) {
            list.add(m);
        }
        return list;
    }

    public List<Member> findByFirstName(String firstName) {
        //Prevent NPE
        List<Member> list = new ArrayList<>();
        for (Member m : em.createNamedQuery("Member.findByFirstName", Member.class)
                .setParameter("mFirstName", firstName)
                .getResultList()) {
            list.add(m);
        }
        return list;
    }

    public List<Member> findByLastName(String lastName) {
        //Prevent NPE
        List<Member> list = new ArrayList<>();
        for (Member m : em.createNamedQuery("Member.findByLastName", Member.class)
                .setParameter("mLastName", lastName)
                .getResultList()) {
            list.add(m);
        }
        return list;
    }

    public List<Member> findByName(String firstName, String lastName) {
        //Prevent NPE
        List<Member> list = new ArrayList<>();
        for (Member m : em.createNamedQuery("Member.findByName", Member.class)
                .setParameter("mFirstName", firstName)
                .setParameter("mLastName", lastName)
                .getResultList()) {
            list.add(m);
        }
        return list;
    }

    public Member findByEmail(String email) {
        Member m2 = null;
        try {
            m2 = em.createNamedQuery("Member.findByEmail", Member.class)
                    .setParameter("mEmail", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.err.println("[ERROR] No result!");
            System.err.println("[ERROR] " + e.getMessage());
        }
        return m2;
    }

    public List<Member> findByCity(String city) {
        //Prevent NPE
        List<Member> list = new ArrayList<>();
        for (Member m : em.createNamedQuery("Member.findByCity", Member.class)
                .setParameter("mCity", city)
                .getResultList()) {
            list.add(m);
        }
        return list;
    }

    public List<Member> findByProvince(String province) {
        //Prevent NPE
        List<Member> list = new ArrayList<>();
        for (Member m : em.createNamedQuery("Member.findByProvince", Member.class)
                .setParameter("mProvince", province)
                .getResultList()) {
            list.add(m);
        }
        return list;
    }

    public List<Member> findByRegion(String city, String province) {
        //Prevent NPE
        List<Member> list = new ArrayList<>();
        for (Member m : em.createNamedQuery("Member.findByRegion", Member.class)
                .setParameter("mCity", city)
                .setParameter("mProvince", province)
                .getResultList()) {
            list.add(m);
        }
        return list;
    }
}
