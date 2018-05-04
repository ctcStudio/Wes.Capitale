package vn.app.vinhomesmetropolis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import vn.app.dcapitale.R;

public abstract class BaseActivity extends AppCompatActivity implements OnClickListener {
    public abstract boolean hasBackButton();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUi();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(5894);
        }
    }

    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        if (hasBackButton()) {
            try {
                View view = findViewById(R.id.navigation_back);
                if (view != null) {
                    view.setVisibility(0);
                }
            } catch (Exception e) {
                AppTracker.error((Object) this, e);
            }
        }
    }

    protected void onPause() {
        super.onPause();
        MusicManager.iAmLeaving();
    }

    protected void onResume() {
        super.onResume();
        try {
            MusicManager.iAmIn();
            setupMediaController();
        } catch (Exception e) {
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navigation_info:
                if (!(this instanceof CreditActivity)) {
                    startActivity(new Intent(this, CreditActivity.class));
                    return;
                }
                return;
            case R.id.navigation_home:
                if (!(this instanceof HomeActivity)) {
                    Intent homeIntent = new Intent(this, HomeActivity.class);
                    homeIntent.setFlags(268468224);
                    startActivity(homeIntent);
                    return;
                }
                return;
            case R.id.navigation_sound:
                if (MusicManager.isPlaying()) {
                    MusicManager.iAmPause();
                    setupMediaController();
                    return;
                }
                MusicManager.iAmResume();
                setupMediaController();
                return;
            default:
                return;
        }
    }

    public void setupMediaController() {
        ImageView control = (ImageView) findViewById(R.id.navigation_sound);
        int soundImageType = 0;
        if (findViewById(R.id.layout_navigation_black) != null) {
            soundImageType = 1;
        } else if (findViewById(R.id.layout_navigation_white) != null) {
            soundImageType = 2;
        }
        if (MusicManager.isPlaying()) {
            if (soundImageType == 1) {
                control.setImageResource(R.drawable.ic_navigation_sound_on_black);
            } else if (soundImageType == 2) {
                control.setImageResource(R.drawable.ic_navigation_sound_on_white);
            }
        } else if (soundImageType == 1) {
            control.setImageResource(R.drawable.ic_navigation_sound_off_black);
        } else if (soundImageType == 2) {
            control.setImageResource(R.drawable.ic_navigation_sound_off_white);
        }
    }

    private void hideSystemUi() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }
}
