package com.ecommerce.burgers_restclient.services.impl;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.burgers_models.models.Ingredient;
import com.ecommerce.burgers_restclient.services.RestTemplateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestIngredientServiceImpl implements RestTemplateService {

    @Autowired
    private RestTemplate rest;

    // @Autowired
    // public RestIngredientServiceImpl(){
    //     this.rest = new RestTemplate();
    // }

    @Override
    public Ingredient getIngredientById(String ingredientId) {
        return rest.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return rest.exchange("http://localhost:8080/ingredients",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ingredient>>() {
                })
                .getBody();
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        rest.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {
        rest.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        return rest.postForObject("http://localhost:8080/ingredients", ingredient, Ingredient.class);
    }

    @Override
    public URI createIngredientFromURI(Ingredient ingredient) {
        return rest.postForLocation("http://localhost:8080/ingredients", ingredient, Ingredient.class);
    }

    @Override
    public Ingredient createIngredientByResponse(Ingredient ingredient) {
        ResponseEntity<Ingredient> responseEntity = rest.postForEntity("http://localhost:8080/ingredients", ingredient,
                Ingredient.class);
        log.info("New resource created at " + responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
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
