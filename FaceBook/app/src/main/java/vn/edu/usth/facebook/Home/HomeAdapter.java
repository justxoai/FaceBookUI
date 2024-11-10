package vn.edu.usth.facebook.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.edu.usth.facebook.R;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    Context context;
    List<HomeItem> items;

    public HomeAdapter(Context context, List<HomeItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.post_frame, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        HomeItem item = items.get(position);

        holder.nameView.setText(item.getName());
        holder.timeView.setText(item.getTime());
        holder.contentView.setText(item.getContent());

        // Load avatar image from URL using Glide
        if (item.getAvatarUrl() != null) {
            Glide.with(context).load(item.getAvatarUrl()).into(holder.avatarView);
        } else {
            holder.avatarView.setImageResource(R.drawable.user); // Fallback if no URL
        }

        // Check if post image is a URL or a drawable resource
        if (item.getPostImageUrl() != null) {
            // Load image from URL using Glide into the ImageView
            holder.postView.setVisibility(View.VISIBLE);
            Glide.with(context).load(item.getPostImageUrl()).into(holder.postView);
        } else {
            holder.postView.setVisibility(View.GONE);  // Hide ImageView if no image URL
        }

        // If there is a video URL, load it in the WebView
        if (item.getPostVideoUrl() != null) {
            holder.postWebView.setVisibility(View.VISIBLE);
            WebSettings webSettings = holder.postWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            holder.postWebView.loadUrl(item.getPostVideoUrl());
        } else {
            holder.postWebView.setVisibility(View.GONE);  // Hide WebView if no video URL
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
