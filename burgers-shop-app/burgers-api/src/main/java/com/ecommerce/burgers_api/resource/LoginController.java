package com.ecommerce.burgers_api.resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/login", produces = "application/json")
// @CrossOrigin(origins = "http://localhost:8080")
public class LoginController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(IngredientController.class);

    @RequestMapping(value = "/oauth2/code/{codeId}", method = RequestMethod.GET)
    public ResponseEntity<String> showEmployees(@RequestParam("code") String code) throws JsonProcessingException, IOException {
        ResponseEntity<String> response = null;
        log.info("Authorization Ccode------" + code);

        RestTemplate restTemplate = new RestTemplate();

        String credentials = "Ov23liFpUOo2O8lyTbjZ:23d506b91b6283c2eac0f6d56001b2842cbaac29";
        String encodedCredentials = new String(Base64.getEncoder().encode(credentials.getBytes()));

        // Set up the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // Set content type to
                                                                       // application/x-www-form-urlencoded
        headers.add("Authorization", "Basic " + encodedCredentials); // Use encoded credentials

        // Create a map for the form data
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code); // Assuming `code` is defined elsewhere
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", "http://localhost:8082/login/oauth2/code/Ov23liFpUOo2O8lyTbjZ");

        // Create the request entity with the headers and form data
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        // Define the access token URL
        String access_token_url = "http://localhost:8082/oauth2/token";

        response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);

        log.info("Access Token Response ---------" + response.getBody());

        return new ResponseEntity<String>( response.getBody(), HttpStatus.OK);
    }
}
