package com.example.da_ari_pollution.repository;


import com.example.da_ari_pollution.entity.PM25Pollution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PM25Repo extends JpaRepository<PM25Pollution,Long> {
}
