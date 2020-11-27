package com.zhuo.mexp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhuo.mexp.fragment.MainFragment;
import com.zhuo.mexp.fragment.MineFragment;
import com.zhuo.mexp.fragment.RankingFragment;

public class MainActivity  extends AppCompatActivity implements View.OnClickListener {

    TextView mTabHome,mTabRanking,mTabMine;
    private Fragment mhomeFragment,mrankFragment,mmineFragment;
    private int mFragmentId=0;
    // 标记三个Fragment
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_RANK = 1;
    public static final int FRAGMENT_MINE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHome=findViewById(R.id.tv_home);
        mTabRanking=findViewById(R.id.tv_ranking);
        mTabMine=findViewById(R.id.tv_mine);
        //根据传入的Bundle对象判断Activity是正常启动还是销毁重建
        if(savedInstanceState == null){
            //设置第一个Fragment默认选中
            setFragment(mFragmentId);
        }
        mTabHome.setOnClickListener(this);
        mTabRanking.setOnClickListener(this);
        mTabMine.setOnClickListener(this);
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("fragment_id", mFragmentId);
        super.onSaveInstanceState(outState);
    }
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        //通过FragmentManager获取保存在FragmentTransaction中的Fragment实例
        mhomeFragment = mFragmentManager.findFragmentByTag("home_fragment");
        mrankFragment = mFragmentManager.findFragmentByTag("rank_fragment");
        mmineFragment = mFragmentManager.findFragmentByTag("mine_fragment");
        //恢复销毁前显示的Fragment
        setFragment(savedInstanceState.getInt("fragment_id"));
    }

    private void hideFragments(FragmentTransaction transaction){
        if(mhomeFragment != null){
            //隐藏Fragment
            transaction.hide(mhomeFragment);
            //将对应菜单栏设置为默认状态
            mTabHome.setTextColor(getResources().getColor(R.color.colorTextNormal));
            mTabHome.setCompoundDrawablesWithIntrinsicBounds(0,
                    R.mipmap.ic_home_normal,0,0);
        }
        if(mrankFragment != null){
            transaction.hide(mrankFragment);
            mTabRanking.setTextColor(getResources().getColor(R.color.colorTextNormal));
            mTabRanking.setCompoundDrawablesWithIntrinsicBounds(0,
                    R.mipmap.ic_rank_normal,0,0);
        }
        if(mmineFragment != null){
            transaction.hide(mmineFragment);
            mTabMine.setTextColor(getResources().getColor(R.color.colorTextNormal));
            mTabMine.setCompoundDrawablesWithIntrinsicBounds(0,
                    R.mipmap.ic_mine_normal,0,0);
        }
    }

    private void setFragment(int fragment_id) {
        //获取Fragment管理器
        FragmentManager mFragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        //隐藏所有Fragment
        hideFragments(mTransaction);
        switch (fragment_id){
            default:
                break;
            case FRAGMENT_HOME:
                mFragmentId = FRAGMENT_HOME;//记录当前显示的tab编号
                //设置菜单栏为选中状态（修改文字和图片颜色）
                mTabHome.setTextColor(getResources().getColor(R.color.colorTextPressed));
                mTabHome.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.mipmap.ic_home_selected,0,0);
                //显示对应Fragment
                if(mhomeFragment == null){
                    mhomeFragment = new MainFragment();
                    mTransaction.add(R.id.container, mhomeFragment, "home_fragment");
                }else {
                    mTransaction.show(mhomeFragment);
                }
                break;
            case FRAGMENT_RANK:
                mFragmentId = FRAGMENT_RANK;
                mTabRanking.setTextColor(getResources().getColor(R.color.colorTextPressed));
                mTabRanking.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.mipmap.ic_rank_selected,0,0);
                if(mrankFragment == null){
                    mrankFragment = new RankingFragment();
                    mTransaction.add(R.id.container, mrankFragment, "rank_fragment");
                }else {
                    mTransaction.show(mrankFragment);
                }
                break;
            case FRAGMENT_MINE:
                mFragmentId = FRAGMENT_MINE;
                mTabMine.setTextColor(getResources().getColor(R.color.colorTextPressed));
                mTabMine.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.mipmap.ic_mine_selected,0,0);
                if(mmineFragment == null){
                    mmineFragment = new MineFragment();
                    mTransaction.add(R.id.container, mmineFragment, "mine_fragment");
                }else {
                    mTransaction.show(mmineFragment);
                }
                break;
        }
        //提交事务
        mTransaction.commit();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_home:
                setFragment(0);
                break;
            case R.id.tv_ranking:
                setFragment(1);
                break;
            case R.id.tv_mine:
                setFragment(FRAGMENT_MINE);
                break;
        }
    }
}