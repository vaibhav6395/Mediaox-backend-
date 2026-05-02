package com.mediabox.mediabox.ServiceInterface.User;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mediabox.mediabox.Models.User.User;

public interface UserServices {

    public User RegisterUser(User user);

    public User FindUserById(Long id);

    public User findUserByEmail(String email);

    public User updatePassword(Long id, String newPassword);

    public String deleteUser(Long id);

    @Transactional
    public User FollowUser(Long userId, Long followingUserId);

    @Transactional
    public User UnfollowUser(Long userId, Long unfollowingUserId);

    public List<User> SearchUsers(String query);

    public User findUserBYjwt(String jwt);

}
