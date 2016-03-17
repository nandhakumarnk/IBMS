package com.rd.strivos.cobby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by COBURG DESIGN on 27-01-2016.
 */
public class ServiceVisitProductDtlsBaseAdapter extends BaseAdapter{

    ArrayList<ListDataInstallProductDtls> myList = new ArrayList<ListDataInstallProductDtls>();
    LayoutInflater inflater;
    Context context;
    int[] result;

    public ServiceVisitProductDtlsBaseAdapter(Context context, ArrayList<ListDataInstallProductDtls> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public ListDataInstallProductDtls getItem(int position) {
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
            convertView = inflater.inflate(R.layout.activity_service_visit_product_list_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ListDataInstallProductDtls currentListData = getItem(position);

        mViewHolder.txtPrincipal.setText(currentListData.getPrincipal());
        mViewHolder.txtPartNo.setText(currentListData.getProductPartNo());
        mViewHolder.txtMcPartNo.setText(currentListData.getPartNo());
        mViewHolder.txtMcSlNo.setText(currentListData.getSlNo());
        mViewHolder.txtContractType.setText(currentListData.getContractType());
        mViewHolder.txtId.setText(String.valueOf(currentListData.getId()));
        //mViewHolder.txtId.setText(currentListData.getProductPartNo());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView) view.findViewById(R.id.txt_frameIdDisplay);
                String tEXT = text.getText().toString();
                ((ServiceVisitGetProductDtls) context).startProductInstallActivity(Long.valueOf(tEXT).longValue());
            }
        });
//
//        convertView.setOnLongClickListener(new View.OnLongClickListener() {
//
//            @Override
//            public boolean onLongClick(View view) {
//                TextView text = (TextView) view.findViewById(R.id.txt_frameIdDisplay);
//                String tEXT = text.getText().toString();
//                ((PaymentsListPage) context).deletePayments(Long.valueOf(tEXT).longValue());
//                return true;
//            }
//        });

        return convertView;
    }

    private class MyViewHolder {

        TextView txtPrincipal;
        TextView txtPartNo;
        TextView txtMcPartNo;
        TextView txtMcSlNo;
        TextView txtContractType;
        TextView txtId;


        public MyViewHolder(View item) {
            txtPrincipal = (TextView) item.findViewById(R.id.txt_framePrincipal);
            txtPartNo = (TextView) item.findViewById(R.id.txt_framePart_No);
            txtMcPartNo = (TextView) item.findViewById(R.id.txt_frameMc_Part_No);
            txtMcSlNo = (TextView) item.findViewById(R.id.txt_frameMc_Sl_No);
            txtContractType = (TextView) item.findViewById(R.id.txt_frameContract_Type);
            txtId = (TextView) item.findViewById(R.id.txt_frameIdDisplay);
        }
    }
}
