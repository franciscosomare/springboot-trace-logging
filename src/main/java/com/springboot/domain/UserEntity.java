package com.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_id_generator")
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Text cannot be empty")
    private String text;
}
