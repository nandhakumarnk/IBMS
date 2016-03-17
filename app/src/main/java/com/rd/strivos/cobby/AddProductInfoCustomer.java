package com.rd.strivos.cobby;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;

public class AddProductInfoCustomer extends Activity{

	Spinner spnPrincipal, spnPartno, spnShortDesc, spnProductType, spnUOM;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_product_info_customer);


	}

}
