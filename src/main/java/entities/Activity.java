/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.CityInfoDTO;
import dto.WeatherInfoDTO;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Benjamin
 */
@Entity
@NamedQuery(name = "Activity.deleteAllRows", query = "DELETE from Activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate localDate;
    private String exerciseType;
    private LocalTime timeOfDay;
    private double duration;
    private double distance;
    private String comment;
    
     @ManyToOne(cascade = CascadeType.ALL)
     User user; 
     
     @OneToOne(cascade = CascadeType.ALL)
     private WeatherInfo weatherInfo;
     @ManyToOne
     CityInfo cityInfo; 
     
     
     
     
     
    public Activity() {
    }

    public Activity(String exerciseType, double duration, double distance, String comment) {
        this.exerciseType = exerciseType;
        this.duration = duration;
        this.distance = distance;
        this.comment = comment;
        this.localDate = localDate.now(ZoneId.of("GMT+01:00"));
        this.timeOfDay = timeOfDay.now(ZoneId.of("GMT+01:00"));
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public LocalTime getTimeOfDay() {
        return timeOfDay;
    }

    public double getDuration() {
        return duration;
    }

    public double getDistance() {
        return distance;
    }

    public String getComment() {
        return comment;
    }

    public User getUser() {
        return user;
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public void setTimeOfDay(LocalTime timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

   

     
   
     
     public void setWeatherInfo(WeatherInfoDTO weatherInfoDTO) {
         WeatherInfo wi = new WeatherInfo(weatherInfoDTO);
         this.weatherInfo = wi;
        if(wi != null){
        wi.setActivity(this);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
