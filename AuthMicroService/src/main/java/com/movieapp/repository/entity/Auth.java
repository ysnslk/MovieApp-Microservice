package com.movieapp.repository.entity;

import com.movieapp.repository.enums.ERole;
import com.movieapp.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Auth extends BaseEntity {
    @Id
    private String authId;
    private String email;
    private String password;
    @Builder.Default
    private ERole role=ERole.GUEST;
    @Builder.Default
    private EStatus status = EStatus.PASSIVE;

}
