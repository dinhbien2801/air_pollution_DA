package com.example.da_ari_pollution.service.serviceImpl;

import com.example.da_ari_pollution.common.AESUtil;
import com.example.da_ari_pollution.entity.Token;
import com.example.da_ari_pollution.repository.TokenRepo;
import com.example.da_ari_pollution.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepo repository;

    @Value("${aes.secretKey}")
    private String aesKey;

    @Override
    public List<Token> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Token> saveAll(List<Token> tokens) {
        return repository.saveAll(tokens);
    }

    @Override
    public Optional<Token> save(Token token) {
        return Optional.of(repository.save(token));
    }

    @Override
    public String getCurrentToken() {
        return AESUtil.decrypt(repository.findAll().get(0).getValue(), aesKey);
    }
}
