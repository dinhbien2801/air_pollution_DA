package com.example.da_ari_pollution.repository;


import com.example.da_ari_pollution.entity.PM10Pollution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PM10Repo extends JpaRepository<PM10Pollution,Long> {
}
