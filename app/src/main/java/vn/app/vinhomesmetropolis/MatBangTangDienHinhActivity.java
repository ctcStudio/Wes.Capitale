package vn.app.vinhomesmetropolis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import vn.app.dcapitale.R;

public class MatBangTangDienHinhActivity extends BaseActivity {
    private int resourceID;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matbangtangdienhinh);
        this.resourceID = getIntent().getIntExtra("id", R.drawable.matbangtangdienhinh_map_c1);
        int titleID = getIntent().getIntExtra("title", R.drawable.matbangtangdienhinh_title_c1);
        ((ImageView) findViewById(R.id.matbang_map)).setImageResource(this.resourceID);
        ((ImageView) findViewById(R.id.matbangtangdienhinh_title)).setImageResource(titleID);
        if (this.resourceID == R.drawable.matbangtangdienhinh_map_c6 || this.resourceID == R.drawable.matbangtangdienhinh_map_c3) {
            findViewById(R.id.button_ch1phongngu).setVisibility(8);
        }
    }

    public boolean hasBackButton() {
        return true;
    }

    public void onClick(View view) {
        super.onClick(view);
        int mapId;
        switch (view.getId()) {
            case R.id.button_ch1phongngu:
                Intent ch1phongnguIntent = new Intent(this, MatBangCanHoDienHinhActivity.class);
                ch1phongnguIntent.putExtra("title_id", R.drawable.matbangcanho_title_1phongngu);
                ch1phongnguIntent.putExtra("detail_id", R.drawable.matbangcanho_detail_c1_ch1phongngu);
                ch1phongnguIntent.putExtra("map_id", R.drawable.matbangcanho_map_c1_ch1phongngu);
                startActivity(ch1phongnguIntent);
                return;
            case R.id.button_ch2phongngu:
                switch (this.resourceID) {
                    case R.drawable.matbangtangdienhinh_map_c1:
                        mapId = R.drawable.matbangcanho_map_c1_ch2phongngu;
                        break;
                    case R.drawable.matbangtangdienhinh_map_c3:
                        mapId = R.drawable.matbangcanho_map_c3_ch2phongngu;
                        break;
                    default:
                        mapId = R.drawable.matbangcanho_map_c6_ch2phongngu;
                        break;
                }
                Intent ch2phongnguIntent = new Intent(this, MatBangCanHoDienHinhActivity.class);
                ch2phongnguIntent.putExtra("title_id", R.drawable.matbangcanho_title_2phongngu);
                ch2phongnguIntent.putExtra("detail_id", R.drawable.matbangcanho_detail_c1_ch2phongngu);
                ch2phongnguIntent.putExtra("map_id", mapId);
                startActivity(ch2phongnguIntent);
                return;
            case R.id.button_ch3phongngu:
                switch (this.resourceID) {
                    case R.drawable.matbangtangdienhinh_map_c1:
                        mapId = R.drawable.matbangcanho_map_c1_ch3phongngu;
                        break;
                    case R.drawable.matbangtangdienhinh_map_c3:
                        mapId = R.drawable.matbangcanho_map_c3_ch3phongngu;
                        break;
                    default:
                        mapId = R.drawable.matbangcanho_map_c6_ch3phongngu;
                        break;
                }
                Intent ch3phongnguIntent = new Intent(this, MatBangCanHoDienHinhActivity.class);
                ch3phongnguIntent.putExtra("title_id", R.drawable.matbangcanho_title_3phongngu);
                ch3phongnguIntent.putExtra("detail_id", R.drawable.matbangcanho_detail_c1_ch3phongngu);
                ch3phongnguIntent.putExtra("map_id", mapId);
                startActivity(ch3phongnguIntent);
                return;
            case R.id.navigation_back:
                finish();
                return;
            default:
                return;
        }
    }
}
