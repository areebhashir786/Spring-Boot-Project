
package com.example.SpringProject2;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author areeb
 */
   
@Entity
@Table(name = "Person")
public class User implements Serializable{
    
    @Id
    private String Email;
    private String Name;
    private String Password;
    private String Gender;
    private String dob;

    public User() {
    }
   
    
     public String getEmail() {
        return Email;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public String getGender() {
        return Gender;
    }

    
    public String getDob() {
        return dob;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    
    public User( String Email, String Name,String Password, String Gender, String dob) {
        this.Email = Email;
        this.Name = Name;
        this.Password = Password;
        this.Gender = Gender;
        this.dob = dob;
    }
    
}
