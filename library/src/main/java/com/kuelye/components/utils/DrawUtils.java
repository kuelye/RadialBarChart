package com.kuelye.components.utils;/*
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

import android.graphics.Path;
import android.support.annotation.NonNull;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.signum;
import static java.lang.Math.sin;

public final class DrawUtils {

  public static void addArcToPath(@NonNull Path path, float xCenter, float yCenter
      , float radiusFrom, float radiusTo, float angleFrom, float angleTo) {
    path.moveTo((float) (xCenter + radiusFrom * cos(toRadians(angleFrom)))
        , (float) (yCenter - radiusFrom * sin(toRadians(angleFrom))));
    path.lineTo((float) (xCenter + radiusTo * cos(toRadians(angleFrom)))
        , (float) (yCenter - radiusTo * sin(toRadians(angleFrom))));
    pathArcAsCurveTo(path, xCenter, yCenter, radiusTo, angleFrom, angleTo);
    path.lineTo((float) (xCenter + radiusFrom * cos(toRadians(angleTo)))
        , (float) (yCenter - radiusFrom * sin(toRadians(angleFrom))));
    pathArcAsCurveTo(path, xCenter, yCenter, radiusFrom, angleTo, angleFrom);
  }

  /**
   * @param angleFrom In degrees in the standard polar coordinate system.
   * @param angleTo In degrees in the standard polar coordinate system.
   */
  public static void pathArcAsCurveTo(@NonNull Path path, float xCenter, float yCenter
      , float radius, float angleFrom, float angleTo) {
    float angleIterationFrom = angleFrom, angleIterationTo;
    float dAngle = angleTo - angleFrom;
    if (abs(dAngle) > 360) {
      dAngle = 360;
    }

    while (dAngle != 0) {
      if (abs(dAngle) > 90) {
        angleIterationTo = angleIterationFrom + signum(dAngle) * 90;
        dAngle -= signum(dAngle) * 90;
      } else {
        angleIterationTo = angleIterationFrom + dAngle;
        dAngle = 0;
      }

      final float xFrom = (float) (xCenter + radius * cos(angleIterationFrom * PI / 180));
      final float yFrom = (float) (yCenter - radius * sin(angleIterationFrom * PI / 180));
      final float xTo = (float) (xCenter + radius * cos(angleIterationTo * PI / 180));
      final float yTo = (float) (yCenter - radius * sin(angleIterationTo * PI / 180));

      pathArcAsBezierCurveTo(path, xTo, yTo, xFrom, yFrom, xCenter, yCenter);

      angleIterationFrom = angleIterationTo;
    }
  }

  /**
   * To the range 0° <= angle < 360°.
   * @param angle In degrees.
   * @return In degrees.
   */
  public static float normalizeDegrees(float angle) {
    angle %= 360;
    if (angle < 0f) {
      angle += 360;
    }

    return angle;
  }
  /**
   * @param angle In degrees.
   * @return In radians.
   */
  public static double toRadians(float angle) {
    return angle * PI / 180;
  }

  /* ============================ INNER ============================= */

  /**
   * The last point of the current contour should be equal to (xFrom, yFrom).
   */
  private static void pathArcAsBezierCurveTo(@NonNull Path path, float xTo, float yTo
      , float xFrom, float yFrom, float xCenter, float yCenter) {
    final double ax = xFrom - xCenter;
    final double ay = yFrom - yCenter;
    final double bx = xTo - xCenter;
    final double by = yTo - yCenter;
    final double q1 = ax * ax + ay * ay;
    final double q2 = q1 + ax * bx + ay * by;
    final double k2 = 4d / 3d * (Math.sqrt(2d * q1 * q2) - q2) / (ax * by - ay * bx);

    final float xc1 = (float) (xCenter + ax - k2 * ay);
    final float yc1 = (float) (yCenter + ay + k2 * ax);
    final float xc2 = (float) (xCenter + bx + k2 * by);
    final float yc2 = (float) (yCenter + by - k2 * bx);

    path.lineTo(xFrom, yFrom); // TODO :(
    path.cubicTo(xc1, yc1, xc2, yc2, xTo, yTo);
  }

}
