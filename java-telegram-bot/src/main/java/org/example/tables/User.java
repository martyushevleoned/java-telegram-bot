package org.example.tables;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"User\"")
public class User {

    @Id
    @Column(name = "id", columnDefinition = "int")
    private Integer id;

    @Column(name = "username", columnDefinition = "char")
    private String username;

    @Column(name = "password", columnDefinition = "char")
    private String password;
}