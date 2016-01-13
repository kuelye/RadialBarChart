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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.kuelye.components.utils.DrawUtils;

import java.util.List;

import static java.lang.Math.PI;

public class RadialBarChartView extends View {

  @NonNull private Paint mPaint;
  @Nullable private List<RadialBarData> mData;

  private double mStartAngle = 0;
  private double mEndAngle = 90;
  private float mRadiusFromModifier = 0.5f;
  private float mRadiusToModifier = 0.75f;

  public RadialBarChartView(Context context, AttributeSet attrs) {
    super(context, attrs);

    mPaint = new Paint(0);
    mPaint.setColor(0xffff0000);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    final int chartViewTop = getPaddingTop();
    final int chartViewBottom = getMeasuredHeight() - getPaddingBottom();
    final int chartViewLeft = getPaddingLeft();
    final int chartViewRight = getMeasuredWidth() - getPaddingRight();
    final int chartCenterX = (chartViewLeft + chartViewRight) / 2;
    final int chartCenterY = (chartViewTop + chartViewBottom) / 2;
    final int chartSize = Math.min(chartViewRight - chartViewLeft, chartViewBottom - chartViewTop);
    final float rFrom = chartSize * mRadiusFromModifier / 2f;
    final float rTo = chartSize * mRadiusToModifier / 2f;
    final int xc = chartCenterX;
    final int yc = chartCenterY;

    drawMarker(canvas, chartCenterX, chartCenterY);

    final Path path = new Path();
    DrawUtils.addArcToPath(path, xc, yc, rFrom, rTo, (float) mStartAngle, (float) mEndAngle);

    canvas.drawPath(path, mPaint);
  }

  public void updateData(List<RadialBarData> data) {
    mData = data;

    notifyDataUpdated();
  }

  public void updateStartAngle(int startAngle) {
    mStartAngle = startAngle;

    notifyDataUpdated();
  }

  public void updateEndAngle(int endAngle) {
    mEndAngle = endAngle;

    notifyDataUpdated();
  }

  public void updateRadiusFromModifier(float radiusFromModifier) {
    mRadiusFromModifier = radiusFromModifier;

    notifyDataUpdated();
  }

  public void updateRadiusToModifier(float radiusToModifier) {
    mRadiusToModifier = radiusToModifier;

    notifyDataUpdated();
  }

  public void notifyDataUpdated() {
    invalidate();
  }

  /* ========================== HIDDEN ============================== */

  private void drawRadialBar() {

  }

  private void drawMarker(@NonNull Canvas canvas, float x, float y) {
    final Paint markerPaint = new Paint(0);
    markerPaint.setColor(0xff00ff00);

    canvas.drawCircle(x, y, 10, markerPaint);
  }

}
