package com.MCXP.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "user_sequence_gen")
    @SequenceGenerator(name = "user_sequence_gen",
    sequenceName = "user_seq",
    allocationSize = 1)
    private Long id;

    @Column(name="first_name",nullable = false)
    @NotBlank
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email",nullable = false, unique = true)
    @NotBlank
    private String email;

    @Column(name="user_name",nullable = false, unique = true)
    @NotBlank
    private String userName;

    @Column(name="password_hash")
    @NotBlank
    private String passwordHash;

    @Column(name="phone_number")
    private String phoneNumber;


    @ManyToOne
    @JoinColumn(name="role_id",nullable = false)
    private Role role;



}
