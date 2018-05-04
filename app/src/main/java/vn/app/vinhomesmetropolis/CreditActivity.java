package vn.app.vinhomesmetropolis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import vn.app.dcapitale.R;

public class CreditActivity extends BaseActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
    }

    public boolean hasBackButton() {
        return true;
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.navigation_back) {
            finish();
        }
    }
}
