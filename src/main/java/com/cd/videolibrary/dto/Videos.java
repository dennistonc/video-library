/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cd.videolibrary.dto;

public class Videos {
    private String releaseDate;
    private String videoRating;
    private String videoTitle;
    // Programming Language + directorName month/year
    private String directorName;
    private String studioName;
    private String userNote;

    public Videos(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoTitle() {
        return videoTitle;
    }
    
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVideoRating() {
        return videoRating;
    }

    public void setVideoRating(String videoRating) {
        this.videoRating = videoRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
    
    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }
    
    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
}
