package com.example.android.animatordemo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity_XXX";
    private ImageView iv;
    private View hideShowView;
    private LinearLayout container;
    private View upView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);
        hideShowView = findViewById(R.id.hideShowView);
        upView = findViewById(R.id.upView);
        initLayoutTransition();
    }

    private void initLayoutTransition() {
        container = findViewById(R.id.container);
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setDuration(3000);
        // 替换系统的 android:animateLayoutChanges="true"
        container.setLayoutTransition(layoutTransition);

    }

    public void jumpExecption(View view) {
        startActivity(new Intent(getApplicationContext(), ExeceptionActivity.class));
    }

    public void jumpSecondActivity(View view) {
        startActivity(new Intent(getApplicationContext(), SecondActivity.class));
    }


    public void propertyAnimator(View view) {
        // 范围
        ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
        // 周期
        animation.setDuration(1000);
        // 默认是加速后减速  AccelerateDecelerateInterpolator  LinearInterpolator匀速
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
        // animatorTemp   animation
        animation.addUpdateListener( animatorTemp -> {
            float animatedValue = (float) animatorTemp.getAnimatedValue();
            long currentPlayTime = animatorTemp.getCurrentPlayTime();
            Log.i(TAG, "animatedValue: " + animatedValue + ", currentPlayTime:" + currentPlayTime);
        });
    }

    public void propertyAnimator2(View view) {
        MyPoint startValue = new MyPoint(0, 0);
        MyPoint endValue = new MyPoint(100, 100);
        // 范围
        ValueAnimator animation = ValueAnimator.ofObject(new MyTypeEvaluator(), startValue, endValue);
        // 周期
        animation.setDuration(1000);
        // 默认是加速后减速  AccelerateDecelerateInterpolator  LinearInterpolator匀速
        animation.setInterpolator(new LinearInterpolator());
//        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
        // animatorTemp   animation
        animation.addUpdateListener( animatorTemp -> {
            MyPoint animatedValue = (MyPoint) animatorTemp.getAnimatedValue();
            long currentPlayTime = animatorTemp.getCurrentPlayTime();
            Log.i(TAG, "animatedValue: " + animatedValue + ", currentPlayTime:" + currentPlayTime);
        });
    }

    public void deleteSelf(View view) {
        container.removeView(view);
    }

    public void addChildView(View view) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(getApplicationContext());
        tv.setText("我是子view");
        tv.setLayoutParams(layoutParams);
        container.addView(tv, 0);
    }

    public void scaleImageView(View view) {
//        ObjectAnimator.ofFloat()
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv, "scaleX", 2);
        objectAnimator.start();
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 没有用
                Log.i(TAG, " onAnimationUpdate getCurrentPlayTime: " + animation.getCurrentPlayTime());
                /*ViewParent parent = iv.getParent();
                parent.requestLayout();
                parent.getParent().requestLayout();*/
            }
        });
    }

    public void scaleImageView2(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        // 默认以矩形的轴心放大,你可以修改,如下修改成左上角
        upView.setPivotX(0);
        upView.setPivotY(0);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(upView, "scaleX", 2);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(upView, "scaleY", 2);
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.setDuration(200);
        animatorSet.start();
    }

    public void getPivotXY(View view) {
        float pivotX = upView.getPivotX();
        float pivotY = upView.getPivotY();
        int width = upView.getWidth();
        int height = upView.getHeight();
        Log.i(TAG, "pivotX="+pivotX+", pivotY="+pivotY
                +", width:"+width+", height:"+height);
    }

    public void moveImageView(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv, "translationX",
                100);
        objectAnimator.start();
        /*objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 没有用
                Log.i(TAG, " onAnimationUpdate getCurrentPlayTime: " + animation.getCurrentPlayTime());
                ViewParent parent = iv.getParent();
                parent.requestLayout();
                parent.getParent().requestLayout();
            }
        });*/
    }

    public void getImageViewLocation(View view) {
        int left = iv.getLeft();
        int top = iv.getTop();
        int right = iv.getRight();
        int bottom = iv.getBottom();
        Log.i(TAG, "位置 left: " + left + ", top:"+top+", right:"+right+", bottom:"+bottom);
        int width = iv.getWidth(); // width:275, height:275
        int height = iv.getHeight();
        float scaleX = iv.getScaleX();
        int measuredHeight = iv.getMeasuredHeight();
        int measuredWidth = iv.getMeasuredWidth();
        // 增量
        float translationX = iv.getTranslationX();
        float translationY = iv.getTranslationY();
        //  mLeft + 增量
        float x = iv.getX();
        float y = iv.getY();
        Log.i(TAG, " scaleX : " + scaleX + ", width:"+width+", height:"+height
                + ", measuredWidth:"+measuredWidth + ", measuredHeight: "+measuredHeight
        +", translationX:"+translationX+", translationY:"+translationY+", x:"+x+", y:"+y);
    }

    public void setImageViewWidth(View view) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) iv.getLayoutParams();
        layoutParams.width *= 2;
        iv.setLayoutParams(layoutParams);
        Log.i(TAG, "设置2倍宽度");
    }

    public void setImageViewWidthByAnimator(View view) {
        int originWidth = iv.getWidth();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f, 2.0f);
