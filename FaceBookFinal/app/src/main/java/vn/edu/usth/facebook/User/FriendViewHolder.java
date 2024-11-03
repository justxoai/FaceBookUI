package vn.edu.usth.facebook.User;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.usth.facebook.R;

public class FriendViewHolder extends RecyclerView.ViewHolder{

    ImageView avatarView, friend_avatarView;
    TextView nameView, mutal_friendView;

    public FriendViewHolder(@NonNull View itemView) {
        super(itemView);
        avatarView = itemView.findViewById(R.id.user_image);
        friend_avatarView = itemView.findViewById(R.id.user_mutal_friend);
        nameView = itemView.findViewById(R.id.user_name);
        mutal_friendView = itemView.findViewById(R.id.mutal_friend);
    }

}
