package vn.edu.usth.facebook.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import vn.edu.usth.facebook.R;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileViewHolder> {

    Context context;
    List<ProfileItem> items;

    public ProfileAdapter(Context context, List<ProfileItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfileViewHolder(LayoutInflater.from(context).inflate(R.layout.post_frame, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        ProfileItem item = items.get(position);
        holder.nameView.setText(item.getName());
        holder.timeView.setText(item.getTime());
        holder.contentView.setText(item.getContent());

        // Load images using Glide
        Glide.with(context)
                .load(item.getAvatarUrl())
                .placeholder(R.drawable.user)
                .into(holder.avatarView);

        Glide.with(context)
                .load(item.getPostImageUrl())
                .placeholder(R.drawable.post)
                .into(holder.postView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
