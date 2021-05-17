/*
 * Copyright (C) 2020 The Dirty Unicorns Project
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

package com.dirtyunicorns.themes.receivers;

import static android.os.UserHandle.USER_SYSTEM;
import static com.dirtyunicorns.themes.Schedule.ScheduleFragment.PREF_THEME_SCHEDULED_START_THEME_VALUE;
import static com.dirtyunicorns.themes.Schedule.ScheduleFragment.PREF_THEME_SCHEDULED_TOAST;
import static com.dirtyunicorns.themes.utils.Utils.handleBackgrounds;
import static com.dirtyunicorns.themes.utils.Utils.setStartAlarm;

import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.om.IOverlayManager;
import android.content.SharedPreferences;
import android.os.ServiceManager;
import android.widget.Toast;

import androidx.preference.PreferenceManager;

import com.android.internal.util.aospextended.ThemesUtils;
import com.dirtyunicorns.themes.R;

public class ThemesStartReceiver extends BroadcastReceiver {

    private IOverlayManager mOverlayManager;
    private SharedPreferences mSharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {

        mOverlayManager = IOverlayManager.Stub.asInterface(
                ServiceManager.getService(Context.OVERLAY_SERVICE));

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String scheduledStartThemeValue = mSharedPreferences.getString(PREF_THEME_SCHEDULED_START_THEME_VALUE, null);

        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction()) && scheduledStartThemeValue != null) {
            setStartAlarm(context);
        } else if (scheduledStartThemeValue != null) {
            switch (scheduledStartThemeValue) {
                case "1":
                    handleBackgrounds(false, context, UiModeManager.MODE_NIGHT_NO, ThemesUtils.BLACK, mOverlayManager);
                    handleBackgrounds(false, context, UiModeManager.MODE_NIGHT_NO, ThemesUtils.EXTENDED, mOverlayManager);
                    handleBackgrounds(false, context, UiModeManager.MODE_NIGHT_NO, ThemesUtils.ELEGANT, mOverlayManager);
                    if (PreferenceManager.getDefaultSharedPreferences(context)
                            .getBoolean(PREF_THEME_SCHEDULED_TOAST, true)) {
                        Toast.makeText(context, context.getString(R.string.theme_type_light) + " "
                                + context.getString(R.string.theme_schedule_applied), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "2":
                    handleBackgrounds(false, context, UiModeManager.MODE_NIGHT_YES, ThemesUtils.BLACK, mOverlayManager);
                    handleBackgrounds(false, context, UiModeManager.MODE_NIGHT_YES, ThemesUtils.EXTENDED, mOverlayManager);
                    handleBackgrounds(false, context, UiModeManager.MODE_NIGHT_YES, ThemesUtils.ELEGANT, mOverlayManager);
                    if (PreferenceManager.getDefaultSharedPreferences(context)
                            .getBoolean(PREF_THEME_SCHEDULED_TOAST, true)) {
                        Toast.makeText(context, context.getString(R.string.theme_type_dark) + " "
                                + context.getString(R.string.theme_schedule_applied), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "3":
                    handleBackgrounds(true, context, UiModeManager.MODE_NIGHT_YES, ThemesUtils.BLACK, mOverlayManager);
                    handleBackgrounds(false, context, UiModeManager.MODE_NIGHT_YES, ThemesUtils.EXTENDED, mOverlayManager);
                    handleBackgrounds(false, context, UiModeManager.MODE_NIGHT_YES, ThemesUtils.ELEGANT, mOverlayManager);
                    if (PreferenceManager.getDefaultSharedPreferences(context)
                            .getBoolean(PREF_THEME_SCHEDULED_TOAST, true)) {
                        Toast.makeText(context, context.getString(R.string.theme_type_black) + " "
                                + context.getString(R.string.theme_schedule_applied), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "4":
                    handleBackgrounds(false, context, UiModeManager.MODE_NIGHT_YES, ThemesUtils.BLACK, mOverlayManager);
                    handleBackgrounds(true, context, UiModeManager.MODE_NIGHT_YES, ThemesUtils.EXTENDED, mOverlayManager);
                    handleBackgrounds(false, context, UiModeManager.MODE_NIGHT_YES, ThemesUtils.ELEGANT, mOverlayManager);
                    if (PreferenceManager.getDefaultSharedPreferences(context)
                            .getBoolean(PREF_THEME_SCHEDULED_TOAST, true)) {
                        Toast.makeText(context, context.getString(R.string.theme_type_extended) + " "
                                + context.getString(R.string.theme_schedule_applied), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "5":
                    handleBackgrounds(false, context, UiModeManager.MODE_NIGHT_YES, ThemesUtils.BLACK, mOverlayManager);
                    handleBackgrounds(false, context, UiModeManager.MODE_NIGHT_YES, ThemesUtils.EXTENDED, mOverlayManager);
                    handleBackgrounds(true, context, UiModeManager.MODE_NIGHT_YES, ThemesUtils.ELEGANT, mOverlayManager);
                    if (PreferenceManager.getDefaultSharedPreferences(context)
                            .getBoolean(PREF_THEME_SCHEDULED_TOAST, true)) {
                        Toast.makeText(context, context.getString(R.string.theme_type_elegant) + " "
                                + context.getString(R.string.theme_schedule_applied), Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
