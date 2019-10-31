package sk.furmi.myapplication;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import sk.furmi.myapplication.models.Projectile;

public class AnimationActivity extends Activity {

    private List<Projectile> dataReceived;
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_animation);
            Intent intent = getIntent();
            Bundle args = intent.getBundleExtra("BUNDLE");
            dataReceived = (List<Projectile>) args.getSerializable("ARRAYLIST");
            Button button = (Button) findViewById(R.id.bounceBallButton);

            ImageView image = (ImageView) findViewById(R.id.bounceBallImage);
            image.setOnClickListener(MyOnClickListener);
        }
    View.OnClickListener MyOnClickListener =
            new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    moveit(v, dataReceived);
                }

            };

    private void moveit(final View view, List<Projectile> dataReceived) {

        float x = view.getX();
        float y = view.getY();
        Path path = new Path();

        path.moveTo(x + 0, y + 0);
        for (Projectile projectile : dataReceived){
            path.lineTo(projectile.getxPos() + 100, projectile.getyPos() + 150);
        }


        ObjectAnimator objectAnimator =
                ObjectAnimator.ofFloat(view, View.X,
                        View.Y, path);
        objectAnimator.setDuration((long) (dataReceived.get(dataReceived.size()-1).getTimeVal()*1000));
        objectAnimator.start();
    }

    }