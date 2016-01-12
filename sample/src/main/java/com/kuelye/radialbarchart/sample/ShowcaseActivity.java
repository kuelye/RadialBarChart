package com.kuelye.radialbarchart.sample;

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

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SeekBar;

import com.kuelye.radialbarchart.library.RadialBarChartView;

public class ShowcaseActivity extends Activity {

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.showcase_activity);

    final RadialBarChartView radialBarChartView
        = (RadialBarChartView) findViewById(R.id.showcase_activity_radialbarchartview);
    final SeekBar startAngleSeekBar = (SeekBar) findViewById(R.id.showcase_activity_start_angle_seekbar);
    startAngleSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListenerStub() {

      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        radialBarChartView.updateStartAngle(progress);
      }

    });
    final SeekBar endAngleSeekBar = (SeekBar) findViewById(R.id.showcase_activity_end_angle_seekbar);
    endAngleSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListenerStub() {

      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        radialBarChartView.updateEndAngle(progress);
      }

    });
    final SeekBar radiusModifierSeekBar = (SeekBar) findViewById(R.id.showcase_activity_radius_modifier_seekbar);
    radiusModifierSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListenerStub() {

      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        radialBarChartView.updateRadiusModifier(progress / 100f);
      }

    });
  }

  /* ========================== INNER =============================== */

  private abstract class OnSeekBarChangeListenerStub implements SeekBar.OnSeekBarChangeListener {

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
      // stub
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
      // stub
    }

  }

}
