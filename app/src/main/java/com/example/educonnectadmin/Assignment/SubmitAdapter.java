package com.example.educonnectadmin.Assignment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educonnectadmin.R;

import java.util.ArrayList;
import java.util.List;

public class SubmitAdapter extends RecyclerView.Adapter<SubmitAdapter.submitViewHolder> {
    Context context;
    List<SubmitData> list;

    public SubmitAdapter(Context context, List<SubmitData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public submitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.submisson_item_layout, parent, false);
        return new submitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull submitViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(list.get(position).getPdfUrl()));
                context.startActivity(intent);
            }
        });
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(list.get(position).getPdfUrl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filteredList(ArrayList<SubmitData> filterlist) {
        list = filterlist;
        notifyDataSetChanged();
    }

    public class submitViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView download;
        public submitViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.UserName);
            download = itemView.findViewById(R.id.ebookDownload);
        }
    }
}
