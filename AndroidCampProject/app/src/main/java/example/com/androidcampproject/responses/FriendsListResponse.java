package example.com.androidcampproject.responses;

import java.util.List;

import example.com.androidcampproject.Friend;

/**
 * Created by i.demedyuk on 25.07.2015.
 */
public class FriendsListResponse {
    List<Friend> response;

    public List<Friend> getResponse() {
        return response;
    }

    public void setResponse(List<Friend> response) {
        this.response = response;
    }
}
