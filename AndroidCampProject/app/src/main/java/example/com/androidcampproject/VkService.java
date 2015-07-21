package example.com.androidcampproject;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Esmond on 20.07.2015.
 */
public interface VkService {
    @GET("/method/friends.getLists/{id}")
    List<Person> listPerson(@Path("id") String user);
    String accessToken = MainActivity.accessToken;
    //friends.getLists();
    //photos.getAlbums();
    //photos.get();
}
