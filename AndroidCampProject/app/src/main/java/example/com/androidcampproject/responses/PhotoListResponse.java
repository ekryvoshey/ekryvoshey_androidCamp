package example.com.androidcampproject.responses;

import java.util.List;

import example.com.androidcampproject.Photo;

/**
 * Created by Esmond on 29.07.2015.
 */
public class PhotoListResponse {
    List<Photo> response;

    public List<Photo> getResponse() {
        return response;
    }

    public void setResponse(List<Photo> response) {
        this.response = response;
    }
}
