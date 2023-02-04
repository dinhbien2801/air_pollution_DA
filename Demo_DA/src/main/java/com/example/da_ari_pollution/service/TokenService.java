package com.example.da_ari_pollution.service;

import com.example.da_ari_pollution.entity.Token;

import java.util.List;
import java.util.Optional;

public interface TokenService {

    List<Token> findAll();

    List<Token> saveAll(List<Token> tokens);

    Optional<Token> save(Token token);

    String getCurrentToken();
}
