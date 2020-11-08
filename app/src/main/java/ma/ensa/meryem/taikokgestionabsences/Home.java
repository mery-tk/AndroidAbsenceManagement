package ma.ensa.meryem.taikokgestionabsences;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    View first,second,third,fourth,fifth,sixth;
    TextView a,slogan;

  //Animations
  Animation topAnimation,bottomAnimation,middleAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);


        topAnimation=AnimationUtils.loadAnimation(this,R.anim.top_animations);
        bottomAnimation=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        middleAnimation=AnimationUtils.loadAnimation(this,R.anim.middle_animation);


        //Hooks
        first=findViewById(R.id.first_line);
        second=findViewById(R.id.second_line);
        third=findViewById(R.id.third_line);
        fourth=findViewById(R.id.fourth_line);
        fifth=findViewById(R.id.fifth_line);
        sixth=findViewById(R.id.sixth_line);

        a=findViewById(R.id.a);
        slogan=findViewById(R.id.tagLine);

        first.setAnimation(topAnimation);
        second.setAnimation(topAnimation);
        third.setAnimation(topAnimation);
        fourth.setAnimation(topAnimation);
        fifth.setAnimation(topAnimation);
        sixth.setAnimation(topAnimation);

        a.setAnimation(middleAnimation);
        slogan.setAnimation(bottomAnimation);

        final  Handler handler=new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent intent;
                        intent = new Intent(Home.this,Menu1.class);
                        startActivity(intent);
                        finish();
                    }
                }


                , 5000);



    }

}
