package example.com.androidcampproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Esmond on 15.07.2015.
 */
public class RecyclerViewFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RVAdapter rvAdapter;
    List<Person> persons;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_layout, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());

        initializeData();
        initializeAdapter();

        rvAdapter = new RVAdapter(persons);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    private void initializeData() {
        persons = new ArrayList<>();
        persons.add(new Person("User One", "City One", R.drawable.notification_template_icon_bg));
        persons.add(new Person("User Two", "City Two", R.drawable.notification_template_icon_bg));
        persons.add(new Person("User Three", "City Three", R.drawable.notification_template_icon_bg));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        recyclerView.setAdapter(adapter);
    }
}
