/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.CityInfo;

/**
 *
 * @author Benjamin
 */
public class CityInfoDTO {
    private String name;
    private String geocoordinates;
    private String municipality;
    private String population;

    public CityInfoDTO(CityInfo cityInfo) {
        this.name = cityInfo.getName();
        this.geocoordinates = cityInfo.getGeocoordinates();
        this.municipality = cityInfo.getMunicipality();
        this.population = cityInfo.getPopulation();
    }

    public CityInfoDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeocoordinates() {
        return geocoordinates;
    }

    public void setGeocoordinates(String geocoordinates) {
        this.geocoordinates = geocoordinates;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
    
         
}
