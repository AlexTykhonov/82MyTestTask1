package com.tae.a82mytesttask1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecycAdapter extends RecyclerView.Adapter<RecycAdapter.ViewHolder> {

    Context context;
    ArrayList<Mark> markArrayList;
    LayoutInflater layoutInflater;

    public RecycAdapter(Context context, ArrayList<Mark> markArrayList) {
        this.context = context;
        this.markArrayList = markArrayList;
        layoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.listmark, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Mark mark = markArrayList.get(i);
        viewHolder.markId.setText(mark.getId().toString());
        viewHolder.markTitle.setText(mark.getTitle());
        viewHolder.markAuthor.setText(mark.getAuthor());
        viewHolder.markDescription.setText(mark.getDescription());
        viewHolder.markPublished.setText(mark.getPublished().toString());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(context,
                        "MarkID"+markArrayList.get(i).getId(), Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(context, MarkActivity.class);
                intent.putExtra("id", markArrayList.get(i).getId());
                intent.putExtra("title", markArrayList.get(i).getTitle());
                intent.putExtra("author", markArrayList.get(i).getAuthor());
                intent.putExtra("description", markArrayList.get(i).getDescription());
                intent.putExtra("published", markArrayList.get(i).getPublished());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return markArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        final TextView markId;
        final TextView markTitle;
        final TextView markAuthor;
        final TextView markDescription;
        final TextView markPublished;
        final LinearLayout linearLayout;
        final CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.markId = itemView.findViewById(R.id.markId);
            this.markTitle = itemView.findViewById(R.id.markTitle);
            this.markAuthor = itemView.findViewById(R.id.markAuthor);
            this.markDescription = itemView.findViewById(R.id.markDescription);
            this.markPublished = itemView.findViewById(R.id.markPublished);
            this.linearLayout = itemView.findViewById(R.id.linearLayout);
            this.cardView = itemView.findViewById(R.id.card_view);
        }

    }
    public void  setData (ArrayList<Mark> setMarkList) {

        final MarkDiffUtil markDiffUtil = new MarkDiffUtil(markArrayList, setMarkList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(markDiffUtil);

        markArrayList.clear();
        markArrayList.addAll(setMarkList);
        diffResult.dispatchUpdatesTo(this);
        //         notifyDataSetChanged();
// latest update



    }

}
