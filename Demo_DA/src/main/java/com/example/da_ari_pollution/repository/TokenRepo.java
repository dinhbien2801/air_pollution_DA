package com.example.da_ari_pollution.repository;

import com.example.da_ari_pollution.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token,Long> {
}
