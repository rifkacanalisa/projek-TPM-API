package com.example.api.adapter;

import android.graphics.Color;
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
import com.example.api.NBAListener;
import com.example.api.R;
import com.example.api.fragment.MatchFragment;
import com.example.api.model.detailteam.DetailTeamItem;
import com.example.api.model.match.EventsItem;
import com.example.api.service.NBAService;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
    public List<EventsItem> matchList;

    public MatchAdapter(List<EventsItem> matchList) {
        this.matchList = matchList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View matchRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new ViewHolder(matchRow);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchAdapter.ViewHolder holder, int position) {
        holder.bind(matchList.get(position));
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTeamHome, ivTeamAway;
        TextView tvHomeName, tvHomeScore, tvAwayName, tvAwayScore, tvDate;
        String idHomeTeam, idAwayTeam;
        String sHomeScore, sAwayScore;
        int homeScore, awayScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivTeamHome = itemView.findViewById(R.id.iv_match_hometeam);
            ivTeamAway = itemView.findViewById(R.id.iv_match_awayteam);
            tvHomeName = itemView.findViewById(R.id.tv_match_hometeam_name);
            tvHomeScore = itemView.findViewById(R.id.tv_match_hometeam_score);
            tvAwayName = itemView.findViewById(R.id.tv_match_awayteam_name);
            tvAwayScore = itemView.findViewById(R.id.tv_match_awayteam_score);
            tvDate = itemView.findViewById(R.id.tv_match_date);
        }

        void bind(EventsItem item){
            idHomeTeam = item.getIdHomeTeam();
            idAwayTeam = item.getIdAwayTeam();

            new NBAService().getDetailTeam(detailHomeListener, idHomeTeam);
            new NBAService().getDetailTeam(detailAwayListener, idAwayTeam);
            
            tvHomeName.setText(item.getStrHomeTeam());
            tvHomeScore.setText(item.getIntHomeScore());
            tvAwayName.setText(item.getStrAwayTeam());
            tvAwayScore.setText(item.getIntAwayScore());
            tvDate.setText(item.getDateEvent());

            sHomeScore = item.getIntHomeScore();
            sAwayScore = item.getIntAwayScore();
            if(sHomeScore!=null && sAwayScore!=null){
                homeScore = Integer.parseInt(sHomeScore);
                awayScore = Integer.parseInt(sAwayScore);
                if(homeScore > awayScore){
                    tvHomeScore.setTextColor(Color.parseColor("#537a50"));
                    tvAwayScore.setTextColor(Color.parseColor("#bf5c5c"));
                } else {
                    tvHomeScore.setTextColor(Color.parseColor("#bf5c5c"));
                    tvAwayScore.setTextColor(Color.parseColor("#537a50"));
                }
            }
        }

        NBAListener<List<DetailTeamItem>> detailHomeListener = new NBAListener<List<DetailTeamItem>>() {
            @Override
            public void onSuccess(List<DetailTeamItem> items) {
                Glide.with(itemView.getContext()).load(items.get(0).getStrTeamBadge()).into(ivTeamHome);
            }

            @Override
            public void onFailed(String msg) {
                Log.d("Error : ", msg);
            }
        };

        NBAListener<List<DetailTeamItem>> detailAwayListener = new NBAListener<List<DetailTeamItem>>() {
            @Override
            public void onSuccess(List<DetailTeamItem> items) {
                Glide.with(itemView.getContext()).load(items.get(0).getStrTeamBadge()).into(ivTeamAway);
            }

            @Override
            public void onFailed(String msg) {
                Log.d("Error : ", msg);
            }
        };
    }
}
