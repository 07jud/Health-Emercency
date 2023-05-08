package com.healthEmergencySystem.Health.App.Domain;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usertable")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private Boolean active;
    @NotEmpty(message = "Please choose a password")
    @NotNull
    @Size(min=7, message = "Password should be at least 7 characters")
    private String password;
    private String roles;
    @Column(unique = true)
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Invalid email")
    @NotNull
    private String email;
    @NotEmpty(message = "First Name should not be empty")
    private String firstName;
    @NotEmpty(message = "Last Name should not be empty")
    private String lastName;
    @CreationTimestamp
    private LocalDateTime createDateTime; 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;


    
}