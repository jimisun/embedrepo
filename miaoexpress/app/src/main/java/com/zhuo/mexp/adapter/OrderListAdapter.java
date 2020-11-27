package com.zhuo.mexp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuo.mexp.activity.R;
import com.zhuo.mexp.model.Order;

import java.util.ArrayList;

public class OrderListAdapter extends BaseAdapter {
    Order order=null;
    LayoutInflater layoutInflater;
    ArrayList<Order> list ;
    public OrderListAdapter(Context context, ArrayList<Order> list) {
        layoutInflater = LayoutInflater.from(context);
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.orderlist_item, null);
            viewHolder.layout=convertView.findViewById(R.id.all);
            viewHolder.imageView=convertView.findViewById(R.id.img);
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.phone = convertView.findViewById(R.id.phone);
            viewHolder.address = convertView.findViewById(R.id.address);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        order=list.get(position);
        if (order.getOrder_distribut_type().equals("代发大件")||order.getOrder_distribut_type().equals("代发小件"))
        {
            viewHolder.layout.setBackgroundResource(R.mipmap.s);}
        else {
            viewHolder.layout.setBackgroundResource(R.mipmap.t);
        }
        if (order.getOrder_picpath()!=null&&!order.getOrder_picpath().equals("")){
            viewHolder.imageView.setImageURI(Uri.parse(order.getOrder_picpath()));
        }
        viewHolder.name.setText(order.getOrder_receiver_name());
        viewHolder.phone.setText(order.getOrder_receiver_tel()+"");
        viewHolder.address.setText(order.getOrder_receiver_address());
        return convertView;
    }
    class ViewHolder {
        public ImageView imageView;
        public TextView address;
        public TextView name;
        public TextView phone;
        public LinearLayout layout;
    }
}

