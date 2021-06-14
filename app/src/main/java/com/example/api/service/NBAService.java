package com.example.api.service;

import com.example.api.NBAListener;
import com.example.api.model.detailteam.DetailResponse;
import com.example.api.model.detailteam.DetailTeamItem;
import com.example.api.model.match.EventsItem;
import com.example.api.model.match.MatchResponse;
import com.example.api.model.team.TeamResponse;
import com.example.api.model.team.TeamsItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NBAService {
    private Retrofit retrofit = null;

    public NBAAPI getNBAAPI() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(NBAAPI.URL_BASE).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(NBAAPI.class);
    }

    public void getMatch(final NBAListener<List<EventsItem>> listener) {
        getNBAAPI().getMatch().enqueue(new Callback<MatchResponse>() {
            @Override
            public void onResponse(Call<MatchResponse> call, Response<MatchResponse> response) {
                MatchResponse data = response.body();
                if (data != null && data.getEvents() != null) {
                    listener.onSuccess(data.getEvents());
                }
            }

            @Override
            public void onFailure(Call<MatchResponse> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });

    }

    public void getTeam(final NBAListener<List<TeamsItem>> listener){
        getNBAAPI().getTeam().enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                TeamResponse data = response.body();
                if (data != null && data.getTeams() != null) {
                    listener.onSuccess(data.getTeams());
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });
    }

    public void getDetailTeam(final NBAListener<List<DetailTeamItem>> listener, String id){
        getNBAAPI().getDetail(id).enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                DetailResponse data = response.body();
                if (data != null && data.getTeams() != null) {
                    listener.onSuccess(data.getTeams());
                }
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });

    }
}
