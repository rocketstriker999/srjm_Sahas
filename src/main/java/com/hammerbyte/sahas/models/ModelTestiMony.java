


package com.hammerbyte.sahas.models;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "testimonies")
public class ModelTestiMony {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testiMonyId;
    private String testiMony;
   
    @Column(updatable = true)
    @CreationTimestamp
    private Date testiMonyDate;

    

}
