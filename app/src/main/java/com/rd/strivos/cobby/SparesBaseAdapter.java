package com.rd.strivos.cobby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by COBURG DESIGN on 21-01-2016.
 */
public class SparesBaseAdapter extends BaseAdapter {

    ArrayList<ListDataSpares> myList = new ArrayList<ListDataSpares>();
    LayoutInflater inflater;
    Context context;
    int[] result;

    public SparesBaseAdapter(Context context, ArrayList<ListDataSpares> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }


    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public ListDataSpares getItem(int position) {
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
            convertView = inflater.inflate(R.layout.activity_spares_list_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ListDataSpares currentListData = getItem(position);

        mViewHolder.txtPartNo.setText(currentListData.getPartNo());
        mViewHolder.txtDescription.setText(currentListData.getShortDescription());
        mViewHolder.txtUOM.setText(currentListData.getUOM());
        mViewHolder.txtQuantity.setText(currentListData.getQuantity());
        mViewHolder.txtId.setText(String.valueOf(currentListData.getId()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView) view.findViewById(R.id.txt_frameIdDisplay);
                String tEXT = text.getText().toString();
                ((SparesListPage) context).startAddSparesActivity(Long.valueOf(tEXT).longValue());
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                TextView text = (TextView) view.findViewById(R.id.txt_frameIdDisplay);
                String tEXT = text.getText().toString();
                ((SparesListPage) context).deleteSpares(Long.valueOf(tEXT).longValue());
                return true;
            }
        });

        return convertView;
    }

    private class MyViewHolder {

        TextView txtPartNo;
        TextView txtDescription;
        TextView txtUOM;
        TextView txtQuantity;
        TextView txtId;


        public MyViewHolder(View item) {
            txtPartNo = (TextView) item.findViewById(R.id.txt_framePartNo);
            txtDescription = (TextView) item.findViewById(R.id.txt_frameDescrip);
            txtUOM = (TextView) item.findViewById(R.id.txt_frameUOM);
            txtQuantity = (TextView) item.findViewById(R.id.txt_frameQuantityDisplay);
            txtId = (TextView) item.findViewById(R.id.txt_frameIdDisplay);
        }
    }
}
