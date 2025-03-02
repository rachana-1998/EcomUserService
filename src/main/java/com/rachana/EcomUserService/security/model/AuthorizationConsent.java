package com.rachana.EcomUserService.security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "AuthorizationConsent")
@IdClass(AuthorizationConsent.AuthorizationConsentId.class) // Add this!
public class AuthorizationConsent {

    @Id
    private String registeredClientId;

    @Id
    private String principalName;

    @Column(length = 4000)
    private String authorities;

    // Getters and Setters

    public static class AuthorizationConsentId implements Serializable {
        private String registeredClientId;
        private String principalName;

        public AuthorizationConsentId() {}

        public AuthorizationConsentId(String registeredClientId, String principalName) {
            this.registeredClientId = registeredClientId;
            this.principalName = principalName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AuthorizationConsentId that = (AuthorizationConsentId) o;
            return registeredClientId.equals(that.registeredClientId) &&
                    principalName.equals(that.principalName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(registeredClientId, principalName);
        }
    }
}

