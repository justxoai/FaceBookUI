package vn.edu.usth.facebook.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.Video.VideoItem;

public class DoubleAdapter extends RecyclerView.Adapter<DoubleViewHolder>{
    
    Context context;
    List<DoubleItem> items;

    public DoubleAdapter(Context context, List<DoubleItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public DoubleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DoubleViewHolder(LayoutInflater.from(context).inflate(R.layout.image_video_post_frame,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoubleViewHolder holder, int position) {
        DoubleItem link = items.get(position);

        holder.nameView.setText(items.get(position).getName());

        holder.timeView.setText(items.get(position).getTime());

        holder.contentView.setText(items.get(position).getContent());

        holder.avatarView.setImageResource(items.get(position).getAvatar());

        holder.postView.setImageResource(items.get(position).getPostimage());

        holder.bind(link);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
