// Copyright 2013 Square, Inc.

package com.squareup.timessquare;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.timessquare.MonthCellDescriptor.RangeState;

public class CalendarCellView extends FrameLayout {
  private static final int[] STATE_SELECTABLE = {
      R.attr.tsquare_state_selectable
  };
  private static final int[] STATE_CURRENT_MONTH = {
      R.attr.tsquare_state_current_month
  };
  private static final int[] STATE_TODAY = {
      R.attr.tsquare_state_today
  };
  private static final int[] STATE_HIGHLIGHTED = {
      R.attr.tsquare_state_highlighted
  };
  private static final int[] STATE_RANGE_FIRST = {
      R.attr.tsquare_state_range_first
  };
  private static final int[] STATE_RANGE_MIDDLE = {
      R.attr.tsquare_state_range_middle
  };
  private static final int[] STATE_RANGE_LAST = {
      R.attr.tsquare_state_range_last
  };

  public TextView dateText;
  public ImageView topRightImage;
  public ViewGroup bottomLeftLayout;

  private boolean isSelectable = false;
  private boolean isCurrentMonth = false;
  private boolean isToday = false;
  private boolean isHighlighted = false;
  private RangeState rangeState = RangeState.NONE;

  @SuppressWarnings("UnusedDeclaration") //
  public CalendarCellView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    LayoutInflater.from(getContext()).inflate(R.layout.day, this);
    this.dateText = (TextView) findViewById(R.id.dateText);
    this.topRightImage = (ImageView) findViewById(R.id.top_right_image);
    this.bottomLeftLayout = (ViewGroup) findViewById(R.id.bottom_left_layout);
  }

  public void setSelectable(boolean isSelectable) {
    this.isSelectable = isSelectable;
    refreshDrawableState();
  }

  public void setCurrentMonth(boolean isCurrentMonth) {
    this.isCurrentMonth = isCurrentMonth;
    refreshDrawableState();
  }

  public void setToday(boolean isToday) {
    this.isToday = isToday;
    refreshDrawableState();
  }

  public void setRangeState(MonthCellDescriptor.RangeState rangeState) {
    this.rangeState = rangeState;
    refreshDrawableState();
  }

  public void setHighlighted(boolean highlighted) {
    isHighlighted = highlighted;
    refreshDrawableState();
  }

  public boolean isCurrentMonth() {
    return isCurrentMonth;
  }

  public boolean isToday() {
    return isToday;
  }

  public boolean isSelectable() {
    return isSelectable;
  }

  @Override protected int[] onCreateDrawableState(int extraSpace) {
    final int[] drawableState = super.onCreateDrawableState(extraSpace + 5);

    if (isSelectable) {
      mergeDrawableStates(drawableState, STATE_SELECTABLE);
    }

    if (isCurrentMonth) {
      mergeDrawableStates(drawableState, STATE_CURRENT_MONTH);
    }

    if (isToday) {
      mergeDrawableStates(drawableState, STATE_TODAY);
    }

    if (isHighlighted) {
      mergeDrawableStates(drawableState, STATE_HIGHLIGHTED);
    }

    if (rangeState == MonthCellDescriptor.RangeState.FIRST) {
      mergeDrawableStates(drawableState, STATE_RANGE_FIRST);
    } else if (rangeState == MonthCellDescriptor.RangeState.MIDDLE) {
      mergeDrawableStates(drawableState, STATE_RANGE_MIDDLE);
    } else if (rangeState == RangeState.LAST) {
      mergeDrawableStates(drawableState, STATE_RANGE_LAST);
    }

    return drawableState;
  }
}
