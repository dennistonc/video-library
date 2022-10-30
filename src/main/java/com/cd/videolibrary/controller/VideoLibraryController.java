package com.cd.videolibrary.controller;

import com.cd.videolibrary.dao.VideoLibraryDaoException;
import com.cd.videolibrary.dto.Videos;
import com.cd.videolibrary.ui.VideoLibraryView;
import java.util.List;
import com.cd.videolibrary.dao.VideoLibraryDao;

public class VideoLibraryController {
    
    private VideoLibraryView view;
    private VideoLibraryDao dao;
    
    public VideoLibraryController(VideoLibraryDao dao, VideoLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listVideos();
                        break;
                    case 2:
                        searchVideo();
                        break;
                    case 3:
                        viewVideo();
                        break;
                    case 4:
                        addVideo();
                        break;
                    case 5:
                        removeVideo();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        
            exitMessage();
        } catch (VideoLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void listVideos() throws VideoLibraryDaoException {
        view.displayDisplayAllBanner();
        List<Videos> videoList = dao.getAllVideos();
        view.displayVideoList(videoList);
    }
    
    private void searchVideo() throws VideoLibraryDaoException {
    
        view.displayVideoSearchBanner();
        List<Videos> videoList = dao.getAllVideos();
        String searchInput = view.getVideoSearchChoice();
        view.displayVideoSearch(videoList, searchInput);
    }
    
    private void viewVideo() throws VideoLibraryDaoException {
        view.displayDisplayVideoBanner();
        String videoTitle = view.getVideoTitleChoice();
        Videos video = dao.getVideo(videoTitle);
        view.displayVideo(video);
    }
    
    private void addVideo() throws VideoLibraryDaoException {
        view.displayAddVideoBanner();
        Videos newVideo = view.getNewVideoInfo();
        dao.addVideo(newVideo.getVideoTitle(), newVideo);
        view.displayCreateSuccessBanner();
    }
    
    private void removeVideo() throws VideoLibraryDaoException {
        view.displayRemoveVideoBanner();
        String videoTitle = view.getVideoTitleChoice();
        Videos removedVideo = dao.removeVideo(videoTitle);
        view.displayRemoveResult(removedVideo);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}