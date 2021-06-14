package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.api.model.detailteam.DetailTeamItem;
import com.example.api.model.team.TeamsItem;
import com.example.api.service.NBAService;

import java.util.List;

public class DetailTeamActivity extends AppCompatActivity {

    ImageView ivPict, ivBanner;
    TextView tvName, tvShortName, tvYear, tvStadium, tvDescription;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_team);

        ivPict = findViewById(R.id.iv_detailteam_pict);
        ivBanner = findViewById(R.id.iv_detailteam_banner);
        tvName = findViewById(R.id.tv_detailteam_name);
        tvShortName = findViewById(R.id.tv_detailteam_shortname);
        tvYear = findViewById(R.id.tv_detailteam_year);
        tvStadium = findViewById(R.id.tv_detailteam_stadium);
        tvDescription = findViewById(R.id.tv_detailteam_description);

        Boolean check = checkIncoming();
        if(check.equals(true)){
            id = getIntent().getStringExtra("id");
            new NBAService().getDetailTeam(detailListener, id);
        }
    }

    private Boolean checkIncoming(){
        Boolean check = false;
        if(getIntent().hasExtra("id")){
            check = true;
        }
        return check;
    }


    NBAListener<List<DetailTeamItem>> detailListener = new NBAListener<List<DetailTeamItem>>() {
        @Override
        public void onSuccess(List<DetailTeamItem> items) {
            String stadiumName, combineStadium, locationName, combineLocation;
            String stadium = " Stadium located at ";

            Glide.with(getApplicationContext()).load(items.get(0).getStrTeamBadge()).into(ivPict);
            tvName.setText(items.get(0).getStrTeam());
            tvShortName.setText(items.get(0).getStrTeamShort());
            tvYear.setText(items.get(0).getIntFormedYear());
            Glide.with(getApplicationContext()).load(items.get(0).getStrTeamBanner()).into(ivBanner);
            tvDescription.setText(items.get(0).getStrDescriptionEN());

            stadiumName = items.get(0).getStrStadium();
            locationName = items.get(0).getStrStadiumLocation();
            combineStadium = stadiumName.concat(stadium);
            combineLocation = combineStadium.concat(locationName);
//            Log.d("Hasil : ", combineStadium);
            tvStadium.setText(combineLocation);
        }

        @Override
        public void onFailed(String msg) {
            Log.d("Error : ", msg);
        }
    };
}