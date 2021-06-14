package com.example.api.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.api.NBAListener;
import com.example.api.R;
import com.example.api.adapter.MatchAdapter;
import com.example.api.adapter.TeamAdapter;
import com.example.api.model.team.TeamsItem;
import com.example.api.service.NBAService;

import java.util.ArrayList;
import java.util.List;

public class TeamFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false);
    }

    public RecyclerView rvTeam;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTeam = view.findViewById(R.id.rv_team);
        rvTeam.setHasFixedSize(true);
        new NBAService().getTeam(teamListener);

        rvTeam.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    NBAListener<List<TeamsItem>> teamListener = new NBAListener<List<TeamsItem>>() {
        @Override
        public void onSuccess(List<TeamsItem> items) {
//            for (TeamsItem item : items) {
//                Log.d("Hasil : ", item.getStrTeam());
//            }
            rvTeam.setLayoutManager(new LinearLayoutManager(getContext()));
            TeamAdapter teamAdapter = new TeamAdapter(items);
            rvTeam.setAdapter(teamAdapter);
        }

        @Override
        public void onFailed(String msg) {
            Log.d("Error : ", msg);
        }
    };

}