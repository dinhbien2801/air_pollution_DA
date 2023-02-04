package com.example.da_ari_pollution.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "`co_pollution`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class COPollution implements Serializable {
    private static final long serialVersionUID = -6018008248352337169L;

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
