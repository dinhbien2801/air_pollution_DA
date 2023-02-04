package com.example.da_ari_pollution.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "`tmp`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tmp {
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
