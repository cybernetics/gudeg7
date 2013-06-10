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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yosi Pramajaya
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
})
@NamedQueries({
    @NamedQuery(name = "Member.findAll", query = "SELECT m FROM Member m"),
    @NamedQuery(name = "Member.findById", query = "SELECT m FROM Member m WHERE m.id = :membersId"),
    @NamedQuery(name = "Member.findByFirstName", query = "SELECT m FROM Member m WHERE m.firstName LIKE :mFirstName"),
    @NamedQuery(name = "Member.findByLastName", query = "SELECT m FROM Member m WHERE m.lastName LIKE :mLastName"),
    @NamedQuery(name = "Member.findByName", query = "SELECT m FROM Member m WHERE m.firstName LIKE :mFirstName AND m.lastName LIKE :mLastName"),
    @NamedQuery(name = "Member.findByEmail", query = "SELECT m FROM Member m WHERE m.email = :mEmail"),
    @NamedQuery(name = "Member.findByCity", query = "SELECT m FROM Member m WHERE m.city LIKE :mCity"),
    @NamedQuery(name = "Member.findByProvince", query = "SELECT m FROM Member m WHERE m.province LIKE :mProvince"),
    @NamedQuery(name = "Member.findByRegion", query = "SELECT m FROM Member m WHERE m.city LIKE :mCity AND m.province LIKE :mProvince")
})
public class Member implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @NotNull
    @Size(min = 4, max = 45)
    private String firstName;
    
    @Column(nullable = false)
    @NotNull
    @Size(min = 4, max = 45)
    private String lastName;
    
    @Column(nullable = false)
    @NotNull
    private String email;
    
    @Column(nullable = false)
    @NotNull
    private String password;
    
    @Column(nullable = false)
    @NotNull
    @Size(max = 100)
    private String address;
    
    @Column(nullable = false)
    @NotNull
    @Size(max = 50)
    private String city;
    
    @Column(nullable = false)
    @NotNull
    @Size(max = 50)
    private String province;
    
    @Column(nullable = false)
    @NotNull
    private byte[] mac;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getMac() {
        return mac;
    }

    public void setMac(byte[] mac) {
        this.mac = mac;
    }
}
