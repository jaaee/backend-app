package com.MCXP.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Role extends BaseEntity {

    @Id
    private Long id ;

    @Column(name="name", nullable = false)
    @NotBlank
    private String name;
}
