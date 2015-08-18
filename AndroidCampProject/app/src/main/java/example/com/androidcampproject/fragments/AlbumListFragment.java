package example.com.androidcampproject.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.activities.MainActivity;
import example.com.androidcampproject.adapters.AlbumGridAdapter;
import example.com.androidcampproject.adapters.AlbumListAdapter;
import example.com.androidcampproject.objects.Album;
import example.com.androidcampproject.AutofitRecyclerView;
import example.com.androidcampproject.R;
import example.com.androidcampproject.responses.AlbumsListResponse;

/**
 * Created by Esmond on 02.08.2015.
 */
public class AlbumListFragment extends Fragment {
    private List<Album> albums;
    private AutofitRecyclerView recyclerView;
    private AlbumGridAdapter rvGridAdapter;
    private AlbumListFragment rvListAdapter;
    private Toolbar toolbar = MainActivity.toolbar;
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
                if(menuItem.getItemId() == R.id.action_list_view){
                    Log.d("text", "List view item selected");
                }
                if(menuItem.getItemId() == R.id.action_grid_view){
                    Log.d("text", "Grid view item selected");
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
        recyclerView = (AutofitRecyclerView) view.findViewById(R.id.album_list);

        rvGridAdapter = new AlbumGridAdapter(albums);
        recyclerView.setAdapter(rvGridAdapter);

        return view;
    }

    public void onEvent(AlbumsListResponse event) {
        rvGridAdapter.setData(event.getResponse());
        rvGridAdapter.notifyDataSetChanged();
    }
}
