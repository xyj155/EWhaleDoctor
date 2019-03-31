package com.example.module_library.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.module_library.MyApp;

import java.util.ArrayList;
import java.util.List;

public class ViewPageIndicator extends HorizontalScrollView implements ViewPager.OnPageChangeListener {

    private static final int COLOR_TEXT_NORMAL = 0xFF000000;
    private static final int COLOR_INDICATOR_COLOR = 0xffd321;

    private Context context;
    private int tabWidth = 250;
    private String[] titles;
    private int count;
    private Paint mPaint;
    private float mTranslationX;
    private ViewPager viewPager;
    private int SCREEN_WIDTH;
    private float lineheight = 8.0f;
    private List imageList = new ArrayList<>();


    public ViewPageIndicator(Context context) {
        this(context, null);
    }

    public ViewPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ViewPageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        mPaint = new Paint();
        mPaint.setColor(COLOR_INDICATOR_COLOR);
        mPaint.setStrokeWidth(lineheight*2);//底部指示线的宽度
        mPaint.setColor(0xffffd321);
        setHorizontalScrollBarEnabled(false);
        SCREEN_WIDTH = context.getResources().getDisplayMetrics().widthPixels;
    }

    public void setLineheight(float height) {
        this.lineheight = height;
        mPaint.setStrokeWidth(lineheight);//底部指示线的宽度
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
    }

    public void setTitles(String[] titles, List imageList) {
        this.titles = titles;
        this.imageList = imageList;
        count = titles.length;
        generateTitleView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mTranslationX, getHeight() - lineheight);
        canvas.drawLine(80, 0, tabWidth - 80, 0, mPaint);//（startX, startY, stopX, stopY, paint）
        canvas.drawCircle(78,0,lineheight,mPaint);
        canvas.drawCircle(tabWidth - 82,0,lineheight,mPaint);
        canvas.restore();

    }

    public void scroll(int position, float offset) {
        mTranslationX = tabWidth * (position + offset);
        scrollTo((int) mTranslationX - (SCREEN_WIDTH - tabWidth) / 2, 0);

        invalidate();
    }

    LinearLayout linearLayout;



    private void generateTitleView() {
        if (getChildCount() > 0)
            this.removeAllViews();
        count = titles.length;
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(0, 10, 0, 20);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(count * tabWidth, LinearLayout.LayoutParams.MATCH_PARENT));
        for (int i = 0; i < count; i++) {
            LinearLayout linearLayout1 = new LinearLayout(context);
            linearLayout1.setOrientation(LinearLayout.VERTICAL);
            linearLayout1.setGravity(Gravity.CENTER);
            CircleImageView circleImageView = new CircleImageView(context);
            circleImageView.setBorderColor(0xfffafafa);
            circleImageView.setBorderWidth(2);
            circleImageView.setPadding(8, 8, 8, 8);
//            GlideUtil.BaseGlide(imageList.get(i).toString(), circleImageView);
            RoundedCorners roundedCorners = new RoundedCorners(10);
            RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300).centerInside();
            Glide.with(MyApp.getInstance()).asBitmap().apply(options).load(imageList.get(i).toString()).into(circleImageView);
            LinearLayout.LayoutParams ivIp = new LinearLayout.LayoutParams(210,
                    210);

            circleImageView.setLayoutParams(ivIp);
            TextView tv = new TextView(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(tabWidth,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(COLOR_TEXT_NORMAL);
            tv.setText(titles[i]);
            tv.setPadding(0, 8, 0, 15);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);//字体大小
            tv.setLayoutParams(lp);

            if (i == 0) {
                circleImageView.setBorderColor(0xffffd321);
                circleImageView.setBorderWidth(10);
                tv.setTextSize(17);
                TextPaint tp = tv.getPaint();
                tp.setFakeBoldText(true);
            }
            final int finalI = i;
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewPager != null) {
                        viewPager.setCurrentItem(finalI);
                    }
                }
            });
            linearLayout1.addView(circleImageView);
            linearLayout1.addView(tv);

            linearLayout.addView(linearLayout1);
        }
        addView(linearLayout);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        scroll(position, positionOffset);
    }

    private static final String TAG = "MyIndicator";
    private int lastPosition = 0;
    public ViewPager.OnPageChangeListener delegatePageListener;

    @Override
    public void onPageSelected(int position) {
        Log.i(TAG, "onPageSelected: " + position);
        LinearLayout curr = (LinearLayout) linearLayout.getChildAt(position);
        LinearLayout pre = (LinearLayout) linearLayout.getChildAt(lastPosition);
        View childAt = curr.getChildAt(1);
        View childPre = pre.getChildAt(1);
        View imgIcoPre = pre.getChildAt(0);
        View imgIcoCur = curr.getChildAt(0);
        if (childAt instanceof TextView) {
            Log.i(TAG, "onPageSelected: childAt");
            TextPaint tp = (((TextView) childAt).getPaint());
            TextPaint tpPre = (((TextView) childPre).getPaint());

            ((CircleImageView) imgIcoPre).setBorderColor(0xfffafafa);
            ((CircleImageView) imgIcoPre).setBorderWidth(2);
            ((CircleImageView) imgIcoCur).setBorderColor(0xffffd321);
            ((CircleImageView) imgIcoCur).setBorderWidth(10);
            ((TextView) childAt).setTextSize(17);
            ((TextView) childPre).setTextSize(14);
            tp.setFakeBoldText(true);
            tpPre.setFakeBoldText(false);
        }
        Log.i(TAG, "onPageSelected: " + lastPosition);
        lastPosition = position;
        if (delegatePageListener != null) {
            delegatePageListener.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
