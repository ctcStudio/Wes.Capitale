package vn.app.vinhomesmetropolis;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import vn.app.dcapitale.R;

public class HomeActivity extends BaseActivity {

    class C02461 implements OnClickListener {
        C02461() {
        }

        public void onClick(DialogInterface dialog, int which) {
            HomeActivity.this.finish();
        }
    }

    class C02472 implements OnClickListener {
        C02472() {
        }

        public void onClick(DialogInterface dialog, int which) {
            DataHelper.download(HomeActivity.this);
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
        initData();
        initView();
    }

    private void initData() {
        AppController.initFolder();
        if (AppController.getInstance().isDataZip()) {
            DataHelper.unpackZip(this);
        } else if (!AppController.getInstance().isDataReady()) {
            new Builder(this).setMessage((int) R.string.policy).setPositiveButton((int) R.string.button_download, new C02472()).setNegativeButton((int) R.string.button_exit_app, new C02461()).show();
        }
    }

    private void initView() {
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.button_home_phim:
                MusicManager.keepMusicOn();
                startActivity(new Intent(this, VideoActivity.class));
                return;
            case R.id.button_home_bando:
                MusicManager.keepMusicOn();
                startActivity(new Intent(this, BanDoDuAnActivity.class));
                return;
            case R.id.button_home_360:
                MusicManager.keepMusicOn();
                startActivity(new Intent(this, VirtualTourActivity.class));
                return;
            case R.id.button_home_canho:
                MusicManager.keepMusicOn();
                startActivity(new Intent(this, KhoiCanHoActivity.class));
                return;
            case R.id.button_home_matbang:
                MusicManager.keepMusicOn();
                startActivity(new Intent(this, MatBangChonCanActivity.class));
                return;
            case R.id.navigation_back:
                finish();
                return;
            default:
                return;
        }
    }

    public boolean hasBackButton() {
        return false;
    }
}
