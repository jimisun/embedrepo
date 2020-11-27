package com.zhuo.mexp.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhuo.mexp.activity.R;
import com.zhuo.mexp.dao.DistributorDao;
import com.zhuo.mexp.model.Distributor;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RankingFragment extends Fragment {

    ListView listView;
    List<Distributor> list;
    public RankingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);
        listView = view.findViewById(R.id.paihanglist);
        DistributorDao distributor_dao = new DistributorDao(getContext());
        list = distributor_dao.distributorDesc();
        MyListViewAdapter myListViewAdapter=new MyListViewAdapter(getContext(),list);
        listView.setAdapter(myListViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Distributor distributor=list.get(position);
                Intent intent=new Intent();
                intent.putExtra("ispsy","2");
                intent.putExtra("name",distributor.getDistributor_name());
                intent.putExtra("phone",distributor.getDistributor_tel()+"");
                intent.putExtra("idcar",distributor.getDistributor_idcar()+"");
                intent.putExtra("jdcs",distributor.getDistributor_singularnum()+"");
                Log.i("data",distributor.getDistributor_idcar()+distributor.getDistributor_tel()+"");
                //intent.setClass(getContext(), PersonInfoActivity.class);
                startActivity(intent);
            }
        });
        return  view;
    }

    class MyListViewAdapter extends BaseAdapter{
        List<Distributor> distributorList;
        LayoutInflater layoutInflater;
        public MyListViewAdapter(Context context, List<Distributor> dList){
            layoutInflater=LayoutInflater.from(context);
            distributorList=dList;
        }

        @Override
        public int getCount() {
            return distributorList.size();
        }

        @Override
        public Object getItem(int position) {
            return distributorList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder=new ViewHolder();
            if(convertView==null){
                convertView=layoutInflater.inflate(R.layout.list_item,null);
                viewHolder.mingcitv=convertView.findViewById(R.id.mingci);
                viewHolder.psyimageview=convertView.findViewById(R.id.psy_image);
                viewHolder.psynametv=convertView.findViewById(R.id.psy_name);
                viewHolder.psyjdcstv=convertView.findViewById(R.id.psy_jdcs);
                convertView.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder)convertView.getTag();
            }
            int pos=position+1;
            viewHolder.mingcitv.setText(pos+"");
            Bitmap bitmap= BitmapFactory.decodeFile(distributorList.get(position).getDistributor_picPath());
            viewHolder.psyimageview.setImageBitmap(bitmap);
            viewHolder.psynametv.setText(distributorList.get(position).getDistributor_name());//distributorList.get(position)->Distibutor.get
            viewHolder.psyjdcstv.setText(distributorList.get(position).getDistributor_singularnum()+"");
            return convertView;
        }
    }

    class  ViewHolder{
        TextView mingcitv;
        ImageView psyimageview;
        TextView psynametv;
        TextView psyjdcstv;
    }

}