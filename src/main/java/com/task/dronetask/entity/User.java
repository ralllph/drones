package com.task.dronetask.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "users")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="username",nullable = false, unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

}
