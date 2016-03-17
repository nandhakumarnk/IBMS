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
public class PaymentsListPageServiceVisitBaseAdapter extends BaseAdapter {

    ArrayList<ListDataPayments> myList = new ArrayList<ListDataPayments>();
    LayoutInflater inflater;
    Context context;
    int[] result;

    public PaymentsListPageServiceVisitBaseAdapter(Context context, ArrayList<ListDataPayments> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public ListDataPayments getItem(int position) {
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
            convertView = inflater.inflate(R.layout.activity_payments_list_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ListDataPayments currentListData = getItem(position);

        mViewHolder.txtVendorName.setText(currentListData.getVendor());
        mViewHolder.txtRefNo.setText(currentListData.getRefNo());
        mViewHolder.txtRefDate.setText(currentListData.getRefDate());
        mViewHolder.txtAmount.setText(currentListData.getAmount());
        mViewHolder.txtRecedAmount.setText(currentListData.getRecedAmount());
        mViewHolder.txtRefDocNo.setText(currentListData.getRefDocNo());
        mViewHolder.txtRefDocDate.setText(currentListData.getRefDocDate());
        mViewHolder.txtId.setText(String.valueOf(currentListData.getId()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView) view.findViewById(R.id.txt_frameIdDisplay);
                String tEXT = text.getText().toString();
                ((PaymentsListPageServiceVisit) context).startAddPaymentActivity(Long.valueOf(tEXT).longValue());
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                TextView text = (TextView) view.findViewById(R.id.txt_frameIdDisplay);
                String tEXT = text.getText().toString();
                ((PaymentsListPageServiceVisit) context).deletePayments(Long.valueOf(tEXT).longValue());
                return true;
            }
        });

        return convertView;
    }

    private class MyViewHolder {

        TextView txtVendorName;
        TextView txtRefNo;
        TextView txtRefDate;
        TextView txtAmount;
        TextView txtRecedAmount;
        TextView txtRefDocNo;
        TextView txtRefDocDate;
        TextView txtId;


        public MyViewHolder(View item) {
            txtVendorName = (TextView) item.findViewById(R.id.txt_frameVendorname_service);
            txtRefNo = (TextView) item.findViewById(R.id.txt_frameRefNo_service);
            txtRefDate = (TextView) item.findViewById(R.id.txt_frameRefDate_service);
            txtAmount = (TextView) item.findViewById(R.id.txt_frameAmount_service);
            txtRecedAmount = (TextView) item.findViewById(R.id.txt_frameRecedAmount_service);
            txtRefDocNo = (TextView) item.findViewById(R.id.txt_frameRefDocNo_service);
            txtRefDocDate = (TextView) item.findViewById(R.id.txt_frameRefDocDate_service);
            txtId = (TextView) item.findViewById(R.id.txt_frameIdDisplay);
        }
    }
}
