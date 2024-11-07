package vn.edu.usth.facebook.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.facebook.R;

public class UserSearchAdapter extends RecyclerView.Adapter<UserSearchViewHolder>{

    Context context;
    List<UserSearchItem> items;

    public UserSearchAdapter(Context context, List<UserSearchItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public UserSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserSearchViewHolder(LayoutInflater.from(context).inflate(R.layout.user_search_frame,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserSearchViewHolder holder, int position) {
        holder.nameView.setText(items.get(position).getName());
        holder.contentView.setText(items.get(position).getContent());
        holder.avatarView.setImageResource(items.get(position).getAvatar());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
