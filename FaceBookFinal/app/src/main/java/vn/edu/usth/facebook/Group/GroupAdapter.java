package vn.edu.usth.facebook.Group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.facebook.R;

public class GroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    Context context;
    List<GroupItem> items;

    public GroupAdapter(Context context, List<GroupItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupViewHolder(LayoutInflater.from(context).inflate(R.layout.group_frame,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        holder.nameView.setText(items.get(position).getName());
        holder.timeView.setText(items.get(position).getTime());
        holder.avatarView.setImageResource(items.get(position).getAvatar());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
