/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cd.videolibrary.ui;

import com.cd.videolibrary.dto.Videos;
import java.util.List;

public class VideoLibraryView {
    
    private UserIO io;
    
    public VideoLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("DVD Library Main Menu");
        io.print("1. List DVD Library");
        io.print("2. Search for a DVD by Title");
        io.print("3. View DVD Information");
        io.print("4. Add New or Update Existing DVD in the Library");
        io.print("5. Remove a DVD in the Library");
        io.print("6. Exit");

        return io.readInt("Please select from the choices above.", 1, 6);
    }
    
    public void displayVideoList(List<Videos> videoList) {
        for (Videos currentVideo : videoList) {
            String videoInfo = currentVideo.getVideoTitle();
            io.print(videoInfo);
        }
        io.readString("Please hit enter to continue.");
    }
        
    public void displayVideoSearchBanner () {
        io.print("=== Search for a DVD by Title ===");
    }
    
    public String getVideoSearchChoice() {
        return io.readString("Please enter the search query.");
    }
    
    public void displayVideoSearch(List<Videos> videoList, String search) {
        if (search != null && search != "") {
            io.print("Search results: ");
            for (Videos currentVideo : videoList) {
                if (currentVideo.getVideoTitle().toLowerCase().contains(search.toLowerCase())) {
                    io.print(" " + currentVideo.getVideoTitle());
                }                
            }
        } else {
            io.print("DVD not found in Library.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayAddVideoBanner() {
        io.print("=== Add or Update DVD in Library ===");
        io.print("=== (Existing DVDs will have info updated by Title Match) ===");
    }
    
    public Videos getNewVideoInfo() {
        String videoTitle = io.readString("Please enter the Title of the DVD");
        String releaseDate = io.readString("Please enter its Release Date ");
        String videoRating = io.readString("Please enter its MPAA Rating");
        String directorName = io.readString("Please enter the name of the Director of the DVD");
        String studioName = io.readString("Please enter the name of the Studio which produced the DVD");
        String userNote = io.readString("Please enter any notes regarding this DVD");
        Videos currentVideo = new Videos(videoTitle);
        currentVideo.setReleaseDate(releaseDate);
        currentVideo.setVideoRating(videoRating);
        currentVideo.setDirectorName(directorName);
        currentVideo.setStudioName(studioName);
        currentVideo.setUserNote(userNote);
        return currentVideo;
    }
        
    public void displayCreateSuccessBanner() {
        io.readString("DVD library successfully updated. Please hit enter to continue");
    }
    
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }
    
    public void displayDisplayVideoBanner () {
        io.print("=== DVD Information ===");
    }

    public String getVideoTitleChoice() {
        return io.readString("Please enter a DVD Title.");
    }

    public void displayVideo(Videos video) {
        if (video != null) {
            io.print("Title: " + video.getVideoTitle());
            io.print("Release Date: " + video.getReleaseDate());
            io.print("MPAA Rating: " + video.getVideoRating());
            io.print("Director's Name: " + video.getDirectorName());
            io.print("Studio's Name: " + video.getStudioName());
            io.print("User Notes: " + video.getUserNote());
            io.print("");
        } else {
            io.print("DVD not found in Library.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayRemoveVideoBanner () {
        io.print("=== Remove a DVD from the Library ===");
    }

    public void displayRemoveResult(Videos videoRecord) {
        if(videoRecord != null){
          io.print("DVD successfully removed from the Library.");
        }else{
          io.print("DVD not found in Library.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayExitBanner() {
        io.print("Goodbye!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Please enter a valid command.");
    }
    
    public void displayErrorMessage(String errorMsg) {
         io.print("=== ERROR ===");
         io.print(errorMsg);
    }
}
