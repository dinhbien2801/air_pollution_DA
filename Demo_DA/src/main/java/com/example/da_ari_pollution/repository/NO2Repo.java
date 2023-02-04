package com.example.da_ari_pollution.repository;

import com.example.da_ari_pollution.entity.NO2Pollution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NO2Repo extends JpaRepository<NO2Pollution,Long> {
}
