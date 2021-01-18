/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.User;

/**
 *
 * @author Benjamin
 */
public class UserDTO {
    private String userName;
    private UserInfoDTO userInfo;

    public UserDTO(User user){
        this.userName = user.getUserName();
        this.userInfo = new UserInfoDTO(user.getUserInfo());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserInfoDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDTO userInfo) {
        this.userInfo = userInfo;
    }
    
    
}
