package wwh.com.constraintlayoutset;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.transition.ChangeBounds;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnticipateInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ConstraintSet mConstraintSet1 = new ConstraintSet(); // create a Constraint Set
    ConstraintSet mConstraintSet2 = new ConstraintSet(); // create a Constraint Set
    ConstraintSet mConstraintSet3 = new ConstraintSet(); // create a Constraint Set
    ConstraintLayout mainLayout;
    ImageView imageView;
    boolean mOld = false;
    boolean mIvOld = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainLayout = findViewById(R.id.main_layout);
        imageView = findViewById(R.id.icon_iv);
        mConstraintSet1.clone(mainLayout);
        mConstraintSet2.clone(this,R.layout.content_main);
        mConstraintSet2.constrainHeight(R.id.icon_iv,10);
        mConstraintSet3.clone(mainLayout);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置动画速度
                Transition transition= new ChangeBounds();
                transition.setInterpolator(new AnticipateInterpolator(1f));
                TransitionManager.beginDelayedTransition(mainLayout,transition);
                if(mOld = !mOld) {
                    mConstraintSet2.applyTo(mainLayout);
                }else{
                    mConstraintSet1.applyTo(mainLayout);
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(mainLayout);
                if(mIvOld = !mIvOld) {
                    mConstraintSet3.setVisibility(R.id.title_tv,View.GONE);
                    mConstraintSet3.clear(R.id.icon_iv);
                    mConstraintSet3.connect(R.id.icon_iv,ConstraintSet.LEFT,R.id.main_layout,ConstraintSet.LEFT,0);
                    mConstraintSet3.connect(R.id.icon_iv,ConstraintSet.RIGHT,R.id.main_layout,ConstraintSet.RIGHT,0);
                    mConstraintSet3.connect(R.id.icon_iv,ConstraintSet.TOP,R.id.main_layout,ConstraintSet.TOP,0);
                    mConstraintSet3.connect(R.id.icon_iv,ConstraintSet.BOTTOM,R.id.main_layout,ConstraintSet.BOTTOM,0);
                    mConstraintSet3.applyTo(mainLayout);

                }else{
                    mConstraintSet1.applyTo(mainLayout);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
