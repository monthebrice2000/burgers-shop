package com.ecommerce.burgers_api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.burgers_models.models.Burger;
import com.ecommerce.burgers_repository.repository.BurgerRepository;

@RestController
@RequestMapping(path = "/api/burgers", produces = "application/json")
public class BurgerController {

    @Autowired
    private BurgerRepository burgerRepo;

    @GetMapping(params = "recent")
    public List<Burger> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return burgerRepo.findAll(page); // .getContent();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Burger postBurger(@RequestBody Burger burger) {
        return burgerRepo.save(burger);
    }

    // @GetMapping("/{id}")
    // public Optional<Burger> getBurgerById(@PathVariable("id") Long id) {
    //     return burgerRepo.findById(id);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Burger> getBurgerById(@PathVariable("id") Long id) {
        Optional<Burger> optBurger = burgerRepo.findById(id);
        if (optBurger.isPresent()) {
            return new ResponseEntity<>(optBurger.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
