package example.com.androidcampproject;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Esmond on 20.07.2015.
 */
public interface VkService {
    @GET("/method/friends.get")
    void getFriendsList(@Query("id") String userId, Callback<FriendsListResponse> cb);

    @GET("/method/{id}/photos.getAlbums")
    void getPhotoAlbum(@Path("id") int ownerId, Callback<Album> cb);

    @GET("/method/{id}/photos.get")
    void getPhoto(@Path("id") int ownerId, Callback<Photo> cb);
}
