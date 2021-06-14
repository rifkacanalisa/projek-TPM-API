package com.example.api.model.detailteam;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailResponse{

	@SerializedName("teams")
	private List<DetailTeamItem> teams;

	public void setTeams(List<DetailTeamItem> teams){
		this.teams = teams;
	}

	public List<DetailTeamItem> getTeams(){
		return teams;
	}

	@Override
 	public String toString(){
		return 
			"DetailResponse{" + 
			"teams = '" + teams + '\'' + 
			"}";
		}
}