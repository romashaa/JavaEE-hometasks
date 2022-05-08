package com.example.romanenko_dz3_javaee.controller;

import com.example.romanenko_dz3_javaee.entity.BookEntity;
import com.example.romanenko_dz3_javaee.entity.UserEntity;
import com.example.romanenko_dz3_javaee.service.BookService;
import com.example.romanenko_dz3_javaee.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@Controller
@AllArgsConstructor
public class UserController {
    @Autowired
    BookService booksService;
    @Autowired
    UserService userService;

    @GetMapping("/sign-in")
    public String signInPage(Model model) {
        model.addAttribute("user", new UserEntity());
        return "signIn";
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute UserEntity user) {
        try {
            userService.login(user.getLogin(), user.getPassword());
            return "redirect:/";
        } catch (Exception e) {
            return "signIn";
        }
    }

    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        model.addAttribute("user", new UserEntity());
        return "signup";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute UserEntity user) {
        userService.signUp(user);
        try {
            return "redirect:/sign-in";
        } catch (Exception e) {
            return "signup";
        }
    }


    @GetMapping("/get-favouriteBooks")
    public ResponseEntity<Set<BookEntity>> getFavouriteBooks(Principal principal) {
        Set<BookEntity> response;
        try {
            response = userService.getFavourites(principal.getName());
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/favouriteBooks/add")
    public ResponseEntity<String> addFavouriteBooks(@RequestParam(name = "isbn") String isbn, Principal principal) {
        Optional<UserEntity> userEntity = userService.getUserByUsername(principal.getName());
        if(userEntity.isPresent()) {
            BookEntity bookEntity = booksService.getBookByIsbn(isbn);
            if(bookEntity != null) {
                userService.addToFavourites(userEntity.get(), bookEntity);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(isbn + ": Added to favouriteBooks");
    }


}
