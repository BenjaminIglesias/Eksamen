/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Activity;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Benjamin
 */
public class CombinedActivityDTO {
    private LocalDate localDate;
    private String exerciseType;
    private LocalTime timeOfDay;
    private double duration;
    private double distance;
    private String comment;
    private String name;
    private String geocoordinates;
    private String municipality;
    private String population;
    private String temperature;
    private String skyText;
    private String humidity;
    private String windText;

    public CombinedActivityDTO(Activity activity) {
        this.localDate = activity.getLocalDate();
        this.exerciseType = activity.getExerciseType();
        this.timeOfDay = activity.getTimeOfDay();
        this.duration = activity.getDuration();
        this.distance = activity.getDistance();
        this.comment = activity.getComment();
        this.name = activity.getCityInfo().getName();
        this.geocoordinates = activity.getCityInfo().getGeocoordinates();
        this.municipality = activity.getCityInfo().getMunicipality();
        this.population = activity.getCityInfo().getPopulation();
        this.temperature = activity.getWeatherInfo().getTemperature();
        this.skyText = activity.getWeatherInfo().getSkyText();
        this.humidity = activity.getWeatherInfo().getHumidity();
        this.windText = activity.getWeatherInfo().getWindText();
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

    public String getName() {
        return name;
    }

    public String getGeocoordinates() {
        return geocoordinates;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getPopulation() {
        return population;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getSkyText() {
        return skyText;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWindText() {
        return windText;
    }


    



}
