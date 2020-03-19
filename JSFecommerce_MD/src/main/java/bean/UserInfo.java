/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;

/**
 *
 * @author Daniel Gomez
 */

public class UserInfo{

    /**
     * Creates a new instance of UserInfo
     */
    public UserInfo() {
        
    }

    public UserInfo(String name, String name2) {
        this.name = name;
        this.name2 = name2;
    }
    
    private String name;
    private String name2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
    
    
    
}
