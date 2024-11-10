package vn.edu.usth.facebook.Page;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.usth.facebook.R;

public class ListPageViewHolder extends RecyclerView.ViewHolder{

    ImageView avatarView;
    TextView nameView;
    public ListPageViewHolder(@NonNull View itemView) {
        super(itemView);
        avatarView = itemView.findViewById(R.id.page_avatar);
        nameView = itemView.findViewById(R.id.page_name);
    }
}
