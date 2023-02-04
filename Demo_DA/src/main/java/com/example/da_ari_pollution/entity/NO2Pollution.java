package com.example.da_ari_pollution.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "`no2_pollution`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NO2Pollution implements Serializable {
    private static final long serialVersionUID = -1221297433605317133L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @Column(name = "`long`")
    private double lon;

    @Column(name = "`lat`")
    private double lat;

    @Column(name = "`value`")
    private double value;
}
