package Model.ServerAndClient;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class contains all information about a user
 * we have an object of this class for each use
 * it implements serializable in order to be serialized
 * the equal method is based on username bcz the username is unique
 */

public class Profile implements Serializable {
    private static final long serialVersionUID = -7523282726772266610L;
    private String username;
    private String password;
    private String name;
    private String birthYear;
    private String phoneNumber;
    private String email;
    byte[] profileImage;

    public ArrayList<Profile> followings = new ArrayList<>();
    public ArrayList<Profile> followers = new ArrayList<>();
    public ArrayList<Profile> mutedUsers = new ArrayList<>();
    public ArrayList<Profile> blockedUsers = new ArrayList<>();

    public Profile(String username) {
        this.username = username;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileimage) {
        this.profileImage = profileimage;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        try {

            return this.username.equals(((Profile) obj).getUserName());

        } catch (Exception e) {

            return false;

        }
    }

    @Override
    public String toString() {
        return username;
    }

    public String getUserName() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
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

    public String getBirthYear() {
        return birthYear;
    }


}
