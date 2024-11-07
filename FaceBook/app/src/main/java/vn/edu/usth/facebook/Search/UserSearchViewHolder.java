package vn.edu.usth.facebook.Search;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.usth.facebook.R;

public class UserSearchViewHolder extends RecyclerView.ViewHolder {

    ImageView avatarView;
    TextView nameView, contentView;

    public UserSearchViewHolder(@NonNull View itemView) {
        super(itemView);
        avatarView = itemView.findViewById(R.id.search_user_avatar );
        nameView = itemView.findViewById(R.id.search_user_name);
        contentView = itemView.findViewById(R.id.search_user_content);
    }
}
