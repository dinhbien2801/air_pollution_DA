package com.example.da_ari_pollution.repository;

import com.example.da_ari_pollution.entity.AirPollution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirRepo extends JpaRepository<AirPollution,Long> {
}
