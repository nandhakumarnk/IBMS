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
public class ExpenseListPageServiceVisitBaseAdapter extends BaseAdapter{


    ArrayList<ListDataExpense> myList = new ArrayList<ListDataExpense>();
    LayoutInflater inflater;
    Context context;
    int[] result;

    public ExpenseListPageServiceVisitBaseAdapter(Context context, ArrayList<ListDataExpense> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public ListDataExpense getItem(int position) {
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
            convertView = inflater.inflate(R.layout.activity_list_expense_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ListDataExpense currentListData = getItem(position);

        mViewHolder.txtExpenseType.setText(currentListData.getExpenseType());
        mViewHolder.txtExpenseAmount.setText(currentListData.getExpenseAmount());
        mViewHolder.txtId.setText(String.valueOf(currentListData.getId()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView) view.findViewById(R.id.txt_frameIdDisplay);
                String tEXT = text.getText().toString();
                ((ExpenseListPageServiceVisit) context).startAddExpensesActivity(Long.valueOf(tEXT).longValue());
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                TextView text = (TextView) view.findViewById(R.id.txt_frameIdDisplay);
                String tEXT = text.getText().toString();
                ((ExpenseListPageServiceVisit) context).deleteExpense(Long.valueOf(tEXT).longValue());
                return true;
            }
        });

        return convertView;
    }

    private class MyViewHolder {

        TextView txtExpenseType;
        TextView txtExpenseAmount;
        TextView txtId;


        public MyViewHolder(View item) {
            txtExpenseType = (TextView) item.findViewById(R.id.txt_frameExpenseType);
            txtExpenseAmount = (TextView) item.findViewById(R.id.txt_frameExpenseAmount);
            txtId = (TextView) item.findViewById(R.id.txt_frameIdDisplay);
        }
    }
}
