package vn.app.vinhomesmetropolis;

import android.media.MediaPlayer;
import java.io.IOException;
import vn.app.dcapitale.R;

public class MusicManager {
    private static boolean keepMusicOn;
    private static MediaPlayer player;
    private static boolean userPause;

    public static void iAmIn() {
        AppTracker.log("MusicManager", "iAmIn");
        if (player == null) {
            player = MediaPlayer.create(AppController.getInstance().getApplicationContext(), R.raw.background);
            player.setLooping(true);
            try {
                player.prepare();
            } catch (IllegalStateException e) {
            } catch (IOException e2) {
            }
        }
        if (!(player.isPlaying() || userPause)) {
            player.start();
        }
        keepMusicOn = false;
    }

    public static void keepMusicOn() {
        AppTracker.log("MusicManager", "keepMusicOn");
        keepMusicOn = true;
    }

    public static void iAmLeaving() {
        AppTracker.log("MusicManager", "iAmLeaving");
        if (!keepMusicOn && !userPause) {
            player.pause();
        }
    }

    public static void iAmPause() {
        AppTracker.log("MusicManager", "iAmPause");
        if (player != null) {
            userPause = true;
            player.pause();
        }
    }

    public static void iAmResume() {
        AppTracker.log("MusicManager", "iAmResume");
        if (player != null) {
            userPause = false;
            player.start();
        }
    }

    public static boolean isUserPausing() {
        return userPause;
    }

    public static boolean isKeepMusicOn() {
        return keepMusicOn;
    }

    public static boolean isPlaying() {
        AppTracker.log("MusicManager", "isPlaying");
        return player != null && player.isPlaying();
    }
}
