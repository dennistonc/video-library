package com.cd.videolibrary.dao;

public class VideoLibraryDaoException extends Exception{
    public VideoLibraryDaoException(String message) {
        super(message);
    }
    
    public VideoLibraryDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
