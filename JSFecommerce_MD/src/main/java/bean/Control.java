/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Daniel Gomez
 */
public class Control {

    public Control() {
    }
    
    private String nameUser;

    /**
     * Get the value of nameUser
     *
     * @return the value of nameUser
     */
    public String getNameUser() {
        return nameUser;
    }

    /**
     * Set the value of nameUser
     *
     * @param nameUser new value of nameUser
     */
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    private int idUser;

    /**
     * Get the value of idUser
     *
     * @return the value of idUser
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Set the value of idUser
     *
     * @param idUser new value of idUser
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    private int level;

    /**
     * Get the value of level
     *
     * @return the value of level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set the value of level
     *
     * @param level new value of level
     */
    public void setLevel(int level) {
        this.level = level;
    }

}
