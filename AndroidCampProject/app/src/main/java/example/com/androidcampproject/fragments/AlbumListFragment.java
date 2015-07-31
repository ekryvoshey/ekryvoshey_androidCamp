package example.com.androidcampproject.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.Album;
import example.com.androidcampproject.R;
import example.com.androidcampproject.adapters.AlbumListAdapter;
import example.com.androidcampproject.adapters.FriendsListAdapter;
import example.com.androidcampproject.events.LoadAlbumsListEvent;
import example.com.androidcampproject.responses.AlbumsListResponse;
import example.com.androidcampproject.responses.FriendsListResponse;

/**
 * Created by Esmond on 29.07.2015.
 */
public class AlbumListFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AlbumListAdapter rvAdapter;
    List<Album> albums;
    public static Context context;

    public static AlbumListFragment newInstance(long owner_id) {
        AlbumListFragment f = new AlbumListFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("owner_id", owner_id);
        f.setArguments(bundle);
        return f;
    }

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
        EventBus.getDefault().post(new LoadAlbumsListEvent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.albums_list_layout, container, false);
        context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.albumsList);
        layoutManager = new GridLayoutManager(getActivity(), 2);

        initializeAdapter();

        rvAdapter = new AlbumListAdapter(albums);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    public void onEvent(AlbumsListResponse event){
        rvAdapter.setData(event.getResponse());
        rvAdapter.notifyDataSetChanged();
    }

    public void initializeAdapter(){
        AlbumListAdapter adapter = new AlbumListAdapter(albums);
        recyclerView.setAdapter(adapter);
    }

}
