package com.ecommerce.burgers_restclient.services.impl;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.burgers_models.models.Burger;
import com.ecommerce.burgers_models.models.Ingredient;
import com.ecommerce.burgers_restclient.services.RestBurgerTemplateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestBurgerServiceImpl implements RestBurgerTemplateService {

    @Autowired
    private RestTemplate rest;

    @Override
    public Burger createBurger(Burger burger) {
        return rest.postForObject("http://localhost:8080/burgers", burger, Burger.class);
    }

    // @Override
    // public Ingredient addIngredient(Ingredient ingredient) {
    // String ingredientsUrl = traverson
    // .follow("ingredients")
    // .asLink()
    // .getHref();

    // return rest.postForObject(ingredientsUrl,
    // ingredient,
    // Ingredient.class);
    // }

    // REST TEMPLATE SECURITY

    // public RestIngredientService(String accessToken) {
    //     if (accessToken != null) {
    //         this.restTemplate
    //                 .getInterceptors()
    //                 .add(getBearerTokenInterceptor(accessToken));
    //     }
    // }

    // public Iterable<Ingredient> findAll() {
    //     return Arrays.asList(restTemplate.getForObject(
    //             "http://localhost:8080/api/ingredients",
    //             Ingredient[].class));
    // }

    // public Ingredient addIngredient(Ingredient ingredient) {
    //     return restTemplate.postForObject(
    //             "http://localhost:8080/api/ingredients",
    //             ingredient,
    //             Ingredient.class);
    // }

    // private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
    //     ClientHttpRequestInterceptor interceptor = new ClientHttpRequestInterceptor() {

    //         @Override
    //         public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
    //                 throws IOException {
    //             request.getHeaders().add("Authorization", "Bearer " + accessToken);
    //             return execution.execute(request, body);
    //         }
    //     };
    //     return interceptor;
    // }

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
