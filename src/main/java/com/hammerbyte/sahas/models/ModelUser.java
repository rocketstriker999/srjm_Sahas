package com.hammerbyte.sahas.models;

import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hammerbyte.sahas.enums.EnumUserRole;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

//keep class name AppUser - Conflicts with User Class From Spring Security

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class ModelUser{
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Column(unique = true,nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private EnumUserRole userRole=EnumUserRole.USER;

    @Column(updatable = false)
    @CreationTimestamp  
    private Date createdAt;

}
