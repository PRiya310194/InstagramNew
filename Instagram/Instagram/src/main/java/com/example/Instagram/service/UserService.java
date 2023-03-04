package com.example.Instagram.service;

import com.example.Instagram.dao.UserRepository;
import com.example.Instagram.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public int saveUser(User user) {
        User userObj=userRepository.save(user);
      return userObj.getUserId();
    }
    public JSONArray getUser(String UserId){
        JSONArray userArray=new JSONArray();
        if(null!=UserId && userRepository.findById(Integer.valueOf(UserId)).isPresent() ) {
            User user = userRepository.findById(Integer.valueOf(UserId)).get();
            JSONObject userObj = setUser(user);
            userArray.put(userObj);
        }
            else{
                List<User> userList=userRepository.findAll();
                for(User user:userList){
                    JSONObject userObject=setUser(user);
                    userArray.put(userObject);

                }
            }
            return userArray;
        }
        private JSONObject setUser(User user){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("userId", user.getUserId());
            jsonObject.put("firstName",user.getFirstName());
            jsonObject.put("lastName",user.getLastName());
            jsonObject.put("age",user.getAge());
            jsonObject.put("email",user.getPhoneNumber());
            return jsonObject;
        }
        public  void updateUser(User newUser,String userId){
        if(userRepository.findById(Integer.valueOf(userId)).isPresent()){
            User user=userRepository.findById(Integer.valueOf(userId)).get();
            newUser.setUserId(user.getUserId());
            userRepository.save(newUser);
        }
        }
        public void deleteUser(int userId){
      User user=userRepository.findById(userId).get();
      userRepository.delete(user);

        }
    }

