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
import example.com.androidcampproject.R;
import example.com.androidcampproject.activities.MainActivity;
import example.com.androidcampproject.adapters.FriendsListAdapter;
import example.com.androidcampproject.events.LoadFriendsListFragmentEvent;
import example.com.androidcampproject.objects.Friend;
import example.com.androidcampproject.responses.FriendsListResponse;

/**
 * Created by Esmond on 15.07.2015.
 */
public class FriendsListFragment extends Fragment {

    private RecyclerView recyclerView;
    private FriendsListAdapter rvAdapter;
    private List<Friend> friends;

    public static Context friendListFragmentContext;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
        getActivity().finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().post(new LoadFriendsListFragmentEvent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friends_list_layout, container, false);
        friendListFragmentContext = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.friends_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

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

    public void onEvent(FriendsListResponse event) {
        rvAdapter.setData(event.getResponse());
        rvAdapter.notifyDataSetChanged();
    }
}
