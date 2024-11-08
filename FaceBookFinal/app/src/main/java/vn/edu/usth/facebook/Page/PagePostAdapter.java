package vn.edu.usth.facebook.Page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.facebook.R;

public class PagePostAdapter  extends RecyclerView.Adapter<PagePostViewHolder>{

    Context context;
    List<PagePostItem> items;

    public PagePostAdapter(Context context, List<PagePostItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public PagePostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PagePostViewHolder(LayoutInflater.from(context).inflate(R.layout.post_frame,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PagePostViewHolder holder, int position) {
        holder.nameView.setText(items.get(position).getName());
        holder.timeView.setText(items.get(position).getTime());
        holder.contentView.setText(items.get(position).getContent());
        holder.avatarView.setImageResource(items.get(position).getAvatar());
        holder.postView.setImageResource(items.get(position).getPostimage());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    
}
