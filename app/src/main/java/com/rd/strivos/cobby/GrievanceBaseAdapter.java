package com.rd.strivos.cobby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by COBURG DESIGN on 23-01-2016.
 */
public class GrievanceBaseAdapter extends BaseAdapter {

    ArrayList<ListDataGrievance> myList = new ArrayList<ListDataGrievance>();
    LayoutInflater inflater;
    Context context;
    int[] result;

    public GrievanceBaseAdapter(Context context, ArrayList<ListDataGrievance> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public ListDataGrievance getItem(int position) {
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
            convertView = inflater.inflate(R.layout.activity_grievance_list_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ListDataGrievance currentListData = getItem(position);

        mViewHolder.txtGrievance.setText(currentListData.getGrievance());
        mViewHolder.txtSolutions.setText(currentListData.getSolution());
        mViewHolder.txtId.setText(String.valueOf(currentListData.getId()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView) view.findViewById(R.id.txt_frameIdDisplay);
                String tEXT = text.getText().toString();
                ((GrievanceListPage) context).startAddGrievanceActivity(Long.valueOf(tEXT).longValue());
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                TextView text = (TextView) view.findViewById(R.id.txt_frameIdDisplay);
                String tEXT = text.getText().toString();
                ((GrievanceListPage) context).deleteGrievance(Long.valueOf(tEXT).longValue());
                return true;
            }
        });

        return convertView;
    }

    private class MyViewHolder {

        TextView txtGrievance;
        TextView txtSolutions;
        TextView txtId;


        public MyViewHolder(View item) {
            txtGrievance = (TextView) item.findViewById(R.id.txt_frameGrievance);
            txtSolutions = (TextView) item.findViewById(R.id.txt_frameSolutions);
            txtId = (TextView) item.findViewById(R.id.txt_frameIdDisplay);
        }
    }
}
