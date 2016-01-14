package com.kuelye.components.utils;

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

import static java.lang.Math.PI;

public final class MathUtils {

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

}
