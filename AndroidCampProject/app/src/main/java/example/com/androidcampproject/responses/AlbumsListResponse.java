package example.com.androidcampproject.responses;

import java.util.List;

import example.com.androidcampproject.objects.Album;

/**
 * Created by Esmond on 29.07.2015.
 */
public class AlbumsListResponse {
    List<Album> response;

    public List<Album> getResponse() {
        return response;
    }

    public void setResponse(List<Album> response) {
        this.response = response;
    }
}
