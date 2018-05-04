package vn.app.vinhomesmetropolis;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class MyPhotoAttacher extends PhotoViewAttacher implements OnLongClickListener, OnTouchListener {
    public MyPhotoAttacher(ImageView imageView) {
        super(imageView);
    }

    public boolean onLongClick(View v) {
        return false;
    }

    public boolean onTouch(View view, MotionEvent event) {
        Log.d("Touch", "touch happened -" + event.getAction());
        return super.onTouch(view, event);
    }
}
