package vn.edu.usth.facebook.User;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.facebook.Page.ListPageItem;
import vn.edu.usth.facebook.R;

public class NotFriendAdapter extends RecyclerView.Adapter<NotFriendViewHolder>{

    Context context;
    List<NotFriendItem> items;

    public NotFriendAdapter(Context context, List<NotFriendItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public NotFriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotFriendViewHolder(LayoutInflater.from(context).inflate(R.layout.not_friend_frame,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotFriendViewHolder holder, int position) {
        NotFriendItem item = items.get(position);
        // Set the name
        holder.nameView.setText(item.getName());
        // Set the avatar bitmap to the ImageView
        Bitmap avatarBitmap = item.getAvatarBitmap();
        if (avatarBitmap != null) {
            holder.avatarView.setImageBitmap(avatarBitmap);  // Use Bitmap instead of image resource ID
        } else {
            holder.avatarView.setImageResource(R.drawable.capybara_usth);  // Use a default avatar if Bitmap is null
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
