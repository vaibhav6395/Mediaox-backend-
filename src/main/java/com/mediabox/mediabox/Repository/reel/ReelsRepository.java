package com.mediabox.mediabox.Repository.reel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediabox.mediabox.Models.reel.Reels;

public interface ReelsRepository extends JpaRepository<Reels, Long> {

    public List<Reels> findByUserId(Long userId);

}
