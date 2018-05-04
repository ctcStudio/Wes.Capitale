package vn.app.vinhomesmetropolis;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import vn.app.dcapitale.R;

public class MatBangChonCanActivity extends BaseActivity {
    public static final int[] POS_X = new int[]{Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 815};
    public static final int[] POS_Y = new int[]{610, 209};
    public int IMAGE_REAL_HEIGHT = 886;
    public int IMAGE_REAL_WIDTH = 1180;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matbangchoncan);
        final ImageView image = (ImageView) findViewById(R.id.matbangchoncan_chieudung);
        image.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    Point p = TouchHelper.realTouch(image, motionEvent, MatBangChonCanActivity.this.IMAGE_REAL_WIDTH, MatBangChonCanActivity.this.IMAGE_REAL_HEIGHT);
                    if (TouchHelper.inRange(MatBangChonCanActivity.POS_X[0], MatBangChonCanActivity.POS_Y[0], p.x, p.y, 50)) {
                        MatBangChonCanActivity.this.startC1();
                    } else if (TouchHelper.inRange(MatBangChonCanActivity.POS_X[1], MatBangChonCanActivity.POS_Y[1], p.x, p.y, 50)) {
                        MatBangChonCanActivity.this.startC6();
                    }
                }
                return false;
            }
        });
    }

    public boolean hasBackButton() {
        return true;
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.button_c1:
                startC1();
                return;
            case R.id.button_c3:
                startC3();
                return;
            case R.id.button_c6:
                startC6();
                return;
            case R.id.navigation_back:
                finish();
                return;
            default:
                return;
        }
    }

    public void startC3() {
        Intent c1Intent = new Intent(this, MatBangTangDienHinhActivity.class);
        c1Intent.putExtra("id", R.drawable.matbangtangdienhinh_map_c3);
        c1Intent.putExtra("title", R.drawable.matbangtangdienhinh_title_c3);
        startActivity(c1Intent);
    }

    public void startC1() {
        Intent c1Intent = new Intent(this, MatBangTangDienHinhActivity.class);
        c1Intent.putExtra("id", R.drawable.matbangtangdienhinh_map_c1);
        c1Intent.putExtra("title", R.drawable.matbangtangdienhinh_title_c1);
        startActivity(c1Intent);
    }

    public void startC6() {
        Intent c6Intent = new Intent(this, MatBangTangDienHinhActivity.class);
        c6Intent.putExtra("id", R.drawable.matbangtangdienhinh_map_c6);
        c6Intent.putExtra("title", R.drawable.matbangtangdienhinh_title_c6);
        startActivity(c6Intent);
    }
}
