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

public class RadialBarData {

  private int mLevel;
  private float mValue;

  private RadialBarData(Builder builder) {
    mLevel = builder.mLevel;
    mValue = builder.mValue;
  }

  public int getLevel() {
    return mLevel;
  }

  public float getValue() {
    return mValue;
  }

  @Override
  public String toString() {
    return "RadialBarData{" +
        "mLevel=" + mLevel +
        ", mValue=" + mValue +
        '}';
  }

  /* =========================== INNER ============================== */

  public static class Builder {

    private int mLevel = 0;
    private float mValue = 0;

    public Builder() {
      // stub
    }

    public Builder setValue(float value) {
      mValue = value;

      return this;
    }

    public Builder setLevel(int level) {
      mLevel = level;

      return this;
    }

    public RadialBarData build() {
      return new RadialBarData(this);
    }

  }

}
