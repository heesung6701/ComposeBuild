/*
 * Copyright (C) 2015 The Android Open Source Project
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

package com.example.androidx.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import com.example.androidx.Cheeses;
import com.example.androidx.R;

/**
 * This demonstrates idiomatic usage of AppCompat's AlertDialog.
 */
public class AlertDialogUsage extends AppCompatActivity {

    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog_usage);

        mSpinner = findViewById(R.id.spinner_dialogs);

        // Add an OnClickListener to show our selected dialog
        findViewById(R.id.btn_show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectedDialog();
            }
        });
    }

    private void showSelectedDialog() {
        switch (mSpinner.getSelectedItemPosition()) {
            case 0:
                showSimpleDialog();
                break;
            case 1:
                showSimpleButtonsDialog();
                break;
            case 2:
                showSimpleWithMoreButtonsDialog();
                break;
            case 3:
                showSimpleWithStackedButtonsDialog();
                break;
            case 4:
                showSingleChoiceDialog();
                break;
            case 5:
                showMultiChoiceDialog();
                break;
        }
    }

    private void showSimpleDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(R.string.dialog_title);
        b.setMessage(R.string.dialog_content);
        b.show();
    }

    private void showSimpleButtonsDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(R.string.dialog_title);
        b.setMessage(R.string.dialog_content);
        b.setNegativeButton("-ve", null);
        b.setPositiveButton("+ve", null);
        b.show();
    }

    private void showSimpleWithMoreButtonsDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(R.string.dialog_title);
        b.setMessage(R.string.dialog_content);
        b.setNegativeButton("-ve", null);
        b.setNegativeButtonIcon(AppCompatResources.getDrawable(this, R.drawable.ic_media_pause));
        b.setNeutralButton("=ve", null);
        b.setNeutralButtonIcon(AppCompatResources.getDrawable(this, R.drawable.ic_media_play));
        b.setPositiveButton("+ve", null);
        b.setPositiveButtonIcon(AppCompatResources.getDrawable(this, R.drawable.ic_media_stop));
        b.show();
    }

    private void showSimpleWithStackedButtonsDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(R.string.dialog_title);
        b.setMessage(R.string.dialog_content);
        b.setNegativeButton("Very long negative", null);
        b.setNeutralButton("Very long neutral", null);
        b.setPositiveButton("Very long positive", null);
        b.show();
    }

    private void showSingleChoiceDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(R.string.dialog_title);
        b.setSingleChoiceItems(Cheeses.sCheeseStrings, 0, null);
        b.setPositiveButton("OK", null);
        b.show();
    }

    private void showMultiChoiceDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(R.string.dialog_title);
        b.setMultiChoiceItems(Cheeses.sCheeseStrings, null, null);
        b.setPositiveButton("OK", null);
        b.show();
    }


}
