package com.SpringCourse.WebServiceSpring.User;

import com.SpringCourse.WebServiceSpring.Currency.Currency;
import com.SpringCourse.WebServiceSpring.Currency.CurrencyRepository;
import com.SpringCourse.WebServiceSpring.Currency.CurrencyType;
import com.SpringCourse.WebServiceSpring.Exception.CurrencyNotFoundException;
import com.SpringCourse.WebServiceSpring.Exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    public UserController(CurrencyRepository currencyRepository, UserRepository userRepository) {
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;

        User user1 = new User(1,"Mateusz","Kuzera", "pass", "qzera");
        User user2 = new User(2,"Mateusz2","Kuzera2", "pass", "qzera2");
        User user3 = new User(3,"Mateusz3","Kuzera3", "pass", "qzera3");

        Currency currency1 = new Currency(1, CurrencyType.PLN,30.0);
        Currency currency2 = new Currency(2,CurrencyType.GBP,20.0);
        Currency currency3 = new Currency(3,CurrencyType.USD,300.0);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        currency1.setUser(user1);
        currency2.setUser(user1);
        currency3.setUser(user2);

        currencyRepository.save(currency1);
        currencyRepository.save(currency2);
        currencyRepository.save(currency3);

    }

    private CurrencyRepository currencyRepository;

    private UserRepository userRepository;


    @GetMapping(value = "/users/{user}")
    public User getUserRequest(@PathVariable Integer userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found " + userId));
        return user;
    }
    @GetMapping(value = "/users")
    public List<User> getUsersRequest(){
        List<User> all = userRepository.findAll();
        return all;
    }

    @PostMapping(value = "/users/add")
    public ResponseEntity<User> postUserRequest(@RequestBody User user){
       userRepository.save(user);
       URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/users/{id}").buildAndExpand(user.getId()).toUri();
       return ResponseEntity.created(location).build();
    }
    @DeleteMapping(value = "users/{userId}/delete")
    public User deleteUserRequest(@PathVariable Integer userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found: id " + userId));
        userRepository.delete(user);
        return user;
    }

    @PostMapping(value = "/users/{userId}/currencies/add")
    public Currency addCurrency(@PathVariable Integer userId, @RequestBody Currency currency){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found: id " + userId));
        currency.setUser(user);
        return currencyRepository.save(currency);
    }

    @GetMapping(value = "/users/{userId}/currencies")
    public List<Currency> getCurrencies(@PathVariable Integer userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found: id " + userId));
        return currencyRepository.findAllByUser(user);
    }
    @DeleteMapping(value = "/users/{userId}/currencies/{currencyId}/delete")
    public void deleteCurrencies(@PathVariable Integer userId, @PathVariable Integer currencyId){
        Currency currency = currencyRepository.findById(currencyId).orElseThrow(() -> new IllegalArgumentException("Currency not found " + currencyId));
        currencyRepository.delete(currency);
    }

    @PutMapping(value = "/users/{userId}/currencies/{currencyId}/put")
    public void editCurrenciesValue(@PathVariable Integer userId, @PathVariable Integer currencyId, @RequestBody Currency currency){

        Currency currency2 = currencyRepository.findById(currencyId).orElseThrow(() -> new CurrencyNotFoundException("Currency not found " + currencyId));
        currency2.setValue(currency.getVal());
    }






}