//        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.setEvaluator();
        valueAnimator.setDuration(500);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) iv.getLayoutParams();
                float scaleX = (float) animation.getAnimatedValue();
                layoutParams.width = (int) (originWidth * scaleX);
                Log.i(TAG, " imageview width =  " + layoutParams.width);
                iv.setLayoutParams(layoutParams);
            }
        });
    }

    public void showHideContent(View view) {
        if (hideShowView.getVisibility() == View.VISIBLE) {
            hideShowView.setVisibility(View.GONE);
        } else {
            hideShowView.setVisibility(View.VISIBLE);
        }

    }

    public void viewStateChanged(View view) {
        // 可以使用代码替换xml
        /*StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator
                (getApplicationContext(), R.xml.animate_scale);
        view.setStateListAnimator(stateListAnimator);*/
    }

    public void useViewAnimationXML(View view) {
        ImageView spaceshipImage = (ImageView) findViewById(R.id.up_iv);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation);
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
    }

    public void usePropertyAnimationXML(View view) {
        ImageView spaceshipImage = (ImageView) findViewById(R.id.up_iv);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.property_animator);
        set.setTarget(spaceshipImage);
        set.start();

    }

    public void usePropertyAnimationXML2(View view) {
        ImageView spaceshipImage = (ImageView) findViewById(R.id.up_iv);
        ValueAnimator xmlAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this,
                R.animator.animator);
        xmlAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                float animatedValue = (float)updatedAnimation.getAnimatedValue();
                spaceshipImage.setTranslationX(animatedValue);
            }
        });

        xmlAnimator.start();


    }



    class MyTypeEvaluator implements TypeEvaluator<MyPoint> {
        MyPoint temp = new MyPoint();
        // evaluate 求值 16ms 会被调用一次, fraction从 0 ~ 1
        @Override
        public MyPoint evaluate(float fraction, MyPoint startValue, MyPoint endValue) {
            temp.x = (int) ((endValue.x - startValue.x) * fraction);
            temp.y = (int) ((endValue.y - startValue.y) * fraction);
            // fraction时间百分比
            Log.i(TAG, "evaluate fraction : " + fraction + ", temp : " + temp);
            return temp;
        }
    }

    /**
     * View 类中有利于属性动画的新属性包括
     * translationX 和 translationY 左侧坐标和顶部坐标的增量。
     * rotation、rotationX 和 rotationY：这些属性用于控制视图围绕轴心点进行的 2D（ 属性）和 3D 旋转
     *
     *
     * @param view
     */

    public void layoutTransitionTest(View view) {
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat()
    }
}