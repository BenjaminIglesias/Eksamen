/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import dto.CityInfoDTO;
import dto.WeatherInfoDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import utils.HttpUtils;
import java.lang.reflect.Type;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author Benjamin
 */
public class FetchFacade {
   
      private static EntityManagerFactory emf;
      private static FetchFacade instance;
      private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    
   
    public FetchFacade(){}
      /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static FetchFacade getFetchFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FetchFacade();
        }
        return instance;
    }

     public CityInfoDTO getCityInfo(String name) throws JSONException, IOException{
         
         String json = HttpUtils.fetchData("https://dawa.aws.dk/steder?hovedtype=Bebyggelse&undertype=by&prim%C3%A6rtnavn=" + name);
      StringBuilder sb = new StringBuilder(json); 
      StringBuilder sb2 = new StringBuilder(); 
     
      String objectName = "{\"cityInfo\":";       
      sb.replace(json.length() - 1, json.length(),"}");
                      sb.deleteCharAt(0); 
      sb2.append(objectName);
      sb2.append(sb.toString());

         System.out.println(sb2.toString());
         JSONObject obj = new JSONObject(sb2.toString());
             CityInfoDTO cityInfo = new CityInfoDTO();
             String cityName = obj.getJSONObject("cityInfo").getString("primÃ¦rtnavn");
             cityInfo.setName(cityName);
             String population = obj.getJSONObject("cityInfo").getJSONObject("egenskaber").getString("indbyggerantal");
             cityInfo.setPopulation(population);
             String lat = obj.getJSONObject("cityInfo").getJSONArray("visueltcenter").getString(0);
             String lon = obj.getJSONObject("cityInfo").getJSONArray("visueltcenter").getString(1);
             String geo = lat + ", " + lon;
             cityInfo.setGeocoordinates(geo);
             String municipality = obj.getJSONObject("cityInfo").getJSONArray("kommuner").getJSONObject(0).getString("navn");
             cityInfo.setMunicipality(municipality);
             
     return cityInfo;
    }
    
     
    
     public WeatherInfoDTO getWeatherInfo(String name) throws JSONException, IOException{
         
         String json = HttpUtils.fetchData("https://vejr.eu/api.php?location=" +name +"&degree=C");
         
         StringBuilder sb = new StringBuilder(json); 
      StringBuilder sb2 = new StringBuilder(); 
     
      String objectName = "{\"weatherInfo\":{";       
      sb.replace(json.length() - 1, json.length(),"}}");
                      sb.deleteCharAt(0); 
      sb2.append(objectName);
      sb2.append(sb.toString());
         
      JSONObject obj = new JSONObject(sb2.toString());
        WeatherInfoDTO weatherInfo = new WeatherInfoDTO();
        String temp = obj.getJSONObject("weatherInfo").getJSONObject("CurrentData").getString("temperature");
        weatherInfo.setTemperature(temp);
        String skyText = obj.getJSONObject("weatherInfo").getJSONObject("CurrentData").getString("skyText");
        weatherInfo.setSkyText(skyText);
        String humidity =obj.getJSONObject("weatherInfo").getJSONObject("CurrentData").getString("humidity");
        weatherInfo.setHumidity(humidity);
        String windText = obj.getJSONObject("weatherInfo").getJSONObject("CurrentData").getString("windText");     
        weatherInfo.setWindText(windText);
     return weatherInfo;
    }
    
     
     
     
}
