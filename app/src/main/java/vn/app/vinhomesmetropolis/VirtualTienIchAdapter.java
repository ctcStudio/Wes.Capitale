package vn.app.vinhomesmetropolis;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import vn.app.dcapitale.R;

public class VirtualTienIchAdapter extends Adapter<TienIchViewHolder> {
    public static final String[] LIST_FILE = new String[]{"s34.html", "s5.html", "s6.html", "s7.html", "s35.html", "s36.html", "s8.html", "s9.html", "s11.html", "s37.html", "s38.html", "s13.html", "s39.html", "s14.html", "s15.html", "s16.html", "s17.html", "s19.html", "s18.html", "s20.html", "s22.html", "s12.html", "s40.html", "s21.html", "s4.html", "s3.html", "s10.html"};
    public static final int[] LIST_IMAGE = new int[]{R.drawable.s34, R.drawable.s5, R.drawable.s6, R.drawable.s7, R.drawable.s35, R.drawable.s36, R.drawable.s8, R.drawable.s9, R.drawable.s11, R.drawable.s37, R.drawable.s38, R.drawable.s13, R.drawable.s39, R.drawable.s14, R.drawable.s15, R.drawable.s16, R.drawable.s17, R.drawable.s19, R.drawable.s18, R.drawable.s20, R.drawable.s22, R.drawable.s12, R.drawable.s40, R.drawable.s21, R.drawable.s4, R.drawable.s3, R.drawable.s10};
    private Context context;
    private OnClickListener mListener = new C03541();

    public interface OnClickListener {
        void onClick(int i);
    }

    class C03541 implements OnClickListener {
        C03541() {
        }

        public void onClick(int position) {
            try {
                VirtualTourActivity activity = (VirtualTourActivity) VirtualTienIchAdapter.this.context;
                activity.loadUrlFromFile(VirtualTienIchAdapter.LIST_FILE[position]);
                activity.hideOptionLayout();
            } catch (Exception e) {
                AppTracker.error((Object) this, e);
            }
        }
    }

    public static class TienIchViewHolder extends ViewHolder implements View.OnClickListener {
        public ImageView image;
        OnClickListener listener;

        public TienIchViewHolder(View itemView, OnClickListener mListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.image = (ImageView) itemView;
            this.listener = mListener;
        }

        public void onClick(View view) {
            if (this.listener != null) {
                this.listener.onClick(getAdapterPosition());
            }
        }
    }

    public VirtualTienIchAdapter(Context mContext) {
        this.context = mContext;
    }

    public TienIchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TienIchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_in_360, parent, false), this.mListener);
    }

    public void onBindViewHolder(TienIchViewHolder holder, int position) {
        holder.image.setImageResource(LIST_IMAGE[position]);
    }

    public int getItemCount() {
        return LIST_IMAGE.length;
    }
}
