package com.github.miao1007.wordpressclient.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.github.miao1007.wordpressclient.R;
import com.github.miao1007.wordpressclient.utils.CleanUtils;

public class SettingsActivity extends BackableActivity implements View.OnClickListener {

    Activity context = SettingsActivity.this;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_setting);
        findViewById(R.id.frag_setting_login).setOnClickListener(this);
        findViewById(R.id.frag_setting_clean_cache).setOnClickListener(this);
        findViewById(R.id.frag_setting_feedback).setOnClickListener(this);
        findViewById(R.id.frag_setting_about_us).setOnClickListener(this);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //TODO: coming soon
        //view.findViewById(R.id.frag_setting_check_update).setOnClickListener(this);
        findViewById(R.id.frag_setting_exit).setOnClickListener(this);
        //Reused include view
        View includeview = findViewById(R.id.frag_setting_include_text_size);
        includeview.findViewById(R.id.button_font_small).setOnClickListener(this);
        includeview.findViewById(R.id.button_font_large).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frag_setting_login:
                startActivity(new Intent(context, LoginActivity.class));
                break;
            case R.id.frag_setting_feedback:
                startActivity(new Intent(context, FeedbackActivity.class));
                break;
            case R.id.frag_setting_about_us:
                startActivity(new Intent(context, AboutActivity.class));
                break;
            case R.id.frag_setting_exit:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            //Set And Save the Text Size
            case R.id.button_font_small:
                preferences = getSharedPreferences("textsize", 0);
                editor = preferences.edit();
                editor.putInt("size", preferences.getInt("size", 100) - 20 > 20 ? preferences.getInt("size", 100) - 20 : 0);
                editor.commit();
                Toast.makeText(context, "当前缩放为" + String.valueOf(preferences.getInt("size", 100)), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_font_large:
                preferences = context.getSharedPreferences("textsize", 0);
                editor = preferences.edit();
                editor.putInt("size", preferences.getInt("size", 100) + 20 < 200 ? preferences.getInt("size", 100) + 20 : 0);
                editor.commit();
                Toast.makeText(context, "当前缩放为" + String.valueOf(preferences.getInt("size", 100)), Toast.LENGTH_SHORT).show();
                break;
            case R.id.frag_setting_clean_cache:
                //create a dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("提示")
                        .setMessage("确定清楚缓存吗")
                        .setPositiveButton("确认",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                CleanUtils.cleanInternalCache(context);
                                CleanUtils.cleanExternalCache(context);
                                CleanUtils.cleanDatabases(context);
                                CleanUtils.cleanSharedPreference(context);
                                Toast.makeText(context, "清理完成" ,Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("取消",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();break;

        }
    }
}
