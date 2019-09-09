package com.clairvoyantsoft.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="userProfile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailId;

    private String firstname;

    private String lastname;

    private String gender;

    private String phoneNumber;
}
