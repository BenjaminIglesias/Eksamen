/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.UserInfo;

/**
 *
 * @author Benjamin
 */
public class UserInfoDTO {
    private String name;
    private double age;
    private double weight;

    public UserInfoDTO(UserInfo userInfo) {
        this.name = userInfo.getName();
        this.age = userInfo.getAge();
        this.weight = userInfo.getWeight();
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
   
    
}
