package com.ecommerce.burgers_shop.services;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import com.ecommerce.burgers_shop.models.Ingredient;

public class RestIngredientService {

    @Autowired
    private RestTemplate restTemplate;

    public RestIngredientService(String accessToken) {
        if (accessToken != null) {
            this.restTemplate
                    .getInterceptors()
                    .add(getBearerTokenInterceptor(accessToken));
        }
    }

    public Iterable<Ingredient> findAll() {
        return Arrays.asList(restTemplate.getForObject(
                "http://localhost:8080/api/ingredients",
                Ingredient[].class));
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        return restTemplate.postForObject(
                "http://localhost:8080/api/ingredients",
                ingredient,
                Ingredient.class);
    }

    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
        ClientHttpRequestInterceptor interceptor = new ClientHttpRequestInterceptor() {

            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                    throws IOException {
                request.getHeaders().add("Authorization", "Bearer " + accessToken);
                return execution.execute(request, body);
            }
        };
        return interceptor;
    }

    // @Bean
    // @RequestScope
    // public IngredientService ingredientService(
    //         OAuth2AuthorizedClientService clientService) {
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     test =  authentica

    //     String accessToken = null;

    //     if (authentication.getClass()
    //             .isAssignableFrom(OAuth2AuthenticationToken.class)) {
    //         OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
    //         String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
    //         if (clientRegistrationId.equals("taco-admin-client")) {
    //             OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
    //                     clientRegistrationId, oauthToken.getName());
    //             accessToken = client.getAccessToken().getTokenValue();
    //         }
    //     }
    //     return new RestIngredientService(accessToken);
    // }
}
