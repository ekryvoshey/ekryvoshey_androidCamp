package example.com.androidcampproject;

import android.app.Application;

import retrofit.RestAdapter;

/**
 * Created by Esmond on 19.07.2015.
 */
public class MyApplication extends Application
{
    @Override
    public void onCreate(){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("https://api.vk.com").build();
        VkService vkService = restAdapter.create(VkService.class);
    }
}
