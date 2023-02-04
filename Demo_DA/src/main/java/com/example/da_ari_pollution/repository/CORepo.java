package com.example.da_ari_pollution.repository;

import com.example.da_ari_pollution.entity.COPollution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CORepo extends JpaRepository<COPollution,Long> {
}
