package com.example.romanenko_dz3_javaee.service;

import com.example.romanenko_dz3_javaee.entity.BookEntity;
import com.example.romanenko_dz3_javaee.entity.UserEntity;
import com.example.romanenko_dz3_javaee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void signUp(UserEntity user) {
//        Optional<UserEntity> userByLogin = userRepository.findByLogin(user.getLogin());
//
//        if (nonNull(userByLogin)) {
//            throw new IllegalArgumentException();
//        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPermissions(new ArrayList<>());

        userRepository.save(user);
    }
    @Transactional
    public void login(String login, String password) {
        Optional<UserEntity> user = userRepository.findByLogin(login);
        if (user.isEmpty() || !bCryptPasswordEncoder.matches(password, user.get().getPassword())) {
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public Optional<UserEntity> getUserByUsername(String login) {
        return userRepository.findFavouritesByLogin(login);
    }

    @Transactional
    public UserEntity addToFavourites(UserEntity userEntity, BookEntity bookEntity) {
        Set<BookEntity> books = userEntity.getFavouriteBooks();
        books.add(bookEntity);
        userEntity.setFavouriteBooks(books);
        return userRepository.saveAndFlush(userEntity);
    }


    public Set<BookEntity> getFavourites(String username) {
        Optional<UserEntity> oUser = userRepository.findFavouritesByLogin(username);
        if (oUser.isPresent()) {
            UserEntity user = oUser.get();
            return user.getFavouriteBooks();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Transactional
    public boolean isUserFavourite(UserEntity user, BookEntity book) {
        Set<BookEntity> books = user.getFavouriteBooks();
        return books.contains(book);
    }
}