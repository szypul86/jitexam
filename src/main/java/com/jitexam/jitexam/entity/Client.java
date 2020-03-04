package com.jitexam.jitexam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Client {
    @Id
    private Long id;
    private Date creationdatetime;
    private Date modificationdatetime;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private String addinguser;
    @ManyToMany(mappedBy = "clients")
    @JsonIgnore
    private Set<Account> accounts;

}
