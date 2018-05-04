package vn.app.vinhomesmetropolis;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import vn.app.dcapitale.R;

public class DataHelper {
    public static void download(final Context context) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.downloading));
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(1);
        progressDialog.setMax(100);
        progressDialog.show();
        String filenameFull = AppController.ROOT_FOLDER + "/" + AppController.DATA_FILE;
        int downloadID = FileDownloadUtils.generateId(AppController.DATA_RESOURCE, filenameFull);
        FileDownloader.getImpl().create(AppController.DATA_RESOURCE).setPath(filenameFull).setCallbackProgressMinInterval(500).setListener(new FileDownloadListener() {
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                AppTracker.log("Download", "pending");
            }

            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                AppTracker.log("Download", "progress");
                progressDialog.setProgress((int) ((((float) soFarBytes) / ((float) totalBytes)) * 100.0f));
            }

            protected void completed(BaseDownloadTask task) {
                AppTracker.log("Download", "completed");
                AppController.getInstance().setDataStatus(2);
                progressDialog.dismiss();
                DataHelper.unpackZip(context);
            }

            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                AppTracker.log("Download", "paused");
            }

            protected void error(BaseDownloadTask task, Throwable e) {
                AppTracker.log("Download", "error :" + Log.getStackTraceString(e));
                progressDialog.dismiss();
            }

            protected void warn(BaseDownloadTask task) {
                AppTracker.log("Download", "warn");
            }
        }).start();
    }

    public static void unpackZip(final Context context) {
        new AsyncTask<Void, Void, Void>() {
            ProgressDialog dialog;

            protected void onPreExecute() {
                this.dialog = new ProgressDialog(context);
                this.dialog.setCancelable(false);
                this.dialog.setMessage(context.getString(R.string.unziping));
                this.dialog.show();
            }

            protected Void doInBackground(Void... params) {
                String path = AppController.ROOT_FOLDER + "/";
                try {
                    ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(path + AppController.DATA_FILE)));
                    byte[] buffer = new byte[4096];
                    while (true) {
                        ZipEntry ze = zis.getNextEntry();
                        if (ze == null) {
                            break;
                        }
                        String filename = ze.getName();
                        if (ze.isDirectory()) {
                            new File(path + filename).mkdirs();
                        } else {
                            FileOutputStream fout = new FileOutputStream(path + filename);
                            while (true) {
                                int count = zis.read(buffer);
                                if (count == -1) {
                                    break;
                                }
                                fout.write(buffer, 0, count);
                            }
                            fout.close();
                            zis.closeEntry();
                        }
                    }
                    zis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                this.dialog.dismiss();
                AppController.getInstance().setDataStatus(1);
                Toast.makeText(context, R.string.unzip_success, 0).show();
            }
        }.execute(new Void[0]);
    }
}
