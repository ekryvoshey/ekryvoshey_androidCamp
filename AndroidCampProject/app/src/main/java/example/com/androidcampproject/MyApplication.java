package example.com.androidcampproject;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.events.LoadFriendsListEvent;
import example.com.androidcampproject.events.UserSignedInEvent;
import example.com.androidcampproject.interfaces.VkService;
import example.com.androidcampproject.responses.FriendsListResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Esmond on 19.07.2015.
 */
public class MyApplication extends Application {
    static VkService vkService;
    UserSignedInEvent event = new UserSignedInEvent();

    @Override
    public void onCreate() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.vk.com").build();
        vkService = restAdapter.create(VkService.class);
        EventBus.getDefault().register(this);
    }

    public void onEvent(LoadFriendsListEvent event) {
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
}
