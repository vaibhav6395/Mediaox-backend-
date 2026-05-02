package com.mediabox.mediabox.Service.Reel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediabox.mediabox.Models.User.User;
import com.mediabox.mediabox.Models.reel.Reels;
import com.mediabox.mediabox.Repository.reel.ReelsRepository;
import com.mediabox.mediabox.ServiceInterface.User.UserServices;
import com.mediabox.mediabox.ServiceInterface.reel.ReelsService;

@Service
public class ReelsServiceimpl implements ReelsService {

    @Autowired
    private ReelsRepository reelsRepository;
    @Autowired
    private UserServices userServices;

    @Override
    public Reels createReel(Reels reel, User user) {
        Reels newreel = new Reels();
        newreel.setTitle(reel.getTitle());
        newreel.setVideo(reel.getVideo());
        newreel.setUser(user);

        return reelsRepository.save(newreel);
    }

    @Override
    public List<Reels> findallReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUserReels(Long userId) {
        User user = userServices.FindUserById(userId);
        return reelsRepository.findByUserId(userId);
    }

}
