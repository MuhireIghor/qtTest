package com.blogiffy.Blogiffy.models;

import com.blogiffy.Blogiffy.audits.TimestampAudit;
import com.blogiffy.Blogiffy.enums.ERole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class User extends TimestampAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "first_name",length = 100,nullable = false)
    private String firstName;
    @Column(name = "last_name",length = 100,nullable = false)
    private String lastName;
    @Email
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ERole role = ERole.COMMENTER;


}
