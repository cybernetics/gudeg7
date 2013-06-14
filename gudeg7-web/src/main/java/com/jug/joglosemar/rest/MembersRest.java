/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jug.joglosemar.rest;

import com.jug.joglosemar.ejb.MemberEJB;
import com.jug.joglosemar.model.Member;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Yosi Pramajaya
 */
@Path("members")
@Stateless
public class MembersRest {
    
    @EJB
    private MemberEJB membersEJB;
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Member[] findAll() {
        List<Member> list = membersEJB.findAll();
        Member[] membersAll = new Member[list.size()];
        for(int i=0; i<list.size(); i++) {
            Member m = list.get(i);
            m.setPassword("*****");
            membersAll[i] = m;
        }
        return membersAll;
    }
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Member findById(@PathParam("id") Long id) {
        Member m = membersEJB.findById(id);
        m.setPassword("*****");
        return m;
    }
    
    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    public Member[] findAllJSON() {
        List<Member> list = membersEJB.findAll();
        Member[] membersAll = new Member[list.size()];
        for(int i=0; i<list.size(); i++) {
            Member m = list.get(i);
            m.setPassword("*****");
            membersAll[i] = m;
        }
        return membersAll;
    }
    
    @Path("{id}/json")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Member findByIdJSON(@PathParam("id") Long id) {
        Member m = membersEJB.findById(id);
        m.setPassword("*****");
        return m;
    }
}
