package example.com.androidcampproject;

import android.app.Application;

import java.util.List;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.events.LoadFriendsListEvent;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Esmond on 19.07.2015.
 */
public class MyApplication extends Application {
    String accessToken = MainActivity.accessToken;
    String userId = MainActivity.userId;
    static VkService vkService;
    EditorEvent event = new EditorEvent();

    @Override
    public void onCreate() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.vk.com").build();
        vkService = restAdapter.create(VkService.class);
        EventBus.getDefault().register(this);
    }

    public void onEvent(LoadFriendsListEvent event) {
        vkService.getFriendsList(userId, new Callback<FriendsListResponse>() {
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
}
