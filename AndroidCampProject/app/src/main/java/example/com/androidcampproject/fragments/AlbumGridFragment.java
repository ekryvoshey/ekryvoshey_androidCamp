package example.com.androidcampproject.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.AutofitRecyclerView;
import example.com.androidcampproject.R;
import example.com.androidcampproject.adapters.AlbumGridAdapter;
import example.com.androidcampproject.objects.Album;
import example.com.androidcampproject.responses.AlbumsListResponse;

/**
 * Created by Esmond on 22.08.2015.
 */
public class AlbumGridFragment extends Fragment {
    private List<Album> albums;
    private AlbumGridAdapter rvGridAdapter;
    public static Context albumGridFragmentContext;

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_list_view:
                Toast.makeText(getActivity(), "List view item selected", Toast.LENGTH_LONG);
                break;
            case R.id.action_grid_view:
                Toast.makeText(getActivity(), "Grid view item selected", Toast.LENGTH_LONG);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.albums_grid_view_layout, container, false);
        albumGridFragmentContext = view.getContext();
        AutofitRecyclerView autofitRecyclerView = (AutofitRecyclerView) view.findViewById(R.id.album_list);
        rvGridAdapter = new AlbumGridAdapter(albums);
        autofitRecyclerView.setAdapter(rvGridAdapter);

        return view;
    }

    public void onEvent(AlbumsListResponse event) {
        rvGridAdapter.setData(event.getResponse());
        rvGridAdapter.notifyDataSetChanged();
    }
}
