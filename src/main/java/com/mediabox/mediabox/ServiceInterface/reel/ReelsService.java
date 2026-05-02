package com.mediabox.mediabox.ServiceInterface.reel;

import java.util.List;

import com.mediabox.mediabox.Models.User.User;
import com.mediabox.mediabox.Models.reel.Reels;

public interface ReelsService {

    public Reels createReel(Reels reel, User user);

    public List<Reels> findallReels();

    public List<Reels> findUserReels(Long userId);

}
