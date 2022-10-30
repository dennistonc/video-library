package com.cd.videolibrary.dao;

import com.cd.videolibrary.dto.Videos;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class VideoLibraryDaoFileImpl implements VideoLibraryDao {
    
     public static final String LIBRARY_FILE = "library.txt";
     public static final String DELIMITER = "::";

    private Map<String, Videos> videos = new HashMap<>();
    
    @Override
    public Videos addVideo(String videoTitle, Videos video)
        throws VideoLibraryDaoException {
            loadLibrary();
            Videos newVideo = videos.put(videoTitle, video);
            writeLibrary();
            return newVideo;
    }

    @Override
    public List<Videos> getAllVideos()
        throws VideoLibraryDaoException {
            loadLibrary();
            return new ArrayList<Videos>(videos.values());
    }

    @Override
    public Videos getVideo(String videoTitle)
        throws VideoLibraryDaoException {
            loadLibrary();
            return videos.get(videoTitle);
    }

    @Override
    public Videos removeVideo(String videoTitle)
        throws VideoLibraryDaoException {
        loadLibrary();
        Videos removedVideo = videos.remove(videoTitle);
        writeLibrary();
        return removedVideo;
    }
    
    @Override
    public Videos searchVideo(String search)
        throws VideoLibraryDaoException {
            loadLibrary();
            return videos.get(search);
    }
    
    private Videos unmarshallVideo(String videoAsText){
        // videoAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // 1234::Ada::Lovelace::Java-September1842
        //
        // We then split that line on our DELIMITER - which we are using as :: // Leaving us with an array of Strings, stored in videoTokens.
        // Which should look like this:
        // ______________________________________
        // | | | | |
        // |1234|Ada|Lovelace|Java-September1842|
        // | | | | |
        // --------------------------------------
        // [0] [1] [2] [3]
        String[] videoTokens = videoAsText.split(DELIMITER);
        
        // Given the pattern above, the videoTitle is in index 0 of the array.
        String videoTitle = videoTokens[0];

        // Which we can then use to create a new Videos object to satisfy // the requirements of the Videos constructor.
        Videos videoFromFile = new Videos(videoTitle);
    
        // However, there are 3 remaining tokens that need to be set into the
        // new Videos object. Do this manually by using the appropriate setters.
        
        // Index 1 - Release Date
        videoFromFile.setReleaseDate(videoTokens[1]);

        // Index 2 - Video Rating
        videoFromFile.setVideoRating(videoTokens[2]);

        // Index 3 - Director Name
        videoFromFile.setDirectorName(videoTokens[3]);

        // Index 4 - Studio Name
        videoFromFile.setStudioName(videoTokens[4]);

        // Index 5 - User Note
        videoFromFile.setUserNote(videoTokens[5]);
        
        // We have now created a DVD! Return it!
        return videoFromFile;
    }
    
    private void loadLibrary() throws VideoLibraryDaoException {
        Scanner scanner;
        
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                new BufferedReader(
                    new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
        throw new VideoLibraryDaoException(
                    "Could not load library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentVideo holds the most recent DVD unmarshalled
        Videos currentVideo;
        // Go through LIBRARY_FILE line by line, decoding each line into a
        // Videos object by calling the unmarshallVideo method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Videos
            currentVideo = unmarshallVideo(currentLine);
            // We are going to use the video title as the map key for our video object.
            // Put currentVideo into the map using video title as the key
            videos.put(currentVideo.getVideoTitle(), currentVideo);
        }
        // close scanner
        scanner.close();
    }
    
    private String marshallVideo(Videos aVideo){
    // We need to turn a Videos object into a line of text for our file.
    // For example, we need an in memory object to end up like this:
    // Toy Story::Jun 1993::G::Tom Hanks::Disney::A classic
    
    // It's not a complicated process. Just get out each property,
    // and concatenate with our DELIMITER as a kind of spacer.
    
    // Start with the video title, since that's supposed to be first.
    String videoAsText = aVideo.getVideoTitle() + DELIMITER;
    
    // add the rest of the properties in the correct order:
    
    // ReleaseDate
    videoAsText += aVideo.getReleaseDate() + DELIMITER;
    
    // VideoRating
    videoAsText += aVideo.getVideoRating() + DELIMITER;
    
    // DirectorName
    videoAsText += aVideo.getDirectorName() + DELIMITER;
    
    // StudioName
    videoAsText += aVideo.getStudioName() + DELIMITER;
    
    // UserNote
    videoAsText += aVideo.getUserNote();
    
    // We have now turned a DVD/Video to text! Return it!
    return videoAsText;
    }
    
    /**
    * Writes all videos in the roster out to a LIBRARY_FILE. See loadRoster
    * for file format.
    *
    * @throws VideoLibraryDaoException if an error occurs writing to the file
    */
    private void writeLibrary() throws VideoLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new VideoLibraryDaoException(
                "Could not save library data.", e);
        }
        
        // Write out the Videos objects to the roster file.
        String videoAsText;
        List<Videos> videoList = this.getAllVideos();
        for (Videos currentVideo : videoList) {
            // turn a Videos into a String
            videoAsText = marshallVideo(currentVideo);
            // write the Videos object to the file
            out.println(videoAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
    // Clean up
    out.close();
    }
}
