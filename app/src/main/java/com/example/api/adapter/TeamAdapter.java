package com.example.api.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.api.DetailTeamActivity;
import com.example.api.R;
import com.example.api.model.team.TeamsItem;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    public List<TeamsItem> teamList;

    public TeamAdapter(List<TeamsItem> teamList) {
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View teamRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
        return new ViewHolder(teamRow);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.ViewHolder holder, int position) {
        holder.bind(teamList.get(position));
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTeam;
        TextView tvTeamName, tvTeamShortName, tvTeamYear, tvTeamStadium;
        Button btnDetail;
        String stadiumName, combineStadium, locationName, combineLocation;
        String stadium = " Stadium located at ";

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivTeam = itemView.findViewById(R.id.iv_team_pict);
            tvTeamName = itemView.findViewById(R.id.tv_team_name);
            tvTeamShortName = itemView.findViewById(R.id.tv_team_shortname);
            tvTeamYear = itemView.findViewById(R.id.tv_team_year);
            tvTeamStadium = itemView.findViewById(R.id.tv_team_stadium);
            btnDetail = itemView.findViewById(R.id.btn_detail_team);
        }
        void bind(TeamsItem item){
            Glide.with(itemView.getContext()).load(item.getStrTeamBadge()).into(ivTeam);
            tvTeamName.setText(item.getStrTeam());
            tvTeamShortName.setText(item.getStrTeamShort());
            tvTeamYear.setText(item.getIntFormedYear());

            stadiumName = item.getStrStadium();
            locationName = item.getStrStadiumLocation();
            combineStadium = stadiumName.concat(stadium);
            combineLocation = combineStadium.concat(locationName);
//            Log.d("Hasil : ", combineStadium);
            tvTeamStadium.setText(combineLocation);

            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detail = new Intent(itemView.getContext(), DetailTeamActivity.class);
                    detail.putExtra("id", item.getIdTeam());
                    itemView.getContext().startActivity(detail);
                }
            });

        }
    }
}
