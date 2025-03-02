package com.rachana.EcomUserService.security.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Client {


        @Id
        private String id;

        private String clientId;
        private Instant clientIdIssuedAt;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String clientSecret;

        private Instant clientSecretExpiresAt;
        private String clientName;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String clientAuthenticationMethods;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String authorizationGrantTypes;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String redirectUris;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String postLogoutRedirectUris;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String scopes;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String clientSettings;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String tokenSettings;
    }


