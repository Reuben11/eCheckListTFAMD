package tf.www.echecklisttfamd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class HeightWrappingViewPager extends ViewPager {
    public HeightWrappingViewPager(@NonNull Context context) {
        super(context);
        initPageChangeListener();
    }

    public HeightWrappingViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPageChangeListener();
    }


    private void initPageChangeListener() {
        addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                requestLayout();
            }
        });
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View child = getChildAt(getCurrentItem());
        if (child != null) {
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
        } else {for(int i = 0; i < getChildCount(); i++) {
            View child2 = getChildAt(i);
            child2.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child2.getMeasuredHeight() ;
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
        }}

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
