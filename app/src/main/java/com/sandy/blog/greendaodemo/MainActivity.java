package com.sandy.blog.greendaodemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.Gson;
import com.sandy.blog.greendaodemo.dao.DaoHelper;
import com.sandy.blog.greendaodemo.dao.DateSource;
import com.sandy.blog.greendaodemo.dao.Singer;
import com.sandy.blog.greendaodemo.dao.SingerDao;
import com.sandy.blog.greendaodemo.dao.SingerList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements AddDialogFragment.OnDataAddListener, SingerAdapter.OnDataControlListener, EditDialogFragment.OnDataEditListener {

    @BindView(R.id.et)
    EditText mEt;
    @BindView(R.id.search)
    FloatingActionButton mSearch;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.add)
    FloatingActionButton mFab;
    @BindView(R.id.add_list)
    FloatingActionButton mAddList;

    private String mCountry = "";   //目前列表显示的那个国家

    private List<Singer> mSingers = new ArrayList<>();
    private SingerAdapter mSingerAdapter;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    private void initView() {
        mRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        mSingerAdapter = new SingerAdapter(mSingers);
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mSingerAdapter);
        View footView = new View(this);
        footView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                DensityUtil.dip2px(this, 200)));
        mHeaderAndFooterWrapper.addFootView(footView);
        mRv.setAdapter(mHeaderAndFooterWrapper);

        mSingerAdapter.setOnDataControlListener(this);
    }

    @OnClick({R.id.search, R.id.add, R.id.add_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:
                mCountry = mEt.getText().toString().trim();
                notifyData(mCountry);
                break;
            case R.id.add:
                mCountry = "";
                showAddDialog();
                break;
            case R.id.add_list:
                mCountry = "";
                Gson gson = new Gson();
                SingerList singer = gson.fromJson(DateSource.DATA, SingerList.class);
                Log.d("MainActivity", DateSource.DATA);
                List<Singer> singers = singer.getSinger();
                addList(singers);
                break;
        }
    }


    /**
     * 显示添加的对话框
     */
    private void showAddDialog() {
        AddDialogFragment addDialog = new AddDialogFragment();
        addDialog.show(getSupportFragmentManager(), "AddDialog");
        addDialog.setOnDataAddListener(this);
    }


    /**
     * 数据添加
     */
    @Override
    public void addData(String name, String sex, String country) {
        SingerDao singerDao = DaoHelper.getInstance().getSingerDao();
        Singer singer = new Singer(null, name, sex, country);
        singerDao.insert(singer);
        notifyData(null);
    }

    private void addList(List<Singer> singers) {
        SingerDao singerDao = DaoHelper.getInstance().getSingerDao();
        singerDao.insertInTx(singers);
        notifyData(null);
    }


    /**
     * 更新数据
     */
    private void notifyData(String country) {
        List<Singer> singers = DaoHelper.getInstance().getSingerList(country);
        mSingers.clear();
        mSingers.addAll(singers);
        mHeaderAndFooterWrapper.notifyDataSetChanged();
    }

    @Override
    public void onDelete(Singer singer) {
        SingerDao singerDao = DaoHelper.getInstance().getSingerDao();
        singerDao.delete(singer);
        notifyData(mCountry);
    }

    @Override
    public void onEdit(int position) {
        List<Singer> singers = DaoHelper.getInstance().getSingerList(mCountry);
        EditDialogFragment editDialog = new EditDialogFragment();
        editDialog.setSinger(singers.get(position));
        editDialog.show(getSupportFragmentManager(), "AddDialog");
        editDialog.setOnDataEditListener(this);
    }

    @Override
    public void editData(Singer singer, String name, String sex, String country) {
        SingerDao singerDao = DaoHelper.getInstance().getSingerDao();
        singerDao.update(new Singer(Long.valueOf(singer.getId()), name, sex, country));
        notifyData(mCountry);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SingerDao singerDao = DaoHelper.getInstance().getSingerDao();
        singerDao.deleteAll();
    }
}
