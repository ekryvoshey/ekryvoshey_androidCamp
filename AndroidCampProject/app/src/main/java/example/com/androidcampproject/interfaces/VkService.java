package example.com.androidcampproject.interfaces;

import example.com.androidcampproject.responses.AlbumsListResponse;
import example.com.androidcampproject.responses.FriendsListResponse;
import example.com.androidcampproject.responses.PhotoListResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Esmond on 20.07.2015.
 */
public interface VkService {
    @GET("/method/friends.get")
    void getFriendsList(@Query("user_id") String userId, @Query("fields") String photo, Callback<FriendsListResponse> cb);

    @GET("/method/photos.getAlbums")
    void getPhotoAlbum(@Query("owner_id") long ownerId, @Query("need_covers") int needCoversFlag, Callback<AlbumsListResponse> cb);

    @GET("/method/photos.get")
    void getPhoto(@Query("owner_id") long ownerId, @Query("album_id") long albumId, Callback<PhotoListResponse> cb);
}
