package example.com.androidcampproject.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.activities.MainActivity;
import example.com.androidcampproject.adapters.AlbumGridAdapter;
import example.com.androidcampproject.events.LayoutChangingEvent;
import example.com.androidcampproject.objects.Album;
import example.com.androidcampproject.AutofitRecyclerView;
import example.com.androidcampproject.R;
import example.com.androidcampproject.responses.AlbumsListResponse;

/**
 * Created by Esmond on 02.08.2015.
 */
public class AlbumListFragment extends Fragment {
    private List<Album> albums;
    private AutofitRecyclerView autofitRecyclerView;
    private AlbumGridAdapter rvGridAdapter;
    private Toolbar toolbar = MainActivity.toolbar;
    private int gridLayoutId = R.id.album_grid_view;
    private int listLayoutId = R.id.albums_list_view;
    public static String layoutTag = "grid";
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_list_view) {
                    EventBus.getDefault().post(new LayoutChangingEvent("list"));
                }
                if (menuItem.getItemId() == R.id.action_grid_view) {
                    EventBus.getDefault().post(new LayoutChangingEvent("grid"));
                }
                return true;
            }
        });
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
        autofitRecyclerView = (AutofitRecyclerView) view.findViewById(R.id.album_list);
        rvGridAdapter = new AlbumGridAdapter(albums);
        autofitRecyclerView.setAdapter(rvGridAdapter);

        return view;
    }

    public void setGridLayout() {
        if (layoutTag == "grid")
            return;
        rvGridAdapter = new AlbumGridAdapter(albums);
        rvGridAdapter.setLayoutId(R.layout.albums_grid_view_layout);
        autofitRecyclerView.setAdapter(rvGridAdapter);
    }

    @LayoutRes
    public void setListLayout() {
        if (layoutTag == "list")
            return;
        rvGridAdapter = new AlbumGridAdapter(albums);
        rvGridAdapter.setLayoutId(R.layout.albums_list_view_layout);
        autofitRecyclerView.setAdapter(rvGridAdapter);
    }

    public void onEvent(LayoutChangingEvent event) {
        String layoutId = event.getLayoutId();
        if (layoutId == "list") {
            setListLayout();
        }
        if (layoutId == "grid")
            setGridLayout();
    }

    public void onEvent(AlbumsListResponse event) {
        rvGridAdapter.setData(event.getResponse());
        rvGridAdapter.notifyDataSetChanged();
    }
}
