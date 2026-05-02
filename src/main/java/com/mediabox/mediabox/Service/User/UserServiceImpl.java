package com.mediabox.mediabox.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mediabox.mediabox.Exceptions.BadRequestException;
import com.mediabox.mediabox.Exceptions.InvalidJwtException;
import com.mediabox.mediabox.Exceptions.ResourceNotFoundException;
import com.mediabox.mediabox.Models.User.User;
import com.mediabox.mediabox.Repository.User.UserRepository;
import com.mediabox.mediabox.Security.config.JwtProvider;
import com.mediabox.mediabox.ServiceInterface.User.UserServices;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User RegisterUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User FindUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User", "email", email);
        }
        return user;
    }

    @Override
    public User updatePassword(Long id, String newPassword) {
        if (newPassword == null || newPassword.isBlank()) {
            throw new BadRequestException("New password is required");
        }
        User updatedUser = FindUserById(id);
        updatedUser.setPassword(newPassword);
        return userRepository.save(updatedUser);
    }

    @Override
    public String deleteUser(Long id) {
        FindUserById(id);
        userRepository.deleteById(id);
        return "User deleted successfully with userid" + id;

    }

    @Override
    @Transactional
    public User FollowUser(Long userId, Long followingUserId) {
        User user = FindUserById(userId);
        User followingUser = FindUserById(followingUserId);

        if (!user.getFollowers().contains(followingUser.getId())) {
            user.getFollowers().add(followingUser.getId());
        }
        if (!followingUser.getFollowing().contains(user.getId())) {
            followingUser.getFollowing().add(user.getId());
        }
        userRepository.save(user);
        userRepository.save(followingUser);

        return user;
    }

    @Override
    @Transactional
    public User UnfollowUser(Long userId, Long unfollowingUserId) {
        User user = FindUserById(userId);
        User followingUser = FindUserById(unfollowingUserId);
        user.getFollowers().remove(followingUser.getId());
        followingUser.getFollowing().remove(user.getId());
        userRepository.save(user);
        userRepository.save(followingUser);
        return user;

    }

    @Override
    public List<User> SearchUsers(String query) {
        if (query == null || query.isBlank()) {
            throw new BadRequestException("Search query is required");
        }
        List<User> users = userRepository.SearchUser(query);
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found matching the query: " + query);
        }
        return users;
    }

    @Override
    public User findUserBYjwt(String jwt) {
        try {
            String email = JwtProvider.getEmailFromJwtToken(jwt);
            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new InvalidJwtException("Invalid JWT token: user not found");
            }
            return user;
        } catch (InvalidJwtException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InvalidJwtException("Invalid or expired JWT token");
        }
    }

}
