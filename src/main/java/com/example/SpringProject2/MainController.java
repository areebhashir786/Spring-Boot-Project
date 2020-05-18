package com.example.SpringProject2;

//package com.springboot.Controller;

import com.example.SpringProject2.User;
import com.example.SpringProject2.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author areeb
 */

@RestController
@RequestMapping(path="/user")
public class MainController 
{
    @Autowired
    UserService service;
    Optional<User> mod;
    Date date=new Date();
    
     @RequestMapping("/")
    public String WelcomeScreen() 
    {
        return "Welcome to User Authentication and Login System";
    }
    
    
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody List<String> list) 
    {
        Optional<User> u;
        System.out.println(list.get(0));
        
       if(service.Exist(list.get(0)))
       {
           return "User alredy exist";
       }
       else
       {
        User model = service.createUser(list);
        return "\n"+ model.getName() +" You Signup Successfully";
       }
        
    }
       
    @GetMapping("/list")
    public List<String> getUsers() {
        
        List <User> UL = service.getUser();
        List<String> list = new ArrayList<>();
        
        for (User model : UL) {
           list.add(model.getName()); 
        }
        
        return list;
           
    }
    
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String loginUser(@RequestBody List<String> UL) {
        
        Optional<User> UM;
        System.out.println(UL.get(0));
        
       if(service.Exist(UL.get(0)))
       {
           UM = service.getUser(UL.get(0));
           
           if(UM.get().getPassword().equals(UL.get(1)))
           {
              mod=UM;
              System.out.println("\t\tLog IN Status");
            String detail =  "\nWelcome " + UM.get().getName() + "\n You Login Successfully";
                return detail;
           }
           
           else
           {
               return "Email or Password is incorrect";
           }
           
       }
       else{
           return "User doesn't exist";
       }
    }
    
    
    @DeleteMapping(path="/{id}")
    public String delete(@PathVariable String id)
    {
        System.out.println(id);
        if(service.Exist(id))
       {
           service.delete(id);
           return "User deleted successfully";
       }
       else
        {
           return "User doesn't exist";
       }
    }
    
    @PutMapping("/login/update")
    @ResponseStatus(HttpStatus.CREATED)
    public String updateUser(@RequestBody List<String> UL)
    {   
       if(service.Exist(UL.get(0)))
       {
           service.update(UL.get(0), UL.get(1), UL.get(2), UL.get(3), UL.get(4));
           return "Updated Successfully";
       }
       else{
           return "User Not Exist";
       }
        
    }
    
    @PutMapping("/login/updatePassword")
    @ResponseStatus(HttpStatus.CREATED)
    public String PasswordUpdate(@RequestBody List<String> UL)
    {
       Optional<User> UM;
        
       if(service.Exist(UL.get(0)))
       {
            UM = service.getUser(UL.get(0));
           
           if(UM.get().getPassword().equals(UL.get(1)))
           {
               
               return "Password is same";
           }
           else{
               service.UPass(UL.get(0),UL.get(1));
               return "Updated Successfully";
           
       }
       }
       else{
           return "User doesn't exist";
       }
        
    }
    
    @RequestMapping("/login/profile_page")
    public String userDetail()
    {
        if(mod!=null)
        {
               String info =  "User Name  : " + mod.get().getName() + "\n" +
                              "User Email : " +mod.get().getEmail()+ "\n" +
                              "Gender     : " + mod.get().getGender()+ "\n" +
                              "D.O.B      : " + mod.get().getDob()+ "\n"+
                              "Date       : " + date.toString();
                return info;
        }
        else
        {
            return "Error";
        }
    }
    
     @RequestMapping("/login/logout")
    public String logout()
    {
        if(mod!=null)
        {
       System.out.println("Log Out");
               String info =  "\n" + mod.get().getName() + " You Log out Successfully";
               
               mod=null;
               return info;
        }
        else
        {
            return "No user present";
        }           
    }  
}
