package vn.app.vinhomesmetropolis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import vn.app.dcapitale.R;

public class KhoiCanHoActivity extends BaseActivity {
    private static final int[] POS_X = new int[]{434, 498, 643, 746};
    private static final int[] POS_Y = new int[]{633, 517, 560, 792};
    public static final int TOUCH_RANGE = 50;
    private static final String[] VIEW = new String[]{"s17.html", "s16.html", "s15.html", "s14.html"};
    public int IMAGE_REAL_HEIGHT = 1080;
    public int IMAGE_REAL_WIDTH = 1124;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoicanho);
    }

    public boolean hasBackButton() {
        return true;
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.khoicanho_button_c1:
                Intent c1Intent = new Intent(this, VirtualTourActivity.class);
                c1Intent.putExtra(VirtualTourActivity.PARAM_FILE, "s39.html");
                c1Intent.putExtra(VirtualTourActivity.PARAM_SHOW_OPTION, false);
                startActivity(c1Intent);
                return;
            case R.id.khoicanho_button_c3:
                Intent giangvoIntent = new Intent(this, VirtualTourActivity.class);
                giangvoIntent.putExtra(VirtualTourActivity.PARAM_FILE, "s16.html");
                giangvoIntent.putExtra(VirtualTourActivity.PARAM_SHOW_OPTION, false);
                startActivity(giangvoIntent);
                return;
            case R.id.khoicanho_button_c5:
                Intent ngockhanhIntent = new Intent(this, VirtualTourActivity.class);
                ngockhanhIntent.putExtra(VirtualTourActivity.PARAM_FILE, "s18.html");
                ngockhanhIntent.putExtra(VirtualTourActivity.PARAM_SHOW_OPTION, false);
                startActivity(ngockhanhIntent);
                return;
            case R.id.khoicanho_button_c6:
                Intent thuleIntent = new Intent(this, VirtualTourActivity.class);
                thuleIntent.putExtra(VirtualTourActivity.PARAM_FILE, "s20.html");
                thuleIntent.putExtra(VirtualTourActivity.PARAM_SHOW_OPTION, false);
                startActivity(thuleIntent);
                return;
            case R.id.khoicanho_button_c7:
                Intent hotayIntent = new Intent(this, VirtualTourActivity.class);
                hotayIntent.putExtra(VirtualTourActivity.PARAM_FILE, "s22.html");
                hotayIntent.putExtra(VirtualTourActivity.PARAM_SHOW_OPTION, false);
                startActivity(hotayIntent);
                return;
            case R.id.navigation_back:
                finish();
                return;
            default:
                return;
        }
    }
}
