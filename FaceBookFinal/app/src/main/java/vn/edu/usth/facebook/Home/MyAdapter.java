package vn.edu.usth.facebook.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.facebook.R;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


    Context context;
    List<HomeItem> items;

    public MyAdapter(Context context, List<HomeItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.home_content,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameView.setText(items.get(position).getName());
        holder.timeView.setText(items.get(position).getTime());
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.secondimageView.setImageResource(items.get(position).getSecondImage());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
