package vn.edu.usth.facebook.Page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.facebook.R;

public class ListPageAdapter extends RecyclerView.Adapter<ListPageViewHolder>{

    Context context;
    List<ListPageItem> items;

    public ListPageAdapter(Context context, List<ListPageItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ListPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListPageViewHolder(LayoutInflater.from(context).inflate(R.layout.page_frame,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListPageViewHolder holder, int position) {
        holder.nameView.setText(items.get(position).getName());

        holder.avatarView.setImageResource(items.get(position).getAvatar());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
