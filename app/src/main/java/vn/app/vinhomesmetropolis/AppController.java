package vn.app.vinhomesmetropolis;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.liulishuo.filedownloader.FileDownloader;
import java.io.File;
import vn.app.dcapitale.R;

public class AppController extends Application {
    public static final String CHECK_DATA = "checkdata";
    public static final String DATA_FILE = "data.zip";
    static final int DATA_MISS = 0;
    public static final String DATA_PANO = "mobile";
    static final int DATA_READY = 1;
    public static final String DATA_RESOURCE = "http://vnimation.xmob.vn/DCapitale/app/data.zip";
    static final int DATA_ZIP = 2;
    public static String ROOT_FOLDER;
    private static AppController mInstance;
    private SharedPreferences preference;

    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ROOT_FOLDER = getFilesDir().getParent();
        FileDownloader.init(getApplicationContext());
        this.preference = getSharedPreferences(getString(R.string.app_preference), 0);
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "utmbebas.ttf");
    }

    public static synchronized AppController getInstance() {
        AppController appController;
        synchronized (AppController.class) {
            appController = mInstance;
        }
        return appController;
    }

    public static void initFolder() {
        try {
            File file = new File(ROOT_FOLDER);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            AppTracker.error("AppController", e);
        }
    }

    public void DeleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory != null) {
            if (fileOrDirectory.isDirectory()) {
                for (File child : fileOrDirectory.listFiles()) {
                    DeleteRecursive(child);
                }
            }
            fileOrDirectory.delete();
        }
    }

    public boolean isDataZip() {
        return getDataStatus() == 2 && new File(ROOT_FOLDER, DATA_FILE).exists();
    }

    public boolean isDataReady() {
        File data = new File(ROOT_FOLDER);
        if (getDataStatus() != 1 || !data.isDirectory() || data.listFiles() == null || data.listFiles().length <= 0) {
            return false;
        }
        return true;
    }

    public void setDataStatus(int status) {
        Editor editor = this.preference.edit();
        editor.putInt(CHECK_DATA, status);
        editor.apply();
    }

    public int getDataStatus() {
        return this.preference.getInt(CHECK_DATA, 0);
    }
}
