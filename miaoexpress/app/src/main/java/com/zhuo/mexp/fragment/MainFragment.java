package com.zhuo.mexp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhuo.mexp.activity.PickExpActivity;
import com.zhuo.mexp.activity.R;
import com.zhuo.mexp.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Banner banner;//banner组件
    List mlist;//图片资源
    List<String> mlist1;//轮播标题
    private Toolbar toolbar;
    public MainFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        toolbar=view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        view.findViewById(R.id.sendExpress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(getContext(), SentExpActivity.class));
            }
        });
        view.findViewById(R.id.pickExpress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PickExpActivity.class));

                //getActivity().finish();
            }
        });
        mlist = new ArrayList<>();
        mlist.add(R.mipmap.lunbo1);
        mlist.add(R.mipmap.lunbo2);
        mlist.add(R.mipmap.lunbo3);
        mlist1 = new ArrayList<>();
        mlist1.add("特色快速业务");
        mlist1.add("送货到家服务");
        mlist1.add("一键查询服务");
        banner = view.findViewById(R.id.main_banner);
        banner.setImageLoader(new GlideImageLoader());   //设置图片加载器
        banner.setImages(mlist);//设置图片源
        banner.setBannerTitles(mlist1);//设置标题源
        banner.setDelayTime(2000);//设置轮播事件，单位毫秒
        banner.setBannerAnimation(Transformer.ZoomIn);
        banner.setOnBannerListener(new OnBannerListener() {
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity(), "点击了轮播第"+position+"个图片" + position, Toast.LENGTH_SHORT).show();
            }
        });
        banner.setIndicatorGravity(BannerConfig.RIGHT);//设置指示器的位置
        banner.start();//开始轮播，一定要调用此方法。
        return view;
    }
}