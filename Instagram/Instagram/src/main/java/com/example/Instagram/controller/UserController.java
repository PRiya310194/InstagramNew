package com.example.Instagram.controller;

import com.example.Instagram.model.User;
import com.example.Instagram.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
   private UserService userService;
    @PostMapping(value="/save-user")
    public ResponseEntity saveUser(@RequestBody String userData){
        User user=setUser(userData);
        int userId=userService.saveUser(user);
        return new ResponseEntity("User saved with id "+userId, HttpStatus.CREATED);
    }
    private User setUser(String userData){
        JSONObject jsonObject=new JSONObject(userData);  // to pass though
        User user=new User();
        user.setAge(jsonObject.getInt("age"));
        user.setFirstName(jsonObject.getString("firstName"));
        user.setLastName(jsonObject.getString("lastName"));
        user.setEmail(jsonObject.getString("email"));
        user.setPhoneNumber(jsonObject.getString("phoneNumber"));
        return user;
    }
    @GetMapping("/user")   // http://localhost:8080/user?userId=1
    public ResponseEntity<String>getUser(@Nullable@RequestParam String userId){
        JSONArray userDetails=userService.getUser(userId);
        return new ResponseEntity<>(userDetails.toString(), HttpStatus.OK);
    }
    @PutMapping(value="/user/{userId}")  // http://localhost:8080/user/2
    public ResponseEntity<String>updateUser(@PathVariable String userId,@RequestBody String userData){
        User user=setUser(userData);
        userService.updateUser(user,userId);
        return new ResponseEntity<>("user updated",HttpStatus.OK);

    }
    @DeleteMapping(value="/user/{userId}")
  public void deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
    }

}
