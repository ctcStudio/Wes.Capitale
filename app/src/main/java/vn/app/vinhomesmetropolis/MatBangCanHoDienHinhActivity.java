package vn.app.vinhomesmetropolis;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import okhttp3.internal.http.StatusLine;
import uk.co.senab.photoview.IPhotoView;
import vn.app.dcapitale.R;
import vn.app.vinhomesmetropolis.view.TouchImageView;

public class MatBangCanHoDienHinhActivity extends BaseActivity {
    static final int C1_ch1phongngu = 2;
    static final int C1_ch2phongngu = 3;
    static final int C1_ch3phongngu = 4;
    static final int C3_ch2phongngu = 5;
    static final int C3_ch3phongngu = 6;
    static final int C6_ch2phongngu = 0;
    static final int C6_ch3phongngu = 1;
    public static final int DRAWABLE_HEIGHT = 891;
    public static final int DRAWABLE_WIDTH = 1310;
    public static final int[] MAP_X = new int[]{612, 811, 764, 754, 685, 617, 696};
    public static final int[] MAP_Y = new int[]{304, 445, StatusLine.HTTP_TEMP_REDIRECT, 290, 429, 293, 426};
    private static int TOUCH_RANGE;
    private int currentMapIndex = 3;
    ImageView detail;
    int detailId;
    TouchImageView map;
    int mapId;
    ImageView title;
    int titleId;

    class C02481 implements OnTouchListener {
        private static final float DELTA_DRAG_IN_ACEPT = 5.0f;
        private float downX;
        private float downY;
        private int track;

