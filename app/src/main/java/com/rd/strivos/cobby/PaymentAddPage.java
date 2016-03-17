package com.rd.strivos.cobby;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by COBURG DESIGN on 22-01-2016.
 */
public class PaymentAddPage extends AppCompatActivity {

    EditText _VendorName, _RefNo, _RefDate, _Amount, _RecedAmount, _RefDocNo, _RefDocDate;
    private TextInputLayout inputLayoutVendorName, inputLayoutRefNo, inputLayoutRefDate, inputLayoutAmount, inputLayoutRecedAmount,
            inputLayoutRefDocNo, inputLayoutRefDocDate;
    Button _Submit, _Clear;
    private int day, month, year;
    int dueDate, dueMonth, dueYear;
    private Calendar cal;
    PaymentsDBHelper paymentsDBHelper = new PaymentsDBHelper(this);
    String companyName, projectType;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments_add_page);


        _VendorName = (EditText) findViewById(R.id.et_vendorname);
        _RefNo = (EditText) findViewById(R.id.et_Refno);
        _RefDate = (EditText) findViewById(R.id.et_refdate);
        _Amount = (EditText) findViewById(R.id.et_Amount);
        _RecedAmount = (EditText) findViewById(R.id.et_RecedAmount);
        _RefDocNo = (EditText) findViewById(R.id.et_refdocno);
        _RefDocDate = (EditText) findViewById(R.id.et_refdocdate);

        inputLayoutVendorName = (TextInputLayout) findViewById(R.id.input_layout_vendor_name);
        inputLayoutRefNo = (TextInputLayout) findViewById(R.id.input_layout_ref_no);
        inputLayoutRefDate = (TextInputLayout) findViewById(R.id.input_layout_ref_date);
        inputLayoutAmount = (TextInputLayout) findViewById(R.id.input_layout_amount);
        inputLayoutRecedAmount = (TextInputLayout) findViewById(R.id.input_layout_reced_amount);
        inputLayoutRefDocNo = (TextInputLayout) findViewById(R.id.input_layout_ref_doc_no);
        inputLayoutRefDocDate = (TextInputLayout) findViewById(R.id.input_layout_ref_doc_date);

        _Submit = (Button) findViewById(R.id.add_service);
        _Clear = (Button) findViewById(R.id.remove_service);

        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        Bundle b = getIntent().getExtras();
        companyName = b.getString("CompanyName");
        projectType = b.getString("ProjectType");
        id = getIntent().getExtras().getLong("id");

        if (id == -1) {
            //personDetails = new MainCommonPersonModel();
//            getfromdatabase();
//            loadSpinner();
        } else {
            try {
                int value = (int) id;

                Cursor c = paymentsDBHelper.getPaymentsListFull(value);
                if (c.getCount() > 0) {
                    _VendorName.setText(c.getString(1));
                    _RefNo.setText(c.getString(2));
                    _RefDate.setText(c.getString(3));
                    _Amount.setText(c.getString(4));
                    _RecedAmount.setText(c.getString(5));
                    _RefDocNo.setText(c.getString(6));
                    _RefDocDate.setText(c.getString(7));
                }
                paymentsDBHelper.close();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                        .show();
                e.printStackTrace();
            }
        }

        _RefDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });

        _RefDocDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });

        _Submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                submitForm();
                Toast.makeText(getApplicationContext(), "Saved Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        _Clear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                clearAll();
                Toast.makeText(getApplicationContext(), "Data has been cleared!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submitForm() {
        final String vendorName = _VendorName.getText().toString().trim();
        final String refNo = _RefNo.getText().toString().trim();
        final String refDate = _RefDate.getText().toString().trim();
        final String amount = _Amount.getText().toString().trim();
        final String recedAmount = _RecedAmount.getText().toString().trim();
        final String refDocNo = _RefDocNo.getText().toString().trim();
        final String refDocDate = _RefDocDate.getText().toString().trim();
        String CompanyName = companyName;
        String ProjectType = projectType;
        String Status = "N";

        if (!validateVendorName()) {
            return;
        }

        if (!validateRefNo()) {
            return;
        }

        if (!validateRefDate()) {
            return;
        }

        if (!isValidAmount()) {
            return;
        }

        if (!validateRecedAmount()) {
            return;
        }
        if (!validateRefDocNo()) {
            return;
        }
        if (!validateRefDocDate()) {
            return;
        }

        if ((int) id < 0) {
            paymentsDBHelper.insertPayments(vendorName, refNo, refDate, amount, recedAmount, refDocNo, refDocDate, ProjectType, CompanyName, Status);
        } else {
            paymentsDBHelper.updatePayments(id, vendorName, refNo, refDate, amount, recedAmount, refDocNo, refDocDate, ProjectType, CompanyName);
        }

        setResult(RESULT_OK);

        finish();
    }

    private boolean validateVendorName() {
        if (_VendorName.getText().toString().trim().isEmpty()) {
            inputLayoutVendorName.setError(getString(R.string.err_msg_vendor));
            requestFocus(_VendorName);
            return false;
        } else {
            inputLayoutVendorName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateRefNo() {
        if (_RefNo.getText().toString().trim().isEmpty()) {
            inputLayoutRefNo.setError(getString(R.string.err_msg_refno));
            requestFocus(_RefNo);
            return false;
        } else {
            inputLayoutRefNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateRefDate() {
        if (_RefDate.getText().toString().trim().isEmpty()) {
            inputLayoutRefDate.setError(getString(R.string.err_msg_refdate));
            requestFocus(_RefDate);
            return false;
        } else {
            inputLayoutRefDate.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isValidAmount() {
        if (_Amount.getText().toString().trim().isEmpty()) {
            inputLayoutAmount.setError(getString(R.string.err_msg_amount));
            requestFocus(_Amount);
            return false;
        } else {
            inputLayoutAmount.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateRecedAmount() {
        if (_RecedAmount.getText().toString().trim().isEmpty()) {
            inputLayoutRecedAmount.setError(getString(R.string.err_msg_recedamount));
            requestFocus(_RecedAmount);
            return false;
        } else {
            inputLayoutRecedAmount.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateRefDocNo() {
        if (_RefDocNo.getText().toString().trim().isEmpty()) {
            inputLayoutRefDocNo.setError(getString(R.string.err_msg_refdocno));
            requestFocus(_RefDocNo);
            return false;
        } else {
            inputLayoutRefDocNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateRefDocDate() {
        if (_RefDocDate.getText().toString().trim().isEmpty()) {
            inputLayoutRefDocDate.setError(getString(R.string.err_msg_refdocdate));
            requestFocus(_RefDocDate);
            return false;
        } else {
            inputLayoutRefDocDate.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void clearAll() {
        _VendorName.setText("");
        _RefNo.setText("");
        _RefDate.setText("");
        _Amount.setText("");
        _RecedAmount.setText("");
        _RefDocNo.setText("");
        _RefDocDate.setText("");
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {

        if (id == 0) {
            return new DatePickerDialog(this, datePickerListener, year, month,
                    day);
        }
        if (id == 1) {
            return new DatePickerDialog(this, datePickerListener1, year, month,
                    day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            dueDate = selectedDay;
            dueMonth = selectedMonth;
            dueYear = selectedYear;

            _RefDocDate.setText(new StringBuilder().append(pad(selectedDay))
                    .append("/").append(pad(selectedMonth + 1)).append("/")
                    .append(pad(selectedYear)));
        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListener1 = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            dueDate = selectedDay;
            dueMonth = selectedMonth;
            dueYear = selectedYear;

            _RefDate.setText(new StringBuilder().append(pad(selectedDay))
                    .append("/").append(pad(selectedMonth + 1)).append("/")
                    .append(pad(selectedYear)));
        }
    };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

}
