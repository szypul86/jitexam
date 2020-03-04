package com.jitexam.jitexam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String benefitsamount;
    private Date creationdatetime;
    private String email;
    private Date modificationdatetime;
    private String name;
    private boolean setupcomplete;
    private String surname;
    private String username;
    @ManyToOne
    @JoinColumn(name = "defaultclient")
    private Client defaultclient;


    @ManyToOne
    @JoinColumn(name = "defaultproject")
    private Project defaultproject;
    @ManyToMany()
    @JoinTable(name = "accountproject",
            joinColumns = @JoinColumn(name = "accounts_id"),
            inverseJoinColumns = @JoinColumn(name = "projects_id"))
    @JsonIgnore
    private Set<Project> projects;
    @ManyToMany()
    @JoinTable(name = "accountclient",
            joinColumns = @JoinColumn(name = "clients_id"),
            inverseJoinColumns = @JoinColumn(name = "accounts_id"))
    @JsonIgnore
    private Set<Client> clients;
}