package example.com.androidcampproject.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.Friend;
import example.com.androidcampproject.R;
import example.com.androidcampproject.adapters.FriendsListAdapter;
import example.com.androidcampproject.responses.FriendsListResponse;
import example.com.androidcampproject.events.LoadFriendsListEvent;

/**
 * Created by Esmond on 15.07.2015.
 */
public class FriendsListFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FriendsListAdapter rvAdapter;
    List<Friend> friends;

    public static Context context;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().post(new LoadFriendsListEvent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friends_list_layout, container, false);
        context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.friendsList);
        layoutManager = new LinearLayoutManager(getActivity());

        initializeAdapter();

        rvAdapter = new FriendsListAdapter(friends);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    private void initializeAdapter() {
        FriendsListAdapter adapter = new FriendsListAdapter(friends);
        recyclerView.setAdapter(adapter);
    }

    public void onEvent(FriendsListResponse event){
        rvAdapter.setData(event.getResponse());
        rvAdapter.notifyDataSetChanged();
    }
}
