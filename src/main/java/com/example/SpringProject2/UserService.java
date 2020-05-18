///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.springboot.Service;
//
package com.example.SpringProject2;

import com.example.SpringProject2.User;
import com.example.SpringProject2.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author areeb
 */

@Service
public class UserService
{
    @Autowired
    Repository repository;
    
    //Insert new user in Database
    public User createUser(List<String> u){
        System.out.println(u.get(0) + u.get(1)+u.get(2)+u.get(3)+u.get(4));
        return repository.save(new User(u.get(0),u.get(1),u.get(2),u.get(3),u.get(4)));
    }
    
    //Getting list of all Users from database
    public List<User> getUser(){
        List<User> list;
        list = repository.findAll();
        System.out.println("User " + list.get(0).getName());
        return list;
    }
    
    //check User Existence 
    public boolean Exist(String email) {
        
        if(repository.existsById(email))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //Update User Password in database
    public User UPass(String email,String password)
    {
        
        String name,gender,dob;
        Optional<User> u = repository.findById(email);
        
        
        name=u.get().getName();
        gender=u.get().getGender();
        dob=u.get().getDob();
        
        repository.delete(u.get());
        
        return repository.save(new User(email,name,password,gender,dob));

    }
    
     //Delete a user from Database
    public void delete(String email)
    {
        Optional<User> us = repository.findById(email);
        repository.delete(us.get());
    }
    
    //update user in database
    public User update(String email,String name, String password, String gender, String dob)
    {
        
        Optional<User> UM = repository.findById(email);
        repository.delete(UM.get());
        return repository.save(new User(email,name,password,gender,dob));

    }
    
   //User by email
    public Optional<User> getUser(String email)
    {
        System.out.println(email);
        Optional<User> user = repository.findById(email);
        if(user!=null)
            return user;
        else
            return null;
    }
}