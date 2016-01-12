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

import java.util.List;

import static java.lang.Math.PI;

public class RadialBarChartView extends View {

  @NonNull private Paint mPaint;
  @Nullable private List<RadialBarData> mData;

  private double mStartAngleInRadians = 0;
  private double mEndAngleInRadians = PI / 2;
  private float mRadiusModifier = 1f;

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
    final float r = chartSize * mRadiusModifier / 2f;
    final int xc = chartCenterX;
    final int yc = chartCenterY;

    final float x1 = (float) (chartCenterX + r / 2f * Math.sin(mStartAngleInRadians));
    final float y1 = (float) (chartCenterY - r / 2f * Math.cos(mStartAngleInRadians));
    final float x4 = (float) (chartCenterX + r / 2f * Math.sin(mEndAngleInRadians));
    final float y4 = (float) (chartCenterY - r / 2f * Math.cos(mEndAngleInRadians));

    drawMarker(canvas, chartCenterX, chartCenterY);
    drawMarker(canvas, x1, y1);
    drawMarker(canvas, x4, y4);

    final double ax = x1 - xc;
    final double ay = y1 - yc;
    final double bx = x4 - xc;
    final double by = y4 - yc;
    final double q1 = ax * ax + ay * ay;
    final double q2 = q1 + ax * bx + ay * by;
    final double k2 = 4d / 3d * (Math.sqrt(2d * q1 * q2) - q2) / (ax * by - ay * bx);

    final float x2 = (float) (xc + ax - k2 * ay);
    final float y2 = (float) (yc + ay + k2 * ax);
    final float x3 = (float) (xc + bx + k2 * by);
    final float y3 = (float) (yc + by - k2 * bx);

    drawMarker(canvas, x2, y2);
    drawMarker(canvas, x3, y3);

    final Path path = new Path();
    path.moveTo(x1, y1);
    path.cubicTo(x2, y2, x3, y3, x4, y4);

    canvas.drawLine(10, 10, 100, 100, mPaint);
    canvas.drawPath(path, mPaint);
  }

  public void updateData(List<RadialBarData> data) {
    mData = data;

    notifyDataUpdated();
  }

  public void updateStartAngle(int startAngleInDegrees) {
    mStartAngleInRadians = startAngleInDegrees * PI / 180;

    notifyDataUpdated();
  }

  public void updateEndAngle(int endAngleInDegrees) {
    mEndAngleInRadians = endAngleInDegrees * PI / 180;

    notifyDataUpdated();
  }

  public void updateRadiusModifier(float radiusModifier) {
    mRadiusModifier = radiusModifier;

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
