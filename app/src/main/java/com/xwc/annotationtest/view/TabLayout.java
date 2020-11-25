package com.xwc.annotationtest.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TabLayout extends ViewGroup {

    private List<Rect> childrenBounds = new ArrayList<>();
    private static final int HEIGHT_MARGIN = (int) Utils.dp2px(4);
    private static final int WIDTH_MARGIN = (int) Utils.dp2px(4);

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // tabLayout 最终的宽度
        int widthUsed = 0;
        // 所有已测量的子 view 已用的高度
        int heightUsed = HEIGHT_MARGIN;
        // 新的一行已经被用的宽度
        int lineWidthUsed = 0;
        // 每一行的所有 view 最高的高度
        int lineMaxHeight = 0;
        // tabLayout 的布局类型
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        // tabLayout 的宽度
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            // 获取 tabLayout 的每一个子 view
            View child = getChildAt(i);
            // 重新测量子 view ，已用宽度设置为0，是获取子 view 原本需要的宽度
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            // 父类的 mode 不能是无限制，否则无论子 view 需要多少宽度都可以满足，例如支持左滑的控件。
            // 判断一行已用的宽度加上当前的子 view 的宽度是否大于父控件 tabLayout，如果超出则换行
            if (specMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.getMeasuredWidth() + WIDTH_MARGIN > specWidth) {
                // 每行已用宽度重新归零
                lineWidthUsed = 0;
                // 总高度加上上一行的最高子 view 的高度（已换行）
                heightUsed += lineMaxHeight;
                lineMaxHeight = 0;
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }
            Rect childBound;
            if (childrenBounds.size() <= i) {
                childBound = new Rect();
                childrenBounds.add(childBound);
            } else {
                childBound = childrenBounds.get(i);
            }
            childBound.set(lineWidthUsed + WIDTH_MARGIN, heightUsed, lineWidthUsed + child.getMeasuredWidth() + WIDTH_MARGIN, heightUsed + child.getMeasuredHeight());
            // 本行已用宽度加上此子 view 的宽度
            lineWidthUsed += child.getMeasuredWidth() + WIDTH_MARGIN;
            // 原本行最宽宽度与本行宽度对比取较大值
            widthUsed = Math.max(widthUsed, lineWidthUsed);
            // 本行最高高度与当前子 view 高度对比取较大值
            lineMaxHeight = Math.max(lineMaxHeight, child.getMeasuredHeight() + HEIGHT_MARGIN) + HEIGHT_MARGIN;
        }
        int width = widthUsed;
        int height = heightUsed + lineMaxHeight;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect childBounds = childrenBounds.get(i);
            child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
