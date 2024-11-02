package vn.edu.usth.facebook.Home;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.usth.facebook.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    ImageButton imageButton;
    TextView nameView,timeView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.content_imageview);
        nameView = itemView.findViewById(R.id.user_name);
        timeView = itemView.findViewById(R.id.time);
    }
}
