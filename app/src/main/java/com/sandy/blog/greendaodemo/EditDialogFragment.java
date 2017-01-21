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
import android.widget.TextView;
import android.widget.Toast;

import com.sandy.blog.greendaodemo.dao.Singer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sandy Luo on 2017/1/17.
 */

public class EditDialogFragment extends DialogFragment {

    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_age)
    EditText mEtAge;
    @BindView(R.id.et_country)
    EditText mEtCountry;
    @BindView(R.id.btn_add)
    Button mBtnAdd;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    private Context mContext;

    private Singer singer;

    public void setSinger(Singer singer) {
        this.singer = singer;
    }


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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        mTvTitle.setText("编辑数据");
        mBtnAdd.setText("编辑");
        mEtName.setText(singer.getName());
        mEtAge.setText(singer.getSex());
        mEtCountry.setText(singer.getCountry());

    }

    @OnClick(R.id.btn_add)
    public void onClick() {
        String name = mEtName.getText().toString().trim();
        String sex = mEtAge.getText().toString().trim();
        String country = mEtCountry.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(sex) || TextUtils.isEmpty(country)) {
            Toast.makeText(mContext, "请填写完整的信息", Toast.LENGTH_SHORT).show();
        } else {
            if (onDataEditListener != null) {
                onDataEditListener.editData(singer,name, sex, country);
                dismiss();
            }
        }
    }

    private OnDataEditListener onDataEditListener;

    public void setOnDataEditListener(OnDataEditListener onDataAddListener) {
        this.onDataEditListener = onDataAddListener;
    }

    public interface OnDataEditListener {

        /**
         * 添加数据
         */
        void editData(Singer singer, String name, String sex, String country);
    }
}
