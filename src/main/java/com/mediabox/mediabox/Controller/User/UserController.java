package com.mediabox.mediabox.Controller.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mediabox.mediabox.Models.User.User;
import com.mediabox.mediabox.ServiceInterface.User.UserServices;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/api/findUserbyid")
    public User FindUserbyId(@RequestHeader("Authorization") String jwt) {
        User user = userServices.findUserBYjwt(jwt);

        return userServices.FindUserById(user.getId());

    }

    @GetMapping("/findUserbyemail/{email}")
    public User getMethodName(@PathVariable String email) {
        return userServices.findUserByEmail(email);

    }

    @PutMapping("/api/updatepassword/")
    public String updateUserPassword(@RequestHeader("Authorization") String jwt, @RequestBody String entity)
            throws Exception {
        try {
            User user = userServices.findUserBYjwt(jwt);
            userServices.updatePassword(user.getId(), entity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return "User  password updated successfully";
    }

    @DeleteMapping("/api/delete-user/")
    public String deleteUser(@RequestHeader("Authorization") String jwt) throws Exception {
        try {
            User user = userServices.findUserBYjwt(jwt);
            return userServices.deleteUser(user.getId());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/api/follow/{followingUserId}")
    public String followHandler(@RequestHeader("Authorization") String jwt, @PathVariable Long followingUserId) {
        try {
            User user = userServices.findUserBYjwt(jwt);

            userServices.FollowUser(user.getId(), followingUserId);
            return "User with id " + user.getId() + " followed user with id " + followingUserId;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/api/unfollow/{unfollowingUserId}")
    public String unfollowHandler(@RequestHeader("Authorization") String jwt, @PathVariable Long unfollowingUserId) {
        try {
            User user = userServices.findUserBYjwt(jwt);

            userServices.UnfollowUser(user.getId(), unfollowingUserId);
            return "User with id " + user.getId() + " unfollowed user with id " + unfollowingUserId;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/api/searchuser")
    public List<User> SearchUser(@RequestParam("query") String query) {
        try {
            return userServices.SearchUsers(query);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("User not found: " + e.getMessage());
        }
    }

    @GetMapping("/api/Profile")
    public User getUserfromToken(@RequestHeader("Authorization") String jwt) {

        User user = userServices.findUserBYjwt(jwt);

        return user;
    }

}
