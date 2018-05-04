package vn.app.vinhomesmetropolis;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.widget.MediaController;
import android.widget.VideoView;
import java.io.File;
import vn.app.dcapitale.R;

public class VideoActivity extends BaseActivity {
    private boolean resumeMusicAfterPlayVideo;
    private VideoView videoView;

    class C02511 implements OnClickListener {
        C02511() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Intent intent = new Intent(VideoActivity.this, HomeActivity.class);
            intent.setFlags(268468224);
            VideoActivity.this.startActivity(intent);
            VideoActivity.this.resumeMusicAfterPlayVideo();
            VideoActivity.this.finish();
        }
    }

    class C02522 implements OnCompletionListener {
        C02522() {
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            VideoActivity.this.resumeMusicAfterPlayVideo();
            VideoActivity.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);
        this.videoView = (VideoView) findViewById(R.id.activity_walkthrough_video);
        String filePath = getFilesDir().getParent() + "/video.mp4";
        File rootFolder = new File(AppController.ROOT_FOLDER);
        AppTracker.log((Object) this, "Folder exist : " + rootFolder.exists() + " file : " + filePath);
        if (rootFolder.exists()) {
            AppTracker.log((Object) this, "Root Folder Size :" + rootFolder.length());
        }
        if (!new File(filePath).exists()) {
            AppController.getInstance().setDataStatus(0);
            new Builder(this).setMessage((int) R.string.error_miss_data).setPositiveButton((int) R.string.button_accept, new C02511()).setNegativeButton((int) R.string.button_cancel, null).show();
        }
        this.videoView.setOnCompletionListener(new C02522());
        this.videoView.setVideoURI(Uri.parse(filePath));
        this.videoView.setMediaController(new MediaController(this));
        this.videoView.requestFocus();
        this.videoView.start();
        this.resumeMusicAfterPlayVideo = MusicManager.isPlaying();
        AppTracker.log((Object) this, "resume when destroy Activity :" + this.resumeMusicAfterPlayVideo);
        MusicManager.iAmPause();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void onBackPressed() {
        resumeMusicAfterPlayVideo();
        super.onBackPressed();
    }

    private void resumeMusicAfterPlayVideo() {
        AppTracker.log((Object) this, "resume Music :" + this.resumeMusicAfterPlayVideo);
        if (this.resumeMusicAfterPlayVideo) {
            MusicManager.iAmResume();
        }
        MusicManager.keepMusicOn();
    }

    public boolean hasBackButton() {
        return false;
    }
}
