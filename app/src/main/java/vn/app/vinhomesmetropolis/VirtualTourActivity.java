package vn.app.vinhomesmetropolis;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.File;
import vn.app.dcapitale.R;

public class VirtualTourActivity extends BaseActivity {
    public static final String PARAM_FILE = "file";
    public static final String PARAM_SHOW_OPTION = "show_option";
    public static final String PARAM_URL = "url";
    private static final int TIENICH_COLUMN = 4;
    View buttonNoiThat;
    View buttonTienIch;
    View buttonTongThe;
    View currentPickOption;
    View currentShowLayout;
    View layoutNoiThat;
    View layoutTienIch;
    View layoutTongThe;
    View noithat1PhongNgu;
    View noithat2PhongNgu;
    View noithat3PhongNgu;
    RecyclerView tienichList;
    View tongtheDem;
    View tongtheNgay;
    WebView webview;

    class C02531 implements OnTouchListener {
        C02531() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == 0 && VirtualTourActivity.this.currentShowLayout != null) {
                VirtualTourActivity.this.hideOptionLayout();
            }
            return false;
        }
    }

    class C02542 implements OnClickListener {
        C02542() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Intent intent = new Intent(VirtualTourActivity.this, HomeActivity.class);
            intent.setFlags(268468224);
            VirtualTourActivity.this.startActivity(intent);
            MusicManager.keepMusicOn();
            VirtualTourActivity.this.finish();
        }
    }

    public class MyWebViewClient extends WebViewClient {
        public MyWebViewClient(Context context) {
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_360);
        this.webview = (WebView) findViewById(R.id.webview);
        this.buttonNoiThat = findViewById(R.id.button_noithat);
        this.buttonTienIch = findViewById(R.id.button_tienich);
        this.buttonTongThe = findViewById(R.id.button_tongthe);
        this.layoutNoiThat = findViewById(R.id.layout_noithat);
        this.layoutTienIch = findViewById(R.id.layout_tienich);
        this.layoutTongThe = findViewById(R.id.layout_tongthe);
        this.noithat1PhongNgu = findViewById(R.id.button_noithat_1phongngu);
        this.noithat2PhongNgu = findViewById(R.id.button_noithat_2phongngu);
        this.noithat3PhongNgu = findViewById(R.id.button_noithat_3phongngu);
        this.tienichList = (RecyclerView) findViewById(R.id.list_tienich);
        this.tongtheNgay = findViewById(R.id.button_tongthe_ngay);
        this.tongtheDem = findViewById(R.id.button_tongthe_dem);
        initView();
        this.webview.setOnTouchListener(new C02531());
        this.webview.setWebChromeClient(new WebChromeClient());
        this.webview.setWebViewClient(new MyWebViewClient(this));
        WebSettings settings = this.webview.getSettings();
        settings.setUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setGeolocationEnabled(true);
        String fileName = getIntent().getStringExtra(PARAM_FILE);
        String url = getIntent().getStringExtra("url");
        boolean showOption = getIntent().getBooleanExtra(PARAM_SHOW_OPTION, true);
        if (fileName == null) {
            fileName = "s1.html";
        }
        if (!showOption) {
            findViewById(R.id.layout_button).setVisibility(8);
        }
        if (url == null || url.isEmpty()) {
            loadUrlFromFile(fileName);
        } else {
            loadUrl(url);
        }
    }

    private void initView() {
        this.currentPickOption = null;
        this.currentShowLayout = null;
        this.layoutTongThe.setVisibility(8);
        this.layoutNoiThat.setVisibility(8);
        this.layoutTienIch.setVisibility(8);
        this.tienichList.setHasFixedSize(true);
        this.tienichList.setLayoutManager(new GridLayoutManager(this, 4));
        this.tienichList.setAdapter(new VirtualTienIchAdapter(this));
    }

    public void loadUrlFromFile(String file) {
        String filePath = AppController.ROOT_FOLDER + "/" + AppController.DATA_PANO + "/" + file;
        AppTracker.log((Object) this, "Load Pano:" + filePath);
        if (new File(filePath).exists()) {
            this.webview.loadUrl("file://" + filePath);
            return;
        }
        AppController.getInstance().setDataStatus(0);
        new Builder(this).setMessage((CharSequence) "Dữ liệu không tồn tại. Bạn có muốn tải lại dữ liệu không?").setPositiveButton((CharSequence) "Đồng ý", new C02542()).setNegativeButton((CharSequence) "Hủy bỏ", null).show();
    }

    public void loadUrl(String url) {
        this.webview.loadUrl(url);
    }

    public boolean hasBackButton() {
        return true;
    }

    public void onBackPressed() {
        if (!hideOptionLayout()) {
            super.onBackPressed();
        }
    }

    public boolean hideOptionLayout() {
        if (this.currentShowLayout == null) {
            return false;
        }
        this.currentShowLayout.setVisibility(8);
        this.currentPickOption.setSelected(false);
        this.currentShowLayout = null;
        this.currentPickOption = null;
        return true;
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.button_noithat:
                hideOptionLayout();
                this.buttonNoiThat.setSelected(true);
                this.layoutNoiThat.setVisibility(0);
                this.currentShowLayout = this.layoutNoiThat;
                this.currentPickOption = this.buttonNoiThat;
                return;
            case R.id.button_tienich:
                hideOptionLayout();
                this.buttonTienIch.setSelected(true);
                this.layoutTienIch.setVisibility(0);
                this.currentShowLayout = this.layoutTienIch;
                this.currentPickOption = this.buttonTienIch;
                return;
            case R.id.button_tongthe:
                hideOptionLayout();
                this.buttonTongThe.setSelected(true);
                this.layoutTongThe.setVisibility(0);
                this.currentShowLayout = this.layoutTongThe;
                this.currentPickOption = this.buttonTongThe;
                return;
            case R.id.button_tongthe_ngay:
                hideOptionLayout();
                loadUrlFromFile("s1.html");
                return;
            case R.id.button_tongthe_dem:
                hideOptionLayout();
                loadUrlFromFile("s2.html");
                return;
            case R.id.button_noithat_1phongngu:
                Intent ch1phongnguIntent = new Intent(this, MatBangCanHoDienHinhActivity.class);
                ch1phongnguIntent.putExtra("title_id", R.drawable.matbangcanho_title_1phongngu);
                ch1phongnguIntent.putExtra("detail_id", R.drawable.matbangcanho_detail_c1_ch1phongngu);
                ch1phongnguIntent.putExtra("map_id", R.drawable.matbangcanho_map_c1_ch1phongngu);
                startActivity(ch1phongnguIntent);
                return;
            case R.id.button_noithat_2phongngu:
                Intent ch2phongnguIntent = new Intent(this, MatBangCanHoDienHinhActivity.class);
                ch2phongnguIntent.putExtra("title_id", R.drawable.matbangcanho_title_2phongngu);
                ch2phongnguIntent.putExtra("detail_id", R.drawable.matbangcanho_detail_c1_ch2phongngu);
                ch2phongnguIntent.putExtra("map_id", R.drawable.matbangcanho_map_c1_ch2phongngu);
                startActivity(ch2phongnguIntent);
                return;
            case R.id.button_noithat_3phongngu:
                Intent ch3phongnguIntent = new Intent(this, MatBangCanHoDienHinhActivity.class);
                ch3phongnguIntent.putExtra("title_id", R.drawable.matbangcanho_title_3phongngu);
                ch3phongnguIntent.putExtra("detail_id", R.drawable.matbangcanho_detail_c1_ch3phongngu);
                ch3phongnguIntent.putExtra("map_id", R.drawable.matbangcanho_map_c1_ch3phongngu);
                startActivity(ch3phongnguIntent);
                return;
            case R.id.navigation_back:
                if (!hideOptionLayout()) {
                    finish();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
