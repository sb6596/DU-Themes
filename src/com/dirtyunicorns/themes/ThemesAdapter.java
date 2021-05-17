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

package com.dirtyunicorns.themes;

import static com.dirtyunicorns.themes.utils.Utils.threeButtonNavbarEnabled;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.PathParser;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.dirtyunicorns.themes.utils.ThemesListItem;

import java.io.File;
import java.util.List;

public class ThemesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private boolean mIsNightMode;
    private int mThemeNightColor;
    private int mThemeFont;
    private String mThemeWpBackup;
    private Resources mResources;
    private List<ThemesListItem> mThemesList;
    private RecyclerView.ViewHolder mViewHolder;

    public ThemesAdapter(Context context, List<ThemesListItem> themesList) {
        mContext = context;
        mResources = context.getResources();
        mThemesList = themesList;
    }

    class ViewHolderMain extends RecyclerView.ViewHolder {

        ImageView mWpBgMain;
        ImageView mQsAccentMainWifi, mQSTileMainWifiActive;
        ImageView mQsTileMainBgBluetoothInactive, mQsTileMainIconBluetoothInactive;
        ImageView mQsAccentMainDnd, mQsTileMainDndActive;
        ImageView mQsTileMainBgFlashlightInactive, mQsTileMainIconFlashlightInactive;
        ImageView mQsAccentMainAutorotate, mQsTileMainAutorotateActive;
        ImageView mQsTileMainBgBatterySaverInactive, mQsTileMainIconBatterySaverInactive;
        ImageView mViewNavbarMain;
        LinearLayout mLlBgMain;
        TextView mThemeMainName;
        View mViewSpacerMain;

        ViewHolderMain(View view) {
            super(view);
            mWpBgMain = view.findViewById(R.id.wp_bg_main);
            mLlBgMain = view.findViewById(R.id.ll_qs_bg_main);
            mQsAccentMainWifi = view.findViewById(R.id.qs_accent_main_wifi);
            mQSTileMainWifiActive = view.findViewById(R.id.qs_tile_main_wifi_active);
            mQsTileMainBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_bg_bluetooth_inactive);
            mQsTileMainIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_icon_bluetooth_inactive);
            mQsAccentMainDnd = view.findViewById(R.id.qs_accent_main_dnd);
            mQsTileMainDndActive = view.findViewById(R.id.qs_tile_main_dnd_active);
            mQsTileMainBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_bg_flashlight_inactive);
            mQsTileMainIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_icon_flashlight_inactive);
            mQsAccentMainAutorotate = view.findViewById(R.id.qs_accent_main_autorotate);
            mQsTileMainAutorotateActive = view.findViewById(R.id.qs_tile_main_autorotate_active);
            mQsTileMainBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_bg_battery_saver_inactive);
            mQsTileMainIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_icon_battery_saver_inactive);
            mViewSpacerMain = view.findViewById(R.id.ic_themes_qs_spacer_main);
            mViewNavbarMain = view.findViewById(R.id.themes_navbar_style_main);
            mThemeMainName = view.findViewById(R.id.theme_main_name);
        }
    }

    class ViewHolderFilled extends RecyclerView.ViewHolder {

        ImageView mWpBgFilled;
        ImageView mQsAccentMainFilledWifi, mQSTileMainFilledWifiActive;
        ImageView mQsTileMainFilledBgBluetoothInactive, mQsTileMainFilledIconBluetoothInactive;
        ImageView mQsAccentMainFilledDnd, mQsTileMainFilledDndActive;
        ImageView mQsTileMainFilledBgFlashlightInactive, mQsTileMainFilledIconFlashlightInactive;
        ImageView mQsAccentMainFilledAutorotate, mQsTileMainFilledAutorotateActive;
        ImageView mQsTileMainFilledBgBatterySaverInactive, mQsTileMainFilledIconBatterySaverInactive;
        ImageView mViewNavbarMainFilled;
        LinearLayout mLlBgMainFilled;
        TextView mThemeMainFilledName;
        View mViewSpacerMainFilled;

        ViewHolderFilled(View view) {
            super(view);
            mWpBgFilled = view.findViewById(R.id.wp_bg_main_filled);
            mLlBgMainFilled = view.findViewById(R.id.ll_qs_bg_main_filled);
            mQsAccentMainFilledWifi = view.findViewById(R.id.qs_accent_main_filled_wifi);
            mQSTileMainFilledWifiActive = view.findViewById(R.id.qs_tile_main_filled_wifi_active);
            mQsTileMainFilledBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_filled_bg_bluetooth_inactive);
            mQsTileMainFilledIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_filled_icon_bluetooth_inactive);
            mQsAccentMainFilledDnd = view.findViewById(R.id.qs_accent_main_filled_dnd);
            mQsTileMainFilledDndActive = view.findViewById(R.id.qs_tile_main_filled_dnd_active);
            mQsTileMainFilledBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_filled_bg_flashlight_inactive);
            mQsTileMainFilledIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_filled_icon_flashlight_inactive);
            mQsAccentMainFilledAutorotate = view.findViewById(R.id.qs_accent_main_filled_autorotate);
            mQsTileMainFilledAutorotateActive = view.findViewById(R.id.qs_tile_main_filled_autorotate_active);
            mQsTileMainFilledBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_filled_bg_battery_saver_inactive);
            mQsTileMainFilledIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_filled_icon_battery_saver_inactive);
            mViewSpacerMainFilled = view.findViewById(R.id.ic_themes_qs_spacer_main_filled);
            mViewNavbarMainFilled = view.findViewById(R.id.themes_navbar_style_main_filled);
            mThemeMainFilledName = view.findViewById(R.id.theme_main_filled_name);
        }
    }

    class ViewHolderRounded extends RecyclerView.ViewHolder {

        ImageView mWpBgRounded;
        ImageView mQsAccentMainRoundedWifi, mQSTileMainRoundedWifiActive;
        ImageView mQsTileMainRoundedBgBluetoothInactive, mQsTileMainRoundedIconBluetoothInactive;
        ImageView mQsAccentMainRoundedDnd, mQsTileMainRoundedDndActive;
        ImageView mQsTileMainRoundedBgFlashlightInactive, mQsTileMainRoundedIconFlashlightInactive;
        ImageView mQsAccentMainRoundedAutorotate, mQsTileMainRoundedAutorotateActive;
        ImageView mQsTileMainRoundedBgBatterySaverInactive, mQsTileMainRoundedIconBatterySaverInactive;
        ImageView mViewNavbarMainRounded;
        LinearLayout mLlBgMainRounded;
        TextView mThemeMainRoundedName;
        View mViewSpacerMainRounded;

        ViewHolderRounded(View view) {
            super(view);
            mWpBgRounded = view.findViewById(R.id.wp_bg_main_rounded);
            mLlBgMainRounded = view.findViewById(R.id.ll_qs_bg_main_rounded);
            mQsAccentMainRoundedWifi = view.findViewById(R.id.qs_accent_main_rounded_wifi);
            mQSTileMainRoundedWifiActive = view.findViewById(R.id.qs_tile_main_rounded_wifi_active);
            mQsTileMainRoundedBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_rounded_bg_bluetooth_inactive);
            mQsTileMainRoundedIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_rounded_icon_bluetooth_inactive);
            mQsAccentMainRoundedDnd = view.findViewById(R.id.qs_accent_main_rounded_dnd);
            mQsTileMainRoundedDndActive = view.findViewById(R.id.qs_tile_main_rounded_dnd_active);
            mQsTileMainRoundedBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_rounded_bg_flashlight_inactive);
            mQsTileMainRoundedIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_rounded_icon_flashlight_inactive);
            mQsAccentMainRoundedAutorotate = view.findViewById(R.id.qs_accent_main_rounded_autorotate);
            mQsTileMainRoundedAutorotateActive = view.findViewById(R.id.qs_tile_main_rounded_autorotate_active);
            mQsTileMainRoundedBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_rounded_bg_battery_saver_inactive);
            mQsTileMainRoundedIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_rounded_icon_battery_saver_inactive);
            mViewSpacerMainRounded = view.findViewById(R.id.ic_themes_qs_spacer_main_rounded);
            mViewNavbarMainRounded = view.findViewById(R.id.themes_navbar_style_main_rounded);
            mThemeMainRoundedName = view.findViewById(R.id.theme_main_rounded_name);
        }
    }

    class ViewHolderCircular extends RecyclerView.ViewHolder {

        ImageView mWpBgCircular;
        ImageView mQsAccentMainCircularWifi, mQSTileMainCircularWifiActive;
        ImageView mQsTileMainCircularBgBluetoothInactive, mQsTileMainCircularIconBluetoothInactive;
        ImageView mQsAccentMainCircularDnd, mQsTileMainCircularDndActive;
        ImageView mQsTileMainCircularBgFlashlightInactive, mQsTileMainCircularIconFlashlightInactive;
        ImageView mQsAccentMainCircularAutorotate, mQsTileMainCircularAutorotateActive;
        ImageView mQsTileMainCircularBgBatterySaverInactive, mQsTileMainCircularIconBatterySaverInactive;
        ImageView mViewNavbarMainCircular;
        LinearLayout mLlBgMainCircular;
        TextView mThemeMainCircularName;
        View mViewSpacerMainCircular;

        ViewHolderCircular(View view) {
            super(view);
            mWpBgCircular = view.findViewById(R.id.wp_bg_main_circular);
            mLlBgMainCircular = view.findViewById(R.id.ll_qs_bg_main_circular);
            mQsAccentMainCircularWifi = view.findViewById(R.id.qs_accent_main_circular_wifi);
            mQSTileMainCircularWifiActive = view.findViewById(R.id.qs_tile_main_circular_wifi_active);
            mQsTileMainCircularBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_circular_bg_bluetooth_inactive);
            mQsTileMainCircularIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_circular_icon_bluetooth_inactive);
            mQsAccentMainCircularDnd = view.findViewById(R.id.qs_accent_main_circular_dnd);
            mQsTileMainCircularDndActive = view.findViewById(R.id.qs_tile_main_circular_dnd_active);
            mQsTileMainCircularBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_circular_bg_flashlight_inactive);
            mQsTileMainCircularIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_circular_icon_flashlight_inactive);
            mQsAccentMainCircularAutorotate = view.findViewById(R.id.qs_accent_main_circular_autorotate);
            mQsTileMainCircularAutorotateActive = view.findViewById(R.id.qs_tile_main_circular_autorotate_active);
            mQsTileMainCircularBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_circular_bg_battery_saver_inactive);
            mQsTileMainCircularIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_circular_icon_battery_saver_inactive);
            mViewSpacerMainCircular = view.findViewById(R.id.ic_themes_qs_spacer_main_circular);
            mViewNavbarMainCircular = view.findViewById(R.id.themes_navbar_style_main_circular);
            mThemeMainCircularName = view.findViewById(R.id.theme_main_circular_name);
        }
    }

    class ViewHolderKai extends RecyclerView.ViewHolder {

        ImageView mWpBgKai;
        ImageView mQsAccentMainKaiWifi, mQSTileMainKaiWifiActive;
        ImageView mQsTileMainKaiBgBluetoothInactive, mQsTileMainKaiIconBluetoothInactive;
        ImageView mQsAccentMainKaiDnd, mQsTileMainKaiDndActive;
        ImageView mQsTileMainKaiBgFlashlightInactive, mQsTileMainKaiIconFlashlightInactive;
        ImageView mQsAccentMainKaiAutorotate, mQsTileMainKaiAutorotateActive;
        ImageView mQsTileMainKaiBgBatterySaverInactive, mQsTileMainKaiIconBatterySaverInactive;
        ImageView mViewNavbarMainKai;
        LinearLayout mLlBgMainKai;
        TextView mThemeMainKaiName;
        View mViewSpacerMainKai;

        ViewHolderKai(View view) {
            super(view);
            mWpBgKai = view.findViewById(R.id.wp_bg_main_kai);
            mLlBgMainKai = view.findViewById(R.id.ll_qs_bg_main_kai);
            mQsAccentMainKaiWifi = view.findViewById(R.id.qs_accent_main_kai_wifi);
            mQSTileMainKaiWifiActive = view.findViewById(R.id.qs_tile_main_kai_wifi_active);
            mQsTileMainKaiBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_kai_bg_bluetooth_inactive);
            mQsTileMainKaiIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_kai_icon_bluetooth_inactive);
            mQsAccentMainKaiDnd = view.findViewById(R.id.qs_accent_main_kai_dnd);
            mQsTileMainKaiDndActive = view.findViewById(R.id.qs_tile_main_kai_dnd_active);
            mQsTileMainKaiBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_kai_bg_flashlight_inactive);
            mQsTileMainKaiIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_kai_icon_flashlight_inactive);
            mQsAccentMainKaiAutorotate = view.findViewById(R.id.qs_accent_main_kai_autorotate);
            mQsTileMainKaiAutorotateActive = view.findViewById(R.id.qs_tile_main_kai_autorotate_active);
            mQsTileMainKaiBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_kai_bg_battery_saver_inactive);
            mQsTileMainKaiIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_kai_icon_battery_saver_inactive);
            mViewSpacerMainKai = view.findViewById(R.id.ic_themes_qs_spacer_main_kai);
            mViewNavbarMainKai = view.findViewById(R.id.themes_navbar_style_main_kai);
            mThemeMainKaiName = view.findViewById(R.id.theme_main_kai_name);
        }
    }

    class ViewHolderSam extends RecyclerView.ViewHolder {

        ImageView mWpBgSam;
        ImageView mQsAccentMainSamWifi, mQSTileMainSamWifiActive;
        ImageView mQsTileMainSamBgBluetoothInactive, mQsTileMainSamIconBluetoothInactive;
        ImageView mQsAccentMainSamDnd, mQsTileMainSamDndActive;
        ImageView mQsTileMainSamBgFlashlightInactive, mQsTileMainSamIconFlashlightInactive;
        ImageView mQsAccentMainSamAutorotate, mQsTileMainSamAutorotateActive;
        ImageView mQsTileMainSamBgBatterySaverInactive, mQsTileMainSamIconBatterySaverInactive;
        ImageView mViewNavbarMainSam;
        LinearLayout mLlBgMainSam;
        TextView mThemeMainSamName;
        View mViewSpacerMainSam;

        ViewHolderSam(View view) {
            super(view);
            mWpBgSam = view.findViewById(R.id.wp_bg_main_sam);
            mLlBgMainSam = view.findViewById(R.id.ll_qs_bg_main_sam);
            mQsAccentMainSamWifi = view.findViewById(R.id.qs_accent_main_sam_wifi);
            mQSTileMainSamWifiActive = view.findViewById(R.id.qs_tile_main_sam_wifi_active);
            mQsTileMainSamBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_sam_bg_bluetooth_inactive);
            mQsTileMainSamIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_sam_icon_bluetooth_inactive);
            mQsAccentMainSamDnd = view.findViewById(R.id.qs_accent_main_sam_dnd);
            mQsTileMainSamDndActive = view.findViewById(R.id.qs_tile_main_sam_dnd_active);
            mQsTileMainSamBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_sam_bg_flashlight_inactive);
            mQsTileMainSamIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_sam_icon_flashlight_inactive);
            mQsAccentMainSamAutorotate = view.findViewById(R.id.qs_accent_main_sam_autorotate);
            mQsTileMainSamAutorotateActive = view.findViewById(R.id.qs_tile_main_sam_autorotate_active);
            mQsTileMainSamBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_sam_bg_battery_saver_inactive);
            mQsTileMainSamIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_sam_icon_battery_saver_inactive);
            mViewSpacerMainSam = view.findViewById(R.id.ic_themes_qs_spacer_main_sam);
            mViewNavbarMainSam = view.findViewById(R.id.themes_navbar_style_main_sam);
            mThemeMainSamName = view.findViewById(R.id.theme_main_sam_name);
        }
    }

    class ViewHolderVictor extends RecyclerView.ViewHolder {

        ImageView mWpBgVictor;
        ImageView mQsAccentMainVictorWifi, mQSTileMainVictorWifiActive;
        ImageView mQsTileMainVictorBgBluetoothInactive, mQsTileMainVictorIconBluetoothInactive;
        ImageView mQsAccentMainVictorDnd, mQsTileMainVictorDndActive;
        ImageView mQsTileMainVictorBgFlashlightInactive, mQsTileMainVictorIconFlashlightInactive;
        ImageView mQsAccentMainVictorAutorotate, mQsTileMainVictorAutorotateActive;
        ImageView mQsTileMainVictorBgBatterySaverInactive, mQsTileMainVictorIconBatterySaverInactive;
        ImageView mViewNavbarMainVictor;
        LinearLayout mLlBgMainVictor;
        TextView mThemeMainVictorName;
        View mViewSpacerMainVictor;

        ViewHolderVictor(View view) {
            super(view);
            mWpBgVictor = view.findViewById(R.id.wp_bg_main_victor);
            mLlBgMainVictor = view.findViewById(R.id.ll_qs_bg_main_victor);
            mQsAccentMainVictorWifi = view.findViewById(R.id.qs_accent_main_victor_wifi);
            mQSTileMainVictorWifiActive = view.findViewById(R.id.qs_tile_main_victor_wifi_active);
            mQsTileMainVictorBgBluetoothInactive = view.findViewById(R.id.qs_tile_main_victor_bg_bluetooth_inactive);
            mQsTileMainVictorIconBluetoothInactive = view.findViewById(R.id.qs_tile_main_victor_icon_bluetooth_inactive);
            mQsAccentMainVictorDnd = view.findViewById(R.id.qs_accent_main_victor_dnd);
            mQsTileMainVictorDndActive = view.findViewById(R.id.qs_tile_main_victor_dnd_active);
            mQsTileMainVictorBgFlashlightInactive = view.findViewById(R.id.qs_tile_main_victor_bg_flashlight_inactive);
            mQsTileMainVictorIconFlashlightInactive = view.findViewById(R.id.qs_tile_main_victor_icon_flashlight_inactive);
            mQsAccentMainVictorAutorotate = view.findViewById(R.id.qs_accent_main_victor_autorotate);
            mQsTileMainVictorAutorotateActive = view.findViewById(R.id.qs_tile_main_victor_autorotate_active);
            mQsTileMainVictorBgBatterySaverInactive = view.findViewById(R.id.qs_tile_main_victor_bg_battery_saver_inactive);
            mQsTileMainVictorIconBatterySaverInactive = view.findViewById(R.id.qs_tile_main_victor_icon_battery_saver_inactive);
            mViewSpacerMainVictor = view.findViewById(R.id.ic_themes_qs_spacer_main_victor);
            mViewNavbarMainVictor = view.findViewById(R.id.themes_navbar_style_main_victor);
            mThemeMainVictorName = view.findViewById(R.id.theme_main_victor_name);
        }
    }

    @Override
    public int getItemViewType(int position) {
        ThemesListItem themes = mThemesList.get(position);
        int themeViewType = Integer.parseInt(themes.getThemeSbIcons());
        return themeViewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                mViewHolder = new ViewHolderMain(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main, parent, false));
                break;
            case 2:
                mViewHolder = new ViewHolderFilled(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_filled, parent, false));
                break;
            case 3:
                mViewHolder = new ViewHolderRounded(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_rounded, parent, false));
                break;
            case 4:
                mViewHolder = new ViewHolderCircular(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_circular, parent, false));
                break;
            case 5:
                mViewHolder = new ViewHolderKai(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_kai, parent, false));
                break;
            case 6:
                mViewHolder = new ViewHolderSam(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_sam, parent, false));
                break;
            case 7:
                mViewHolder = new ViewHolderVictor(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restore_themes_main_victor, parent, false));
                break;
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ThemesListItem themes = mThemesList.get(position);
        mIsNightMode = Boolean.parseBoolean(themes.getThemeDayOrNight());
        mThemeNightColor = Color.parseColor(themes.getThemeNightColor());
        mThemeFont = Integer.parseInt(themes.getThemeFont());
        mThemeWpBackup = themes.getThemeWp();
        int bgQsAccent = Color.parseColor(themes.getThemeAccent());
        int qsTileBgInactive, qsTileIconInactive, qsTileIconActive;
        String themeNavbarStyle = themes.getThemeNavbarStyle();
        String pathShape = themes.getThemeIconShape();
        String themeName = themes.getThemeName();
        if (!mIsNightMode) {
            qsTileBgInactive = mResources.getColor(R.color.qs_tile_background_inactive_day);
            qsTileIconInactive = mResources.getColor(R.color.qs_tile_icon_inactive_day);
            qsTileIconActive = mResources.getColor(R.color.qs_tile_icon_active_day);
        } else {
            qsTileBgInactive = mResources.getColor(R.color.qs_tile_background_inactive_night);
            qsTileIconInactive = mResources.getColor(R.color.qs_tile_icon_inactive_night);
            qsTileIconActive = mResources.getColor(R.color.qs_tile_icon_active_night);
        }
        switch (holder.getItemViewType()) {
            case 1:
                ViewHolderMain viewHolderMain = (ViewHolderMain) holder;
                new ThemeWallpaper(viewHolderMain.mWpBgMain).execute();
                viewHolderMain.mLlBgMain.setBackground(getThemeDayNightBg());
                viewHolderMain.mQsAccentMainWifi.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderMain.mQSTileMainWifiActive.setColorFilter(qsTileIconActive);
                viewHolderMain.mQsTileMainBgBluetoothInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderMain.mQsTileMainIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolderMain.mQsAccentMainDnd.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderMain.mQsTileMainDndActive.setColorFilter(qsTileIconActive);
                viewHolderMain.mQsTileMainBgFlashlightInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderMain.mQsTileMainIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolderMain.mQsAccentMainAutorotate.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderMain.mQsTileMainAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolderMain.mQsTileMainBgBatterySaverInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderMain.mQsTileMainIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolderMain.mViewSpacerMain.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolderMain.mViewNavbarMain.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolderMain.mViewNavbarMain.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolderMain.mViewNavbarMain.setVisibility(View.GONE);
                }
                viewHolderMain.mThemeMainName.setText(themeName);
                viewHolderMain.mThemeMainName.setTypeface(getTypeface());
                break;
            case 2:
                ViewHolderFilled viewHolderFilled = (ViewHolderFilled) holder;
                new ThemeWallpaper(viewHolderFilled.mWpBgFilled).execute();
                viewHolderFilled.mLlBgMainFilled.setBackground(getThemeDayNightBg());
                viewHolderFilled.mQsAccentMainFilledWifi.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderFilled.mQSTileMainFilledWifiActive.setColorFilter(qsTileIconActive);
                viewHolderFilled.mQsTileMainFilledBgBluetoothInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderFilled.mQsTileMainFilledIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolderFilled.mQsAccentMainFilledDnd.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderFilled.mQsTileMainFilledDndActive.setColorFilter(qsTileIconActive);
                viewHolderFilled.mQsTileMainFilledBgFlashlightInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderFilled.mQsTileMainFilledIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolderFilled.mQsAccentMainFilledAutorotate.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderFilled.mQsTileMainFilledAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolderFilled.mQsTileMainFilledBgBatterySaverInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderFilled.mQsTileMainFilledIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolderFilled.mViewSpacerMainFilled.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolderFilled.mViewNavbarMainFilled.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolderFilled.mViewNavbarMainFilled.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolderFilled.mViewNavbarMainFilled.setVisibility(View.GONE);
                }
                viewHolderFilled.mThemeMainFilledName.setText(themeName);
                viewHolderFilled.mThemeMainFilledName.setTypeface(getTypeface());
                break;
            case 3:
                ViewHolderRounded viewHolderRounded = (ViewHolderRounded) holder;
                new ThemeWallpaper(viewHolderRounded.mWpBgRounded).execute();
                viewHolderRounded.mLlBgMainRounded.setBackground(getThemeDayNightBg());
                viewHolderRounded.mQsAccentMainRoundedWifi.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderRounded.mQSTileMainRoundedWifiActive.setColorFilter(qsTileIconActive);
                viewHolderRounded.mQsTileMainRoundedBgBluetoothInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderRounded.mQsTileMainRoundedIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolderRounded.mQsAccentMainRoundedDnd.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderRounded.mQsTileMainRoundedDndActive.setColorFilter(qsTileIconActive);
                viewHolderRounded.mQsTileMainRoundedBgFlashlightInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderRounded.mQsTileMainRoundedIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolderRounded.mQsAccentMainRoundedAutorotate.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderRounded.mQsTileMainRoundedAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolderRounded.mQsTileMainRoundedBgBatterySaverInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderRounded.mQsTileMainRoundedIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolderRounded.mViewSpacerMainRounded.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolderRounded.mViewNavbarMainRounded.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolderRounded.mViewNavbarMainRounded.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolderRounded.mViewNavbarMainRounded.setVisibility(View.GONE);
                }
                viewHolderRounded.mThemeMainRoundedName.setText(themeName);
                viewHolderRounded.mThemeMainRoundedName.setTypeface(getTypeface());
                break;
            case 4:
                ViewHolderCircular viewHolderCircular = (ViewHolderCircular) holder;
                new ThemeWallpaper(viewHolderCircular.mWpBgCircular).execute();
                viewHolderCircular.mLlBgMainCircular.setBackground(getThemeDayNightBg());
                viewHolderCircular.mQsAccentMainCircularWifi.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderCircular.mQSTileMainCircularWifiActive.setColorFilter(qsTileIconActive);
                viewHolderCircular.mQsTileMainCircularBgBluetoothInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderCircular.mQsTileMainCircularIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolderCircular.mQsAccentMainCircularDnd.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderCircular.mQsTileMainCircularDndActive.setColorFilter(qsTileIconActive);
                viewHolderCircular.mQsTileMainCircularBgFlashlightInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderCircular.mQsTileMainCircularIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolderCircular.mQsAccentMainCircularAutorotate.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderCircular.mQsTileMainCircularAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolderCircular.mQsTileMainCircularBgBatterySaverInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderCircular.mQsTileMainCircularIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolderCircular.mViewSpacerMainCircular.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolderCircular.mViewNavbarMainCircular.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolderCircular.mViewNavbarMainCircular.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolderCircular.mViewNavbarMainCircular.setVisibility(View.GONE);
                }
                viewHolderCircular.mThemeMainCircularName.setText(themeName);
                viewHolderCircular.mThemeMainCircularName.setTypeface(getTypeface());
                break;
            case 5:
                ViewHolderKai viewHolderKai = (ViewHolderKai) holder;
                new ThemeWallpaper(viewHolderKai.mWpBgKai).execute();
                viewHolderKai.mLlBgMainKai.setBackground(getThemeDayNightBg());
                viewHolderKai.mQsAccentMainKaiWifi.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderKai.mQSTileMainKaiWifiActive.setColorFilter(qsTileIconActive);
                viewHolderKai.mQsTileMainKaiBgBluetoothInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderKai.mQsTileMainKaiIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolderKai.mQsAccentMainKaiDnd.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderKai.mQsTileMainKaiDndActive.setColorFilter(qsTileIconActive);
                viewHolderKai.mQsTileMainKaiBgFlashlightInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderKai.mQsTileMainKaiIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolderKai.mQsAccentMainKaiAutorotate.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderKai.mQsTileMainKaiAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolderKai.mQsTileMainKaiBgBatterySaverInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderKai.mQsTileMainKaiIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolderKai.mViewSpacerMainKai.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolderKai.mViewNavbarMainKai.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolderKai.mViewNavbarMainKai.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolderKai.mViewNavbarMainKai.setVisibility(View.GONE);
                }
                viewHolderKai.mThemeMainKaiName.setText(themeName);
                viewHolderKai.mThemeMainKaiName.setTypeface(getTypeface());
                break;
            case 6:
                ViewHolderSam viewHolderSam = (ViewHolderSam) holder;
                new ThemeWallpaper(viewHolderSam.mWpBgSam).execute();
                viewHolderSam.mLlBgMainSam.setBackground(getThemeDayNightBg());
                viewHolderSam.mQsAccentMainSamWifi.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderSam.mQSTileMainSamWifiActive.setColorFilter(qsTileIconActive);
                viewHolderSam.mQsTileMainSamBgBluetoothInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderSam.mQsTileMainSamIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolderSam.mQsAccentMainSamDnd.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderSam.mQsTileMainSamDndActive.setColorFilter(qsTileIconActive);
                viewHolderSam.mQsTileMainSamBgFlashlightInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderSam.mQsTileMainSamIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolderSam.mQsAccentMainSamAutorotate.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderSam.mQsTileMainSamAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolderSam.mQsTileMainSamBgBatterySaverInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderSam.mQsTileMainSamIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolderSam.mViewSpacerMainSam.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolderSam.mViewNavbarMainSam.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolderSam.mViewNavbarMainSam.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolderSam.mViewNavbarMainSam.setVisibility(View.GONE);
                }
                viewHolderSam.mThemeMainSamName.setText(themeName);
                viewHolderSam.mThemeMainSamName.setTypeface(getTypeface());
                break;
            case 7:
                ViewHolderVictor viewHolderVictor = (ViewHolderVictor) holder;
                new ThemeWallpaper(viewHolderVictor.mWpBgVictor).execute();
                viewHolderVictor.mLlBgMainVictor.setBackground(getThemeDayNightBg());
                viewHolderVictor.mQsAccentMainVictorWifi.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderVictor.mQSTileMainVictorWifiActive.setColorFilter(qsTileIconActive);
                viewHolderVictor.mQsTileMainVictorBgBluetoothInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderVictor.mQsTileMainVictorIconBluetoothInactive.setColorFilter(qsTileIconInactive);
                viewHolderVictor.mQsAccentMainVictorDnd.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderVictor.mQsTileMainVictorDndActive.setColorFilter(qsTileIconActive);
                viewHolderVictor.mQsTileMainVictorBgFlashlightInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderVictor.mQsTileMainVictorIconFlashlightInactive.setColorFilter(qsTileIconInactive);
                viewHolderVictor.mQsAccentMainVictorAutorotate.setImageDrawable(
                    getShapeDrawable(pathShape, bgQsAccent));
                viewHolderVictor.mQsTileMainVictorAutorotateActive.setColorFilter(qsTileIconActive);
                viewHolderVictor.mQsTileMainVictorBgBatterySaverInactive.setImageDrawable(
                    getShapeDrawable(pathShape, qsTileBgInactive));
                viewHolderVictor.mQsTileMainVictorIconBatterySaverInactive.setColorFilter(qsTileIconInactive);
                viewHolderVictor.mViewSpacerMainVictor.setBackground(getThemeDayNightSpacer());
                if (threeButtonNavbarEnabled(mContext)) {
                    viewHolderVictor.mViewNavbarMainVictor.setImageDrawable(getNavbarStyle(themeNavbarStyle));
                    viewHolderVictor.mViewNavbarMainVictor.setBackgroundColor(ColorUtils.setAlphaComponent(
                        mResources.getColor(R.color.themes_preview_navbar_bg), 100));
                } else {
                    viewHolderVictor.mViewNavbarMainVictor.setVisibility(View.GONE);
                }
                viewHolderVictor.mThemeMainVictorName.setText(themeName);
                viewHolderVictor.mThemeMainVictorName.setTypeface(getTypeface());
                break;
        }
    }

    public class ThemeWallpaper extends AsyncTask<Void, Void, Bitmap> {
        ImageView imageView;

        public ThemeWallpaper(ImageView imgView) {
            imageView = imgView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Bitmap doInBackground(Void... params) {
            File file = new File(mThemeWpBackup);
            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

    private Drawable getThemeDayNightBg() {
        Drawable themeDayNightBg = mResources.getDrawable(R.drawable.ic_themes_qs_background_restore);
        themeDayNightBg.setColorFilter(mThemeNightColor, Mode.SRC_IN);
        return themeDayNightBg;
    }

    private Drawable getThemeDayNightSpacer() {
        Drawable themeDayNightSpacer = mResources.getDrawable(R.drawable.ic_themes_qs_spacer_restore);
        int themeDayNightSpacerColor;
        if (!mIsNightMode) {
            themeDayNightSpacerColor = mResources.getColor(R.color.qs_tile_panel_spacer_day);
        } else {
            themeDayNightSpacerColor = mResources.getColor(R.color.qs_tile_panel_spacer_night);
        }
        themeDayNightSpacer.setColorFilter(themeDayNightSpacerColor, Mode.SRC_IN);
        return themeDayNightSpacer;
    }

    private Typeface getTypeface() {
        Typeface fontType = null;
        switch (mThemeFont) {
            case 1:
                fontType = Typeface.create(Typeface.DEFAULT, 400, false);
                break;
            case 2:
                fontType = Typeface.create(Typeface.SERIF, 400, false);
                break;
            case 3:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/Cagliostro.ttf");
                break;
            case 4:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/LGSmartGothic.ttf");
                break;
            case 5:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/Rosemary.ttf");
                break;
            case 6:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/SonySketch.ttf");
                break;
            case 7:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/GoogleSans.ttf");
                break;
            case 8:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/SlateFromOP.ttf");
                break;
            case 9:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/SamsungOne.ttf");
                break;
            case 10:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/FiraSans.ttf");
                break;
            case 11:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/Ubuntu.ttf");
                break;
            case 12:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/TitilliumWeb.ttf");
                break;
            case 13:
                fontType = Typeface.createFromAsset(mResources.getAssets(), "fonts/CaviarDreams.ttf");
                break;
        }
        return fontType;
    }

    private ShapeDrawable getShapeDrawable(String path, int color) {
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        final float pathSize = AdaptiveIconDrawable.MASK_SIZE;
        final Path shapePath = new Path(PathParser.createPathFromPathData(path));
        final int shapeSize = mResources.getDimensionPixelSize(R.dimen.dashboard_tile_image_size);
        shapeDrawable.setIntrinsicWidth(shapeSize);
        shapeDrawable.setIntrinsicHeight(shapeSize);
        shapeDrawable.getPaint().setColor(color);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawable.getPaint().setAntiAlias(true);
        shapeDrawable.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
        shapeDrawable.setShape(new PathShape(shapePath, pathSize, pathSize));
        return shapeDrawable;
    }

    private Drawable getNavbarStyle(String navStyle) {
        Drawable navbarStyle = null;
        switch (navStyle) {
            case "com.android.theme.navbar.asus":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_asus_layer);
                break;
            case "com.android.theme.navbar.oneplus":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_oneplus_layer);
                break;
            case "com.android.theme.navbar.oneui":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_oneui_layer);
                break;
            case "com.android.theme.navbar.tecno":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_tecno_layer);
                break;
            case "default":
                navbarStyle = mResources.getDrawable(R.drawable.navbar_stock_layer);
                break;
        }
        navbarStyle.setColorFilter(mResources.getColor(android.R.color.white), Mode.SRC_IN);

        return navbarStyle;
    }

    @Override
    public int getItemCount() {
        return mThemesList.size();
    }
}
