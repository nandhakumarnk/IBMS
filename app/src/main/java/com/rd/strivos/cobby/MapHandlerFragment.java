package com.rd.strivos.cobby;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapHandlerFragment extends Fragment{

	
    View rootView;
    
    private MapView mMapView;
    private GoogleMap mMap;
    private Bundle mBundle;
    
    public MapHandlerFragment() {
		// TODO Auto-generated constructor stub
	}
    
    @Override
	public View onCreateView(final LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		 rootView = inflater.inflate(R.layout.activity_map, container,
				false);
		 
		 MapsInitializer.initialize(getActivity());

	        mMapView = (MapView) rootView.findViewById(R.id.map);
	        mMapView.onCreate(mBundle);
	        setUpMapIfNeeded(rootView);

		return rootView;
    }
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = savedInstanceState;
    }

    private void setUpMapIfNeeded(View inflatedView) {
        if (mMap == null) {
            mMap = ((MapView) inflatedView.findViewById(R.id.map)).getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(11.0183, 76.9725)).title("Marker"));
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }
   
}
