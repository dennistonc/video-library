package com.cd.videolibrary.dao;

import com.cd.videolibrary.dto.Videos;
import java.util.List;

public interface VideoLibraryDao {
    /**
     * Adds the given video to the library and associates it with the given
     * video title. If there is already a video associated with the given
     * video title it will return that video object, otherwise it will
     * return null.
     *
     * @param videoTitle title with which video is to be associated
     * @param video video to be added to the roster
     * @return the Videos object previously associated with the given  
     * video title if it exists, null otherwise
     */
    Videos addVideo(String videoTitle, Videos video)
        throws VideoLibraryDaoException;

    /**
     * Returns a List of all videos in the roster.
     *
     * @return List containing all videos in the roster.
     */
    List<Videos> getAllVideos()
        throws VideoLibraryDaoException;

    /**
     * Returns the video object associated with the given video title.
     * Returns null if no such video exists
     *
     * @param videoTitle title of the video to retrieve
     * @return the Videos object associated with the given video title,  
     * null if no such video exists
     */
    Videos getVideo(String videoTitle)
        throws VideoLibraryDaoException;

    /**
     * Removes from the library the video associated with the video title.
     * Returns the video object that is being removed or null if
     * there is no video associated with the given video title
     *
     * @param videoTitle title of video to be removed
     * @return Videos object that was removed or null if no video
     * was associated with the given video title
     */
    Videos removeVideo(String videoTitle)
        throws VideoLibraryDaoException;
    
    Videos searchVideo(String search)
        throws VideoLibraryDaoException;
}