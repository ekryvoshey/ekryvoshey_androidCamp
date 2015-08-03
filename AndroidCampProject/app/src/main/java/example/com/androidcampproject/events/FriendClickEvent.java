package example.com.androidcampproject.events;

import android.os.Bundle;

import de.greenrobot.event.EventBus;

/**
 * Created by Esmond on 30.07.2015.
 */
public class FriendClickEvent {
    long userId;
    public FriendClickEvent(long userId){
        this.userId = userId;
    }

    public long getFriendId() {
        return userId;
    }
}