        C02481() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == 0 && event.getPointerCount() == 1) {
                this.downX = event.getX();
                this.downY = event.getY();
            }
            if (event.getAction() == 1 && event.getPointerCount() == 1) {
                float x = event.getX();
                float y = event.getY();
                if (Math.abs(x - this.downX) <= DELTA_DRAG_IN_ACEPT && Math.abs(y - this.downY) <= DELTA_DRAG_IN_ACEPT) {
                    float inViewX;
                    this.track++;
                    Log.e("XMOB", "---------------------------Track:" + this.track);
                    Log.v("XMOB", "Size Image:" + MatBangCanHoDienHinhActivity.this.map.getImageWidth() + "-" + MatBangCanHoDienHinhActivity.this.map.getImageHeight());
                    Log.v("XMOB", "Size View:" + MatBangCanHoDienHinhActivity.this.map.getWidth() + "-" + MatBangCanHoDienHinhActivity.this.map.getHeight());
                    Log.v("XMOB", "Zoom scale:" + MatBangCanHoDienHinhActivity.this.map.getCurrentZoom());
                    Log.v("XMOB", String.format("Touch Position :%.02f-%.02f", new Object[]{Float.valueOf(x), Float.valueOf(y)}));
                    RectF rect = MatBangCanHoDienHinhActivity.this.map.getZoomedRect();
                    Log.v("XMOB", String.format("Letf:%.02f - Top:%.02f - Right:%.02f - Bottom:%.02f", new Object[]{Float.valueOf(rect.left), Float.valueOf(rect.top), Float.valueOf(rect.right), Float.valueOf(rect.bottom)}));
                    if (rect.left == 0.0f && rect.right == IPhotoView.DEFAULT_MIN_SCALE) {
                        Log.v("XMOB", "in Bound!");
                        inViewX = (x - ((((float) MatBangCanHoDienHinhActivity.this.map.getWidth()) - MatBangCanHoDienHinhActivity.this.map.getImageWidth()) / 2.0f)) / MatBangCanHoDienHinhActivity.this.map.getCurrentZoom();
                    } else {
                        Log.v("XMOB", "out Bound!");
                        inViewX = ((rect.left * MatBangCanHoDienHinhActivity.this.map.getImageWidth()) + x) / MatBangCanHoDienHinhActivity.this.map.getCurrentZoom();
                    }
                    float inDrawableX = (891.0f * inViewX) / ((float) MatBangCanHoDienHinhActivity.this.map.getHeight());
                    float inDrawableY = (891.0f * (((rect.top * MatBangCanHoDienHinhActivity.this.map.getImageHeight()) + y) / MatBangCanHoDienHinhActivity.this.map.getCurrentZoom())) / ((float) MatBangCanHoDienHinhActivity.this.map.getHeight());
                    Log.v("XMOB", "Real Position :" + inDrawableX + " - " + inDrawableY);
                    if (((((float) MatBangCanHoDienHinhActivity.MAP_X[MatBangCanHoDienHinhActivity.this.currentMapIndex]) - inDrawableX) * (((float) MatBangCanHoDienHinhActivity.MAP_X[MatBangCanHoDienHinhActivity.this.currentMapIndex]) - inDrawableX)) + ((((float) MatBangCanHoDienHinhActivity.MAP_Y[MatBangCanHoDienHinhActivity.this.currentMapIndex]) - inDrawableY) * (((float) MatBangCanHoDienHinhActivity.MAP_Y[MatBangCanHoDienHinhActivity.this.currentMapIndex]) - inDrawableY)) <= ((float) (MatBangCanHoDienHinhActivity.TOUCH_RANGE * MatBangCanHoDienHinhActivity.TOUCH_RANGE))) {
                        Log.e("XMOB", "Fire!");
                        MatBangCanHoDienHinhActivity.this.showPano(MatBangCanHoDienHinhActivity.this.currentMapIndex);
                    }
                }
            }
            return false;
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matbangcanho);
        TOUCH_RANGE = getResources().getDimensionPixelSize(R.dimen.touch_range);
        this.mapId = getIntent().getIntExtra("map_id", R.drawable.matbangcanho_map_c1_ch1phongngu);
        this.titleId = getIntent().getIntExtra("title_id", R.drawable.matbangcanho_title_1phongngu);
        this.detailId = getIntent().getIntExtra("detail_id", R.drawable.matbangcanho_detail_c1_ch1phongngu);
        switch (this.mapId) {
            case R.drawable.matbangcanho_map_c1_ch1phongngu:
                this.currentMapIndex = 2;
                break;
            case R.drawable.matbangcanho_map_c1_ch2phongngu:
                this.currentMapIndex = 3;
                break;
            case R.drawable.matbangcanho_map_c1_ch3phongngu:
                this.currentMapIndex = 4;
                break;
            case R.drawable.matbangcanho_map_c3_ch2phongngu:
                this.currentMapIndex = 5;
                break;
            case R.drawable.matbangcanho_map_c3_ch3phongngu:
                this.currentMapIndex = 6;
                break;
            case R.drawable.matbangcanho_map_c6_ch2phongngu:
                this.currentMapIndex = 0;
                break;
            case R.drawable.matbangcanho_map_c6_ch3phongngu:
                this.currentMapIndex = 1;
                break;
        }
        this.map = (TouchImageView) findViewById(R.id.matbangcanho_map);
        this.title = (ImageView) findViewById(R.id.matbangcanho_text);
        this.detail = (ImageView) findViewById(R.id.matbangcanho_detail);
        this.map.setImageResource(this.mapId);
        this.title.setImageResource(this.titleId);
        this.detail.setImageResource(this.detailId);
        this.map.setOnTouchListener(new C02481());
    }

    private void checkTouch(MotionEvent event) {
        Point p = TouchHelper.realTouch(this.map, event, DRAWABLE_WIDTH, DRAWABLE_HEIGHT);
        if (TouchHelper.inRange(MAP_X[this.currentMapIndex], MAP_Y[this.currentMapIndex], p.x, p.y, TOUCH_RANGE)) {
            showPano(this.currentMapIndex);
        }
    }

    private void showPano(int index) {
        String link = null;
        switch (index) {
            case 0:
            case 3:
            case 5:
                link = "https://my.matterport.com/show/?m=rN1haKLjeM8&play=1";
                break;
            case 1:
            case 4:
            case 6:
                link = "https://my.matterport.com/show/?m=LwbDdDuiRoL&play=1";
                break;
            case 2:
                link = "https://my.matterport.com/show/?m=CEjAwjsVgPZ&play=1";
                break;
        }
        Intent intent = new Intent(this, VirtualTourActivity.class);
        intent.putExtra("url", link);
        intent.putExtra(VirtualTourActivity.PARAM_SHOW_OPTION, false);
        startActivity(intent);
        int i = this.mapId;
    }

    private boolean inRange(int x, int y, int touchX, int touchY) {
        return ((x - touchX) * (x - touchX)) + ((y - touchY) * (y - touchY)) < TOUCH_RANGE * TOUCH_RANGE;
    }

    public boolean hasBackButton() {
        return true;
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.matbangcanho_button_phongkhachbep:
                Intent phongkhachbepIntent = new Intent(this, VirtualTourActivity.class);
                phongkhachbepIntent.putExtra("url", "https://my.matterport.com/show/?m=LwbDdDuiRoL&play=1");
                phongkhachbepIntent.putExtra(VirtualTourActivity.PARAM_SHOW_OPTION, false);
                startActivity(phongkhachbepIntent);
                return;
            case R.id.navigation_back:
                finish();
                return;
            default:
                return;
        }
    }
}
