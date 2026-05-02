package com.mediabox.mediabox.Controller.reel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediabox.mediabox.Models.User.User;
import com.mediabox.mediabox.Models.reel.Reels;
import com.mediabox.mediabox.ServiceInterface.User.UserServices;
import com.mediabox.mediabox.ServiceInterface.reel.ReelsService;

@RestController
@RequestMapping("/api/reels")
public class ReelsController {

    @Autowired
    private ReelsService reelsService;
    @Autowired
    UserServices userServices;

    @PostMapping("/create")
    public Reels creatReels(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt) {

        User requser = userServices.findUserBYjwt(jwt);

        return reelsService.createReel(reels, requser);

    }

    @GetMapping("/")
    public List<Reels> findAllReels() {

        return reelsService.findallReels();

    }

    @GetMapping("/user/{userId}")
    public List<Reels> findUsersReel(@PathVariable Long userId) throws Exception {
        return reelsService.findUserReels(userId);

    }
}