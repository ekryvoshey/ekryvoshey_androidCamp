package example.com.androidcampproject.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.AutofitRecyclerView;
import example.com.androidcampproject.Photo;
import example.com.androidcampproject.R;
import example.com.androidcampproject.adapters.PhotoListAdapter;
import example.com.androidcampproject.responses.PhotoListResponse;

/**
 * Created by Esmond on 03.08.2015.
 */
public class PhotoListFragment extends Fragment {
    private List<Photo> photos;
    private AutofitRecyclerView recyclerView;
    private PhotoListAdapter rvAdapter;
    public static Context photoListFragmentContext;

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
//        EventBus.getDefault().post(new LoadPhotoListFragmentEvent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.photo_list_layout, container, false);
        photoListFragmentContext = view.getContext();
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView = (AutofitRecyclerView)view.findViewById(R.id.photo_list);

        initializeAdapter();
        rvAdapter = new PhotoListAdapter(photos);
        recyclerView.setAdapter(rvAdapter);

        return view;
    }

    public void initializeAdapter(){
        PhotoListAdapter adapter = new PhotoListAdapter(photos);
        recyclerView.setAdapter(adapter);
    }

    public void onEvent(PhotoListResponse event){
        rvAdapter.setData(event.getResponse());
        rvAdapter.notifyDataSetChanged();
    }
}
