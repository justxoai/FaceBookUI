package vn.edu.usth.facebook.Notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.facebook.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder>{

    Context context;
    List<NotificationItem> items;

    public NotificationAdapter(Context context, List<NotificationItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(LayoutInflater.from(context).inflate(R.layout.notice_frame,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.nameView.setText(items.get(position).getName());
        holder.timeView.setText(items.get(position).getTime());
        holder.avatarView.setImageResource(items.get(position).getAvatar());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
