package example.com.androidcampproject;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.events.AlbumClickEvent;
import example.com.androidcampproject.events.FriendClickEvent;
import example.com.androidcampproject.events.LoadFriendsListFragmentEvent;
import example.com.androidcampproject.events.UserSignedInEvent;
import example.com.androidcampproject.interfaces.VkService;
import example.com.androidcampproject.responses.AlbumsListResponse;
import example.com.androidcampproject.responses.FriendsListResponse;
import example.com.androidcampproject.responses.PhotoListResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Esmond on 19.07.2015.
 */
public class MyApplication extends Application {
    private static VkService vkService;
    private static long owner_id;
    private static long album_id;
    UserSignedInEvent event = new UserSignedInEvent();

    @Override
    public void onCreate() {
        super.onCreate();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.vk.com").build();
        vkService = restAdapter.create(VkService.class);
        EventBus.getDefault().register(this);
    }

    public void onEvent(LoadFriendsListFragmentEvent event) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String userId = sharedPref.getString(MyUtilities.USER_KEY, "");
        vkService.getFriendsList(userId, "photo", new Callback<FriendsListResponse>() {
            @Override
            public void success(FriendsListResponse friendsListResponse, Response response) {
                EventBus.getDefault().post(friendsListResponse);
            }

            @Override
            public void failure(RetrofitError error) {
                android.util.Log.e("MyApplication", "getFriendsList failed: " + error.getMessage());
            }
        });
    }

    public void onEvent(FriendClickEvent event) {
        owner_id = event.getFriendId();
        vkService.getPhotoAlbum(owner_id, 1, new Callback<AlbumsListResponse>() {
            @Override
            public void success(AlbumsListResponse albumsListResponse, Response response) {
                EventBus.getDefault().post(albumsListResponse);
            }

            @Override
            public void failure(RetrofitError error) {
                android.util.Log.e("MyApplication", "getAlbumList failed: " + error.getMessage());
            }
        });
    }

    public void onEvent(AlbumClickEvent event) {
        album_id = event.getAlbumId();
        vkService.getPhoto(owner_id, album_id, new Callback<PhotoListResponse>() {
            @Override
            public void success(PhotoListResponse photoListResponse, Response response) {
                EventBus.getDefault().post(photoListResponse);
            }

            @Override
            public void failure(RetrofitError error) {
                android.util.Log.e("MyApplication", "getPhoto failed: " + error.getMessage());
            }
        });
    }
}
