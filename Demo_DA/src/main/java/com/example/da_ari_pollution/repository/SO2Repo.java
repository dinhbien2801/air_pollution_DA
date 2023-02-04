package com.example.da_ari_pollution.repository;


import com.example.da_ari_pollution.entity.SO2Pollution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SO2Repo extends JpaRepository<SO2Pollution,Long> {
}
