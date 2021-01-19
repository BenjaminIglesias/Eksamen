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
public class ActivityDTO {
    private long id;
    private LocalDate localDate;
    private String exerciseType;
    private LocalTime timeOfDay;
    private double duration;
    private double distance;
    private String comment;
    private CityInfoDTO cityInfo;
    private WeatherInfoDTO weatherInfo;

    public ActivityDTO(Activity activity) {
        this.exerciseType = activity.getExerciseType();
        this.duration = activity.getDuration();
        this.distance = activity.getDistance();
        this.comment = activity.getComment();
        this.cityInfo = new CityInfoDTO(activity.getCityInfo());
        this.weatherInfo = new WeatherInfoDTO(activity.getWeatherInfo());
        this.id = activity.getId();
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public LocalTime getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(LocalTime timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CityInfoDTO getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfoDTO cityInfo) {
        this.cityInfo = cityInfo;
    }

    public WeatherInfoDTO getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherInfoDTO weatherInfo) {
        this.weatherInfo = weatherInfo;
    }
    

}
