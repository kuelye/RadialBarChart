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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.kuelye.radialbarchart.library.RadialBarChartView;
import com.kuelye.radialbarchart.library.RadialBarData;

import java.util.ArrayList;
import java.util.List;

public class ShowcaseActivity extends Activity {

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.showcase_activity);

    final RadialBarChartView radialBarChartView
        = (RadialBarChartView) findViewById(R.id.showcase_activity_radialbarchartview);
    final Button updateDatasButton = (Button) findViewById(R.id.showcase_activity_update_datas_button);
    updateDatasButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final List<RadialBarData> newDatas = new ArrayList<RadialBarData>();
        for (int i = 0; i < Math.random() * 10; ++i) {
          newDatas.add(new RadialBarData.Builder().setValue((float) Math.random()).build());
        }

        Log.w("GUB", "# " + newDatas);
        radialBarChartView.updateDatas(newDatas);
      }
    });
    final SeekBar radiusModifierFromSeekBar = (SeekBar) findViewById(R.id.showcase_activity_radius_from_modifier_seekbar);
    radiusModifierFromSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListenerStub() {

      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        radialBarChartView.updateRadiusFromModifier(progress / 100f);
      }

    });
    final SeekBar radiusModifierToSeekBar = (SeekBar) findViewById(R.id.showcase_activity_radius_to_modifier_seekbar);
    radiusModifierToSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListenerStub() {

      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        radialBarChartView.updateRadiusToModifier(progress / 100f);
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
