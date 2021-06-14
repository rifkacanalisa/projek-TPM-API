package com.example.api.model.team;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TeamResponse{

	@SerializedName("teams")
	private List<TeamsItem> teams;

	public void setTeams(List<TeamsItem> teams){
		this.teams = teams;
	}

	public List<TeamsItem> getTeams(){
		return teams;
	}

	@Override
 	public String toString(){
		return 
			"TeamResponse{" + 
			"teams = '" + teams + '\'' + 
			"}";
		}
}