package com.movieapp.repository.entity;

import com.movieapp.repository.enums.ERole;
import com.movieapp.repository.enums.EStatus;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User extends BaseEntity {
    @Id
    private String userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Builder.Default
    private ERole role=ERole.GUEST;
    @Builder.Default
    private EStatus status = EStatus.PASSIVE;
    private String authId;
}
