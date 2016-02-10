package com.iktwo.notificationmirror.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.iktwo.notificationmirror.R;
import com.iktwo.notificationmirror.utils.ApplicationPreferences;

public class MainFragment extends Fragment {
    private EditText mEditTextUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        mEditTextUrl = (EditText) v.findViewById(R.id.edit_url);

        String url = ApplicationPreferences.getUrl(getContext());

        if (mEditTextUrl.getText().toString().isEmpty()) {
            mEditTextUrl.setText(url);
        }

        mEditTextUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ApplicationPreferences.setUrl(MainFragment.this.getContext(), mEditTextUrl.getText().toString());
            }
        });

        Button button = (Button) v.findViewById(R.id.button_settings);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                startActivity(intent);
            }
        });

        return v;
    }
}
