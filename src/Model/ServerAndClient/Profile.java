package Model.ServerAndClient;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable {
    private final String username;
    private String password;
    private String name;
    private String birthYear;
    private String phoneNumber;
    byte[] profileImage;
    //add new
    public  ArrayList<Profile>followings=new ArrayList<>();

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileimage) {
        this.profileImage = profileimage;
    }
    public Profile(String username){
        this.username =username;
    }


    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        try{
            return this.username.equals(((Profile)obj).getUserName());
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public String toString() {
        return  username ;
    }


    public String getUserName() {
        return username;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Boolean setBirthYear(String birthYear){
        if( ! isValidBirthYear(birthYear) ) return false;
        this.birthYear = birthYear;
        return true;
    }

    public int getBirthYear(){
        return Integer.parseInt(birthYear);
    }


    public int getAge(){
        return 2020 - getBirthYear();
    }

    public static boolean isValidBirthYear(String yearStr){
        try{
            int yearInt = Integer.parseInt(yearStr);
            if (yearInt > 2019 || yearInt < 1800 ) return false;
            return true;
        }
        catch(RuntimeException e){
            return false;
        }
    }

}
