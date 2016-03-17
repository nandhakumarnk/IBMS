package com.rd.strivos.cobby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by COBURG DESIGN on 18-02-2016.
 */
public class CommissioningPreCommProductListBaseAdapter extends BaseAdapter {

    ArrayList<ListDataCommissioningPrecommList> myList = new ArrayList<ListDataCommissioningPrecommList>();
    LayoutInflater inflater;
    Context context;
    int[] result;

    public CommissioningPreCommProductListBaseAdapter(Context context, ArrayList<ListDataCommissioningPrecommList> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public ListDataCommissioningPrecommList getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (myList != null) {
            return myList.get(position).id;
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_commissioning_pre_comm_list_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ListDataCommissioningPrecommList currentListData = getItem(position);

        mViewHolder.txtProductPartNo.setText(currentListData.getProductPartNo());
        mViewHolder.txtPartNo.setText(currentListData.getProductPartNo());
        mViewHolder.txtMcSlNo.setText(currentListData.getSlNo());
        mViewHolder.txtId.setText(String.valueOf(currentListData.getId()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView) view.findViewById(R.id.txt_frameIdDisplay);
                String tEXT = text.getText().toString();
                ((CommissioningPrecommList) context).startProductInstallActivity(Long.valueOf(tEXT).longValue());
            }
        });

        return convertView;
    }

    private class MyViewHolder {

        TextView txtProductPartNo;
        TextView txtPartNo;
        TextView txtMcSlNo;
        TextView txtId;

        public MyViewHolder(View item) {
            txtProductPartNo = (TextView) item.findViewById(R.id.txt_frameppno);
            txtPartNo = (TextView) item.findViewById(R.id.txt_framePart_No);
            txtMcSlNo = (TextView) item.findViewById(R.id.txt_frameMc_Sl_No);
            txtId = (TextView) item.findViewById(R.id.txt_frameIdDisplay);
        }
    }
}
