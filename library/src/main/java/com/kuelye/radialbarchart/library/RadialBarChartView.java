package com.kuelye.radialbarchart.library;

/*
 * Copyright 2016 Alexey Leshchuk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class RadialBarChartView extends View {

  @NonNull private Paint mPaint;
  @Nullable private List<RadialBarData> mData;

  public RadialBarChartView(Context context, AttributeSet attrs) {
    super(context, attrs);

    mPaint = new Paint(0);
    mPaint.setColor(0xffff0000);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    int chartTop = getPaddingTop();
    int chartBottom = getMeasuredHeight() - getPaddingBottom();
    int chartLeft = getPaddingLeft();
    int chartRight = getMeasuredWidth() - getPaddingRight();
    RectF chartRect = new RectF(chartLeft, chartTop, chartRight, chartBottom);
    
    canvas.drawArc(chartRect, 10, 200, false, mPaint);
  }

  public void updateData(List<RadialBarData> data) {
    mData = data;

    notifyDataUpdated();
  }

  public void notifyDataUpdated() {
    invalidate();
  }

  /* ========================== HIDDEN ============================== */

  private void drawRadialBar() {

  }

}
