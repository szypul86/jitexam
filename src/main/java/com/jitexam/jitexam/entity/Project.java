package com.jitexam.jitexam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contactperson;
    private Date creationdatetime;
    private Date modificationdatetime;
    private String name;
    @ManyToOne
    @JoinColumn(name = "clientid")
    private Client client;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private String addinguser;
    @ManyToMany(mappedBy = "projects")
    @JsonIgnore
    private Set<Account> accounts;
}
