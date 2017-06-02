package com.example.quad2.kickstarterprojects.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quad2.kickstarterprojects.R;
import com.example.quad2.kickstarterprojects.activities.MainActivity;
import com.example.quad2.kickstarterprojects.activities.ProjectDetailActivity;
import com.example.quad2.kickstarterprojects.pojo.ApiDatum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quad2 on 31/5/17.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private List<ApiDatum> projectList = new ArrayList<>();
    private Context context;

    public ProjectAdapter(List<ApiDatum> projectList, Context context) {
        this.projectList = projectList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_project, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ApiDatum apiDatum = getItem(position);
        if (apiDatum != null) {

            if (apiDatum.getTitle() != null && apiDatum.getTitle().length() > 0) {
                holder.projectTitile.setText(apiDatum.getTitle());
            }

            holder.amountPledged.setText("Amount : "+ apiDatum.getAmtPledged());

            if (apiDatum.getNumBackers()!=null && apiDatum.getNumBackers().length()>0){
                holder.backers.setText("Backers : "+ apiDatum.getNumBackers());
            }

            if (apiDatum.getEndTime()!=null && apiDatum.getEndTime().length()>0){
                holder.days.setText(apiDatum.getEndTime());
            }

            holder.projectCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProjectDetailActivity.class);
                    intent.putExtra("projectDetail", apiDatum);
                    context.startActivity(intent);
                    ((MainActivity)context).overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    // method that returns the item in the list at a particular position
    public ApiDatum getItem(int position) {
        return position < projectList.size() ? projectList.get(position) : null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView projectTitile, backers, amountPledged, days;
        CardView projectCard;

        public ViewHolder(final View itemView) {
            super(itemView);

            projectCard = (CardView) itemView.findViewById(R.id.project_card);
            projectTitile = (TextView) itemView.findViewById(R.id.project_title);
            backers = (TextView) itemView.findViewById(R.id.backers);
            days = (TextView) itemView.findViewById(R.id.days_count);
            amountPledged = (TextView) itemView.findViewById(R.id.pledged_amount);
        }
    }
}
