package vn.app.vinhomesmetropolis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import uk.co.senab.photoview.PhotoViewAttacher;
import vn.app.dcapitale.R;

public class BanDoDuAnActivity extends BaseActivity {
    ImageView cacholancan;
    ImageView currentSelected;
    ImageView giaothong;
    ImageView map;
    PhotoViewAttacher mapAttacher;
    ImageView tienich;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandoduan);
        this.map = (ImageView) findViewById(R.id.map);
        this.tienich = (ImageView) findViewById(R.id.button_tienich);
        this.cacholancan = (ImageView) findViewById(R.id.button_cacholancan);
        this.giaothong = (ImageView) findViewById(R.id.button_giaothong);
        this.mapAttacher = new PhotoViewAttacher(this.map);
        this.mapAttacher.setScaleType(ScaleType.CENTER_CROP);
    }

    public boolean hasBackButton() {
        return true;
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.button_tienich:
                if (this.currentSelected != null) {
                    this.currentSelected.setSelected(false);
                }
                this.tienich.setSelected(true);
                this.currentSelected = this.tienich;
                this.map.setImageResource(R.drawable.bandoduan_background_tienich);
                this.mapAttacher.update();
                return;
            case R.id.button_cacholancan:
                if (this.currentSelected != null) {
                    this.currentSelected.setSelected(false);
                }
                this.cacholancan.setSelected(true);
                this.currentSelected = this.cacholancan;
                this.map.setImageResource(R.drawable.bandoduan_background_ho);
                this.mapAttacher.update();
                return;
            case R.id.button_giaothong:
                if (this.currentSelected != null) {
                    this.currentSelected.setSelected(false);
                }
                this.giaothong.setSelected(true);
                this.currentSelected = this.giaothong;
                this.map.setImageResource(R.drawable.bandoduan_background_giaothong);
                this.mapAttacher.update();
                return;
            case R.id.navigation_back:
                finish();
                return;
            default:
                return;
        }
    }
}
