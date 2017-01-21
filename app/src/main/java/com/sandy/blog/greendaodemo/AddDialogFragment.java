package com.sandy.blog.greendaodemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sandy Luo on 2017/1/17.
 */

public class AddDialogFragment extends DialogFragment {

    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_age)
    EditText mEtAge;
    @BindView(R.id.et_country)
    EditText mEtCountry;
    @BindView(R.id.btn_add)
    Button mBtnAdd;

    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(mContext).inflate(R.layout.fragment_add_dialog, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.btn_add)
    public void onClick() {
        String name = mEtName.getText().toString().trim();
        String sex = mEtAge.getText().toString().trim();
        String country = mEtCountry.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(sex) || TextUtils.isEmpty(country)) {
            Toast.makeText(mContext, "请填写完整的信息", Toast.LENGTH_SHORT).show();
        } else {
            if (onDataAddListener != null) {
                onDataAddListener.addData(name, sex, country);
                dismiss();
            }
        }
    }

    private OnDataAddListener onDataAddListener;

    public void setOnDataAddListener(OnDataAddListener onDataAddListener) {
        this.onDataAddListener = onDataAddListener;
    }

    public interface OnDataAddListener {

        /**
         * 添加数据
         */
        void addData(String name, String sex, String country);
    }
}
