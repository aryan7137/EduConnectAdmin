package com.example.educonnectadmin.Assignment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educonnectadmin.R;

import java.util.ArrayList;
import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.viewHolder> {


    private final Activity context;
    private List<AssignmentData> list;
    String selectedClass;


    private ProgressDialog pd;

    public AssignmentAdapter(Activity context, List<AssignmentData> list,String selectedClass) {
        this.context = context;
        this.list = list;
        this.selectedClass = selectedClass;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.assignment_item_model, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        pd = new ProgressDialog(context);

        holder.ebookName.setText(list.get(position).getPdfTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Submissions.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name",list.get(position).getPdfTitle());
                intent.putExtra("class",selectedClass);
                context.startActivity(intent);


            }
        });

        holder.ebookDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getPdfUrl()));
                context.startActivity(intent);
            }
        });
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Submissions.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name",list.get(position).getPdfTitle());
                intent.putExtra("class",selectedClass);
                context.startActivity(intent);

            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filteredList(ArrayList<AssignmentData> filterlist) {
        list = filterlist;
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private TextView ebookName;
        private Button ebookDownload, submit;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            ebookName = itemView.findViewById(R.id.ebookName);
            ebookDownload = itemView.findViewById(R.id.assgn_load);
            submit = itemView.findViewById(R.id.submit);
        }
    }

}
