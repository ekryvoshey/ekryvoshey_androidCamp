package example.com.androidcampproject;

import android.app.Application;

import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Esmond on 19.07.2015.
 */
public class MyApplication extends Application
{
    String accessToken = MainActivity.accessToken;
    String userId = MainActivity.userId;
    static VkService vkService;
    EventBus bus = new EventBus().getDefault();
    EditorEvent event = new EditorEvent();
    Callback callback;

    @Override
    public void onCreate(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.vk.com").build();
        vkService = restAdapter.create(VkService.class);

        callback = new Callback() {
            @Override
            public void success(List<Friend> friends, Response response) {}
            @Override
            public void failure(RetrofitError error) {}
        };
    }

    public void onEvent(){
        vkService.getFriendsList(userId, callback);
        bus.post(event);
    }
}
