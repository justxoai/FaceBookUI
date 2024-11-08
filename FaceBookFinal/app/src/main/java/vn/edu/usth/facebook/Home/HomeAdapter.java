package vn.edu.usth.facebook.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.edu.usth.facebook.R;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    Context context;
    List<HomeItem> items;

    public HomeAdapter(Context context, List<HomeItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.post_frame, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        HomeItem item = items.get(position);

        holder.nameView.setText(item.getName());
        holder.timeView.setText(item.getTime());
        holder.contentView.setText(item.getContent());
        holder.avatarView.setImageResource(item.getAvatar());

        // Check if post image is a URL or a drawable resource
        if (item.getPostimageUrl() != null) {
            // Load image from URL using Glide
            Glide.with(context).load(item.getPostimageUrl()).into(holder.postView);
        } else {
            // Load image from resource ID
            holder.postView.setImageResource(item.getPostimage());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}