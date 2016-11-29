package top.est7.muggle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.est7.muggle.CommonView.SecondOrderBezier;

public class MuggleActivity extends AppCompatActivity {

    @BindView(R.id.beisaier_view)
    SecondOrderBezier mBeisaierView;
    @BindView(R.id.activity_muggle)
    LinearLayout mActivityMuggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muggle);
        ButterKnife.bind(this);

    }

}
