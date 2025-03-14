package com.rachana.EcomUserService.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.jwk.RSAKey;

import org.junit.jupiter.api.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    @Order(1)
   public SecurityFilterChain  autherizationServerSecurityFilterChain(HttpSecurity http) throws Exception {

        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
            http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                    .oidc(Customizer.withDefaults());

            http

                    //redirected to login page when not authenticated from the
                    //authorization endpoint
                    .exceptionHandling((exception) -> exception

                            .defaultAuthenticationEntryPointFor(
                                    new LoginUrlAuthenticationEntryPoint("/login"),
                                    new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                            ))

                    //  accept access tokens for user Info and/or Client Registration
                    .oauth2ResourceServer((resourceServer) -> resourceServer
                            .jwt(Customizer.withDefaults()));
            return http.build();
        }


//        @Bean
//        @Order(2)
//        public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
//            throws  Exception{
//                  http
//                        .authorizeHttpRequests((autherize)->autherize.anyRequest().authenticated())
//                        .formLogin(Customizer.withDefaults());
//                return http.build();
//            }


//            @Bean
//
//            public RegisteredClientRepository registeredClientRepository(){
//                     RegisteredClient oidcClient =RegisteredClient.withId(UUID.randomUUID().toString())
//                    .clientId("productService")
//                    .clientSecret(bCryptPasswordEncoder.encode("ILoveMySelf"))
//                    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                    .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                    .redirectUri("http://127.0.0.1:8080/login/oauth2/code/oidc.client")
//
//                    .postLogoutRedirectUri("http://127.0.0.1:8080/")
//                    .scope(OidcScopes.OPENID)
//                    .scope(OidcScopes.PROFILE)
//                    .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                    .build();
//            return new InMemoryRegisteredClientRepository(oidcClient);
//
//       }

@Bean
    public JWKSource<SecurityContext> jwkSource(){
        KeyPair kayPair=generateRsaKey();
    RSAPublicKey publicKey=(RSAPublicKey) kayPair.getPublic();
    RSAPrivateKey privateKey=(RSAPrivateKey) kayPair.getPrivate();
    RSAKey rsaKey=new RSAKey.Builder(publicKey)
            .privateKey(privateKey)
            .keyID(UUID.randomUUID().toString())
            .build();
    JWKSet jwkSet=new JWKSet(rsaKey);
    return new ImmutableJWKSet<>(jwkSet);
}

private  static  KeyPair generateRsaKey(){
        KeyPair keyPair;
        try{
            KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair=keyPairGenerator.generateKeyPair();
        }
        catch (Exception e){
            throw new IllegalStateException(e);
        }
        return keyPair;
}



}
