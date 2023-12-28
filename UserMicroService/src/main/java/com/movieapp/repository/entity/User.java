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
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    @Builder.Default
    private ERole role=ERole.GUEST;
    @Builder.Default
    private EStatus status = EStatus.PASSIVE;
}
