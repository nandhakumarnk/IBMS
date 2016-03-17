package com.rd.strivos.cobby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by COBURG DESIGN on 22-02-2016.
 */
public class PersonAddListPageServiceVisitBaseAdapter extends BaseAdapter{

    ArrayList<ListData> myList = new ArrayList<ListData>();
    LayoutInflater inflater;
    Context context;
    int [] result;

    public PersonAddListPageServiceVisitBaseAdapter(Context context, ArrayList<ListData> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public ListData getItem(int position) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_add_more_person_list_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ListData currentListData = getItem(position);

        mViewHolder.txtPersonName.setText(currentListData.getPersonName());
        mViewHolder.txtPersonDesignation.setText(currentListData.getPersonDesignation());
        mViewHolder.txtPersonMobile.setText(currentListData.getPersonMobile());
        mViewHolder.txtPersonEmail.setText(currentListData.getPersonEmail());
        mViewHolder.txtId.setText(String.valueOf(currentListData.getId()));


//        String [] result;

        //convertView.setTag(Long.valueOf(currentListData.getId()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView) view.findViewById(R.id.txt_frameIdDisplay);
                String tEXT = text.getText().toString();
                ((PersonAddListPageServiceVisit) context).startPersonDetailsActivity(Long.valueOf(tEXT).longValue());
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                TextView text = (TextView) view.findViewById(R.id.txt_frameIdDisplay);
                String tEXT = text.getText().toString();
                ((PersonAddListPageServiceVisit) context).deletePersons(Long.valueOf(tEXT).longValue());
                return true;
            }
        });

        return convertView;
    }

    private class MyViewHolder {

        TextView txtPersonName;
        TextView txtPersonDesignation;
        TextView txtPersonMobile;
        TextView txtPersonEmail;
        TextView txtId;


        public MyViewHolder(View item) {
            txtPersonName = (TextView) item.findViewById(R.id.txt_framePersonNameDisplay);
            txtPersonDesignation = (TextView) item.findViewById(R.id.txt_frameDesignationDisplay);
            txtPersonMobile = (TextView) item.findViewById(R.id.txt_frameMobileDisplay);
            txtPersonEmail = (TextView) item.findViewById(R.id.txt_frameEmailDisplay);
            txtId = (TextView) item.findViewById(R.id.txt_frameIdDisplay);
        }
    }
}
