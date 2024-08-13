package com.ecommerce.burgers_shop.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
// import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
// import com.ecommerce.burgers_shop.config.RsaKeyConfigProperties;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // (proxyBeanMethods = false)
@EnableWebSecurity
public class AuthorizationServerConfig {

        // @Autowired
        // private RsaKeyConfigProperties rsaKeyConfigProperties;

        @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
        String jwkSetUri;

        @Bean
        @Order(1)
        public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
                        throws Exception {
                OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
                http
                                .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                                .oidc(Customizer.withDefaults()); // Enable OpenID Connect 1.0
                http
                                // Redirect to the login page when not authenticated from the
                                // authorization endpoint
                                .exceptionHandling((exceptions) -> exceptions
                                                .defaultAuthenticationEntryPointFor(
                                                                new LoginUrlAuthenticationEntryPoint("/login"),
                                                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)))
                                // Accept access tokens for User Info and/or Client Registration
                                // .oauth2ResourceServer((resourceServer) ->
                                // resourceServer.jwt(Customizer.withDefaults()));
                                .oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults()));

                return http.build();
        }
        // @Bean
        // @Order(Ordered.HIGHEST_PRECEDENCE)
        // public SecurityFilterChain
        // authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        // OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        // return http
        // .formLogin(Customizer.withDefaults())
        // .build();
        // }

        @Bean
        @Order(2)
        public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
                http
                        .authorizeHttpRequests((authorize) -> authorize
                                        .requestMatchers(HttpMethod.GET, "/api/ingredients/message")
                                        .hasAuthority("SCOPE_writeIngredients")
                                        .requestMatchers(HttpMethod.GET, "/api/ingredients")
                                        .hasAuthority("SCOPE_writeIngredients")
                                        .requestMatchers(HttpMethod.POST, "/api/ingredients")
                                        .hasAuthority("SCOPE_writeIngredients")
                                        .requestMatchers(HttpMethod.DELETE, "/api/ingredients")
                                        .hasAuthority("SCOPE_deleteIngredients")
                                        .anyRequest().authenticated())
                        .oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults()))
                        // .oauth2Login(oauth2Login -> oauth2Login
                        //                 .loginPage("/oauth2/authorization/taco-admin-client"))
                        // .oauth2Login(withDefaults())
                        // .oauth2Client(withDefaults())
                        // Form login handles the redirect to the login page from the
                        // authorization server filter chain
                        .formLogin(withDefaults());

                return http.build();
        }

        @Bean
        public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {
                RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                                .clientId("Ov23liFpUOo2O8lyTbjZ")
                                .clientSecret(passwordEncoder.encode("23d506b91b6283c2eac0f6d56001b2842cbaac29"))
                                .clientAuthenticationMethod(
                                                ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                                .redirectUri(
                                                "http://localhost:8080/login/oauth2/code/Ov23liFpUOo2O8lyTbjZ")
                                .postLogoutRedirectUri("http://127.0.0.1:8080")
                                .scope("writeIngredients")
                                .scope("deleteIngredients")
                                .scope(OidcScopes.OPENID)
                                .scope(OidcScopes.PROFILE)
                                .clientSettings(
                                                ClientSettings.builder().requireAuthorizationConsent(true).build())
                                .build();
                return new InMemoryRegisteredClientRepository(registeredClient);
        }

        @Bean
        public JWKSource<SecurityContext> jwkSource() {
                KeyPair keyPair = generateRsaKey();
                RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
                RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
                JWK jwk = new RSAKey.Builder(publicKey)
                                .privateKey(privateKey)
                                .keyID(UUID.randomUUID().toString())
                                .build();
                JWKSet jwkSet = new JWKSet(jwk);
                return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet); // new ImmutableJWKSet<>(jwkSet);
        }

        // @Bean
        // JwtEncoder jwtEncoder() {
        // JWK jwk = new
        // RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
        // JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        // return new NimbusJwtEncoder(jwks);
        // }

        // private static RSAKey generateRsa() throws NoSuchAlgorithmException {
        // KeyPair keyPair = generateRsaKey();
        // RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // return new RSAKey.Builder(publicKey)
        // .privateKey(privateKey)
        // .keyID(UUID.randomUUID().toString())
        // .build();
        // }

        private static KeyPair generateRsaKey() {
                KeyPair keyPair;
                try {
                        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                        keyPairGenerator.initialize(2048);
                        keyPair = keyPairGenerator.generateKeyPair();
                } catch (Exception ex) {
                        throw new IllegalStateException(ex);
                }
                return keyPair;
        }

        // @Bean
        // JwtEncoder jwtEncoder() {
        // JWK jwk = new
        // RSAKey.Builder(rsaKeyConfigProperties.publicKey()).privateKey(rsaKeyConfigProperties.privateKey()).build();

        // JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        // return new NimbusJwtEncoder(jwks);
        // }

        // @Bean
        // public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        // return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
        // }

        @Bean
        JwtDecoder jwtDecoder() {
                return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
        }

        @Bean
        public AuthorizationServerSettings authorizationServerSettings() {
                return AuthorizationServerSettings.builder().build();
        }

}
