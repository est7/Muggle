package top.est7.muggle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextClock;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MuggleActivity extends AppCompatActivity {

    @BindView(R.id.text_clock)
    TextClock mTextClock;
    @BindView(R.id.activity_muggle)
    LinearLayout mActivityMuggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muggle);
        ButterKnife.bind(this);

  }

}
