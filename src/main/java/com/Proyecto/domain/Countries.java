/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.domain;

/**
 *
 * @author jp09f
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;


@Data
@Entity
@Table(name="countries")
public class Countries implements Serializable{
    
    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="country_id")
    private String countryId;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "region_id")
    private int regionId;

    public Countries(String countryId, String countryName, int regionId) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.regionId = regionId;
    }

    public Countries() {
    }
    
}
