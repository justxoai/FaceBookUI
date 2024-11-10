package vn.edu.usth.facebook.Group;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.usth.facebook.R;

public class GroupViewHolder extends RecyclerView.ViewHolder {

    ImageView avatarView;
    TextView nameView,postView;

    public GroupViewHolder(@NonNull View itemView) {
        super(itemView);
        avatarView = itemView.findViewById(R.id.group_avatar);
        nameView = itemView.findViewById(R.id.group_name);
        postView = itemView.findViewById(R.id.num_post);
    }
}