package vn.edu.usth.facebook.Group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.facebook.R;

public class InvitePeopleAdapter extends RecyclerView.Adapter<InvitePeopleViewHolder> {

    Context context;
    List<InvitePeopleItem> items;

    public InvitePeopleAdapter(Context context, List<InvitePeopleItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public InvitePeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InvitePeopleViewHolder(LayoutInflater.from(context).inflate(R.layout.invitepeople_frame,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InvitePeopleViewHolder holder, int position) {
        holder.nameView.setText(items.get(position).getName());
        holder.avatarView.setImageResource(items.get(position).getAvatar());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
