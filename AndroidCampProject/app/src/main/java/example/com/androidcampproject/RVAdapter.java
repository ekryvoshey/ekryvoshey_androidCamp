package example.com.androidcampproject;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Esmond on 15.07.2015.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.FriendViewHolder> {
    public Context context = FriendsListFragment.context;
    List<Friend> friends = new ArrayList<>(0);
    RVAdapter(List<Friend> friends){
        this.friends = friends;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_layout, viewGroup, false);
        FriendViewHolder pvh = new FriendViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int i) {
        holder.firstName.setText(friends.get(i).getFirst_name());
        holder.lastName.setText(friends.get(i).getLast_name());
        Glide.with(context)
                .load(friends.get(i).getPhoto())
                .into(holder.personPhoto);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        if(friends == null) return 0;
        return friends.size();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView firstName;
        TextView lastName;
        ImageView personPhoto;

        FriendViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            firstName = (TextView)itemView.findViewById(R.id.person_name);
            lastName = (TextView)itemView.findViewById(R.id.person_last_name);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    public void setData(List<Friend> friends){
        this.friends = friends;
    }
}
