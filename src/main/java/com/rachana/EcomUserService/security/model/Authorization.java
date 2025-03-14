package com.rachana.EcomUserService.security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name="User_Authorization")
public class Authorization {


        @Id
        @Column
        private String id;

        private String registeredClientId;
        private String principalName;
        private String authorizationGrantType;

        @Lob  // Change large strings to TEXT
        @Column(columnDefinition = "TEXT")
        private String authorizedScopes;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String attributes;

        @Column(length = 500) // Keep small fields as VARCHAR
        private String state;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String authorizationCodeValue;

        private Instant authorizationCodeIssuedAt;
        private Instant authorizationCodeExpiresAt;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String authorizationCodeMetadata;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String accessTokenValue;

        private Instant accessTokenIssuedAt;
        private Instant accessTokenExpiresAt;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String accessTokenMetadata;

        private String accessTokenType;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String accessTokenScopes;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String refreshTokenValue;

        private Instant refreshTokenIssuedAt;
        private Instant refreshTokenExpiresAt;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String refreshTokenMetadata;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String oidcIdTokenValue;

        private Instant oidcIdTokenIssuedAt;
        private Instant oidcIdTokenExpiresAt;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String oidcIdTokenMetadata;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String oidcIdTokenClaims;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String userCodeValue;

        private Instant userCodeIssuedAt;
        private Instant userCodeExpiresAt;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String userCodeMetadata;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String deviceCodeValue;

        private Instant deviceCodeIssuedAt;
        private Instant deviceCodeExpiresAt;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String deviceCodeMetadata;
    }


