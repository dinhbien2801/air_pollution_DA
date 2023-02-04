package com.example.da_ari_pollution.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "`so2_pollution`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SO2Pollution implements Serializable {
    private static final long serialVersionUID = 6305024837463387749L;

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
