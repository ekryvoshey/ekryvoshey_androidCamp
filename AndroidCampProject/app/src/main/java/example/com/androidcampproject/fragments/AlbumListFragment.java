package example.com.androidcampproject.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.adapters.AlbumListAdapter;
import example.com.androidcampproject.objects.Album;
import example.com.androidcampproject.R;
import example.com.androidcampproject.responses.AlbumsListResponse;

/**
 * Created by Esmond on 02.08.2015.
 */

public class AlbumListFragment extends Fragment {
    private List<Album> albums;
    private AlbumListAdapter rvListAdapter;
    public static Context albumListFragmentContext;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        EventBus.getDefault().unregister(this);
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.albums_list_layout, container, false);
        albumListFragmentContext = view.getContext();
        RecyclerView listRecyclerView = (RecyclerView) view.findViewById(R.id.album_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvListAdapter = new AlbumListAdapter(albums);
        listRecyclerView.setLayoutManager(linearLayoutManager);
        listRecyclerView.setAdapter(rvListAdapter);

        return view;
    }

    public void onEvent(AlbumsListResponse event) {
        rvListAdapter.setData(event.getResponse());
        rvListAdapter.notifyDataSetChanged();
    }
}
