package example.com.androidcampproject;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Esmond on 18.08.2015.
 */
public class ListRecyclerView extends RecyclerView {
    public ListRecyclerView(Context context){
        super(context);
        init();
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        setLayoutManager(linearLayoutManager);
    }
}
