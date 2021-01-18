/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.CityInfoDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Benjamin
 */
@Entity
public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String geocoordinates;
    private String municipality;
    private String population;
    @OneToMany(mappedBy = "cityInfo",cascade = CascadeType.ALL)
    private List<Activity> activities  = new ArrayList<>(); 

    public CityInfo() {
    }

    public CityInfo(CityInfoDTO cityInfoDTO) {
        this.name = cityInfoDTO.getName();
        this.geocoordinates = cityInfoDTO.getGeocoordinates();
        this.municipality = cityInfoDTO.getMunicipality();
        this.population = cityInfoDTO.getPopulation();
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

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity){
    this.activities.add(activity);
    if (activity != null){
    activity.setCityInfo(this);
    }
    }
    
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CityInfo)) {
            return false;
        }
        CityInfo other = (CityInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CityInfo[ id=" + id + " ]";
    }
    
}
