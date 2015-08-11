package example.com.androidcampproject.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.Album;
import example.com.androidcampproject.AutofitRecyclerView;
import example.com.androidcampproject.R;
import example.com.androidcampproject.adapters.AlbumListAdapter;
import example.com.androidcampproject.responses.AlbumsListResponse;

/**
 * Created by Esmond on 02.08.2015.
 */
public class AlbumListFragment extends Fragment {
    private List<Album> albums;
    private AutofitRecyclerView recyclerView;
    private AlbumListAdapter rvAdapter;
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
//        EventBus.getDefault().post(new LoadAlbumsListFragmentEvent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.albums_list_layout, container, false);
        albumListFragmentContext = view.getContext();
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView = (AutofitRecyclerView)view.findViewById(R.id.album_list);

        initializeAdapter();
        rvAdapter = new AlbumListAdapter(albums);
        recyclerView.setAdapter(rvAdapter);

        return view;
    }

    public void initializeAdapter(){
        AlbumListAdapter adapter = new AlbumListAdapter(albums);
        recyclerView.setAdapter(adapter);
    }

    public void onEvent(AlbumsListResponse event){
        rvAdapter.setData(event.getResponse());
        rvAdapter.notifyDataSetChanged();
    }
}
