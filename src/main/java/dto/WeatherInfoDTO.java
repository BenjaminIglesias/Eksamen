/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.WeatherInfo;

/**
 *
 * @author Benjamin
 */
public class WeatherInfoDTO {
   private String temperature;
   private String skyText;
   private String humidity;
   private String windText;

    public WeatherInfoDTO(WeatherInfo weatherInfo) {
        this.temperature = weatherInfo.getTemperature();
        this.skyText = weatherInfo.getSkyText();
        this.humidity = weatherInfo.getHumidity();
        this.windText = weatherInfo.getWindText();
    }

    public WeatherInfoDTO() {
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSkyText() {
        return skyText;
    }

    public void setSkyText(String skyText) {
        this.skyText = skyText;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindText() {
        return windText;
    }

    public void setWindText(String windText) {
        this.windText = windText;
    }

    @Override
    public String toString() {
        return "WeatherInfoDTO{" + "temperature=" + temperature + ", skyText=" + skyText + ", humidity=" + humidity + ", windText=" + windText + '}';
    }
   
   
   
}
