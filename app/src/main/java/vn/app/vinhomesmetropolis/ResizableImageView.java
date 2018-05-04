package vn.app.vinhomesmetropolis;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class ResizableImageView extends ImageView {
    public ResizableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable d = getDrawable();
        LayoutParams params = getLayoutParams();
        if (d == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else if (params.width == -1 && params.height == -2) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            setMeasuredDimension(width, (int) Math.ceil((double) ((((float) width) * ((float) d.getIntrinsicHeight())) / ((float) d.getIntrinsicWidth()))));
        } else if (params.height == -1 && params.width == -2) {
            int height = MeasureSpec.getSize(heightMeasureSpec);
            setMeasuredDimension((int) Math.ceil((double) ((((float) height) * ((float) d.getIntrinsicWidth())) / ((float) d.getIntrinsicHeight()))), height);
        }
    }
}
