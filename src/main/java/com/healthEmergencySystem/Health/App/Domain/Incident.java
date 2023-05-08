package com.healthEmergencySystem.Health.App.Domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
public class Incident {

    @GeneratedValue
    @Id
    private Long id;
    @NotBlank(message = "Status is mandatory")
    private String status;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date=new Date();
    private String registeredBy;

}
