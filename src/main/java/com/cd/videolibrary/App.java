package com.cd.videolibrary;

import com.cd.videolibrary.controller.VideoLibraryController;
import com.cd.videolibrary.dao.VideoLibraryDaoFileImpl;
import com.cd.videolibrary.ui.VideoLibraryView;
import com.cd.videolibrary.ui.UserIO;
import com.cd.videolibrary.ui.UserIOConsoleImpl;
import com.cd.videolibrary.dao.VideoLibraryDao;

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        VideoLibraryView myView = new VideoLibraryView(myIo);
        VideoLibraryDao myDao = new VideoLibraryDaoFileImpl();
        VideoLibraryController controller =
            new VideoLibraryController(myDao, myView);
        controller.run();
    }   
}
