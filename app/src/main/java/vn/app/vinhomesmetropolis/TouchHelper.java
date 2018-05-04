package vn.app.vinhomesmetropolis;

import android.graphics.Point;
import android.view.MotionEvent;
import android.widget.ImageView;

public class TouchHelper {
    public static boolean inRange(int x, int y, int touchX, int touchY, int TOUCH_RANGE) {
        return ((x - touchX) * (x - touchX)) + ((y - touchY) * (y - touchY)) < TOUCH_RANGE * TOUCH_RANGE;
    }

    public static Point realTouch(ImageView image, MotionEvent event, int realWidth, int realHeight) {
        Point p = new Point();
        int[] viewCoords = new int[2];
        image.getLocationOnScreen(viewCoords);
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();
        p.x = (touchX * realWidth) / image.getWidth();
        p.y = (touchY * realHeight) / image.getHeight();
        AppTracker.log("TouchHelper", "Coordinate :" + viewCoords[0] + " , " + viewCoords[1] + "\nImage Device Size :" + image.getWidth() + "," + image.getHeight() + "\nTouch :" + touchX + "," + touchY + "\nTouch in Real:" + p.x + "," + p.y);
        return p;
    }
}
