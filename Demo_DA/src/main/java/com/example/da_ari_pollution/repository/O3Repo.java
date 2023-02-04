package com.example.da_ari_pollution.repository;


import com.example.da_ari_pollution.entity.O3Pollution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface O3Repo extends JpaRepository<O3Pollution,Long> {
}
