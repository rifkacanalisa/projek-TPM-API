package com.example.api.service;

import com.example.api.model.detailteam.DetailResponse;
import com.example.api.model.match.EventsItem;
import com.example.api.model.match.MatchResponse;
import com.example.api.model.team.TeamResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NBAAPI {
    String URL_BASE = "https://www.thesportsdb.com";

    @GET("/api/v1/json/1/eventspastleague.php?id=4387")
    Call<MatchResponse> getMatch();

    @GET("/api/v1/json/1/lookup_all_teams.php?id=4387")
    Call<TeamResponse> getTeam();

    @GET("/api/v1/json/1/lookupteam.php?")
    Call<DetailResponse> getDetail(@Query("id") String teamID);
}
