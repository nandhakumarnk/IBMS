package com.rd.strivos.cobby;

/**
 * Created by COBURG DESIGN on 23-12-2015.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.MediaColumns;
import android.provider.MediaStore.Images.ImageColumns;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Cam_offline_PersonMeet extends Activity implements SensorEventListener{

    private Camera mCamera;
    private CameraPreview mPreview;
    private SensorManager sensorManager = null;
    private int orientation;
    private ExifInterface exif;
    private int deviceHeight;
    private Button ibRetake;
    private Button ibUse;
    private Button ibCapture;
    private FrameLayout flBtnContainer;
    private File sdRoot;
    private String dir;
    private String fileName;
    private ImageView rotatingImage;
    private int degrees = -1;
    private static final String TAG = "MFMSCam";
    private String IMEI;
    private String Model;
    String Remarks="",InitRemarks,address,cdt;
    Button start;
    EditText rem;
    String[] arr_text;
    String mins;
    String namestart;
    String name;
    int icon = R.drawable.camicon;
    LocationListener listener;
    LocationManager locationManager;
    Location currentLocation;
    StringBuilder result;
    double latitude_main,longitude_main;
    GPSTracker gps;
    Thread t;
    public FTPClient mFTPClient = null;
    String host;
    Data db;
    DBdata dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cam_offline);
        gps_check();
        //Getting setting values
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        IMEI = telephonyManager.getDeviceId();
        Model = android.os.Build.MODEL;
        Log.e("Phone Model", Model);
        host = "111.118.180.108";
        latitude_main=this.getIntent().getDoubleExtra("lat", latitude_main);
        longitude_main=this.getIntent().getDoubleExtra("lon", longitude_main);
        address=this.getIntent().getStringExtra("add");
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());
        cdt=currentDateandTime;

        db = new Data(getApplicationContext());
        rotatingImage = (ImageView) findViewById(R.id.cf_imageView1);
        ibUse = (Button) findViewById(R.id.cf_ibUse);
        ibCapture = (Button) findViewById(R.id.cf_ibCapture);
        flBtnContainer = (FrameLayout) findViewById(R.id.cf_flBtnContainer);
        // Getting the sensor service.
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Selecting the resolution of the Android device so we can create a
        // proportional preview
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        deviceHeight = display.getHeight();
        isExternalStoragePresent();
        // Add a listener to the Capture button
        ibCapture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCamera.takePicture(null, null, jpegCallback);
                takePicsPeriodically_offline(latitude_main, longitude_main);
            }
        });

        // Add a listener to the Use button
        ibUse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Everything is saved so we can quit the app.
                finish();
            }
        });

    }

    private void createCamera() {
        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Setting the right parameters in the camera
        Camera.Parameters params = mCamera.getParameters();
        params.setPictureSize(1280, 960);
        List<String> focusModes = params.getSupportedFocusModes();
        if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE))
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        params.setPictureFormat(PixelFormat.JPEG);
        params.setJpegQuality(75);
        mCamera.setParameters(params);

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.cf_camera_preview);
        // Calculating the width of the preview so it is proportional.
        float widthFloat = (float) (deviceHeight) * 4 / 3;
        int width = Math.round(widthFloat);

        // Resizing the LinearLayout so we can make a proportional preview. This
        // approach is not 100% perfect because on devices with a really small
        // screen the the image will still be distorted - there is place for
        // improvement.
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, deviceHeight);
        preview.setLayoutParams(layoutParams);

        // Adding the camera preview after the FrameLayout and before the button
        // as a separated element.
        preview.addView(mPreview, 0);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Test if there is a camera on the device and if the SD card is
        // mounted.
        if (!checkCameraHardware(this)) {
            Intent i = new Intent(this, NoCamera.class);
            startActivity(i);
            finish();
        }
        // Creating the camera
        createCamera();

        // Register this class as a listener for the accelerometer sensor
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // release the camera immediately on pause event
        releaseCamera();

        // removing the inserted view - so when we come back to the app we
        // won't have the views on top of each other.
        FrameLayout preview = (FrameLayout) findViewById(R.id.cf_camera_preview);
        preview.removeViewAt(0);
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release(); // release the camera for other applications
            mCamera = null;
        }
    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }
    private boolean checkSDCard() {
        boolean state = false;
        String sd = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(sd)) {
            state = true;
        }
        return state;
    }
    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            // attempt to get a Camera instance
            c = Camera.open();
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }
        // returns null if camera is unavailable
        return c;
    }
    private PictureCallback mPicture = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            // Replacing the button after a photho was taken.
            flBtnContainer.setVisibility(View.GONE);
            ibUse.setVisibility(View.VISIBLE);
            // File name of the image that we just took.
            fileName = "BE_"+cdt +".jpg";
            // Creating the directory where to save the image. Sadly in older
            // version of Android we can not get the Media catalog name
            File mkDir = new File(sdRoot, dir);
            mkDir.mkdirs();
            // Main file where to save the data that we recive from the camera
            File pictureFile = new File(sdRoot, dir + fileName);
            try {
                FileOutputStream purge = new FileOutputStream(pictureFile);
                purge.write(data);
                purge.close();
            } catch (FileNotFoundException e) {
                Log.d("DG_DEBUG", "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d("DG_DEBUG", "Error accessing file: " + e.getMessage());
            }
            // Adding Exif data for the orientation. For some strange reason the
            // ExifInterface class takes a string instead of a file.
            try {
                exif = new ExifInterface("/storage/sdcard0/" + dir + fileName);
                exif.setAttribute(ExifInterface.TAG_ORIENTATION, "" + orientation);
                exif.saveAttributes();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("hmmm",e.toString());
            }
        }
    };

    /**
     * Putting in place a listener so we can get the sensor data only when
     * something changes.
     */
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                RotateAnimation animation = null;
                if (event.values[0] < 4 && event.values[0] > -4) {
                    if (event.values[1] > 0 && orientation != ExifInterface.ORIENTATION_ROTATE_90) {
                        // UP
                        orientation = ExifInterface.ORIENTATION_ROTATE_90;
                        animation = getRotateAnimation(270);
                        degrees = 270;
                    } else if (event.values[1] < 0 && orientation != ExifInterface.ORIENTATION_ROTATE_270) {
                        // UP SIDE DOWN
                        orientation = ExifInterface.ORIENTATION_ROTATE_270;
                        animation = getRotateAnimation(90);
                        degrees = 90;
                    }
                } else if (event.values[1] < 4 && event.values[1] > -4) {
                    if (event.values[0] > 0 && orientation != ExifInterface.ORIENTATION_NORMAL) {
                        // LEFT
                        orientation = ExifInterface.ORIENTATION_NORMAL;
                        animation = getRotateAnimation(0);
                        degrees = 0;
                    } else if (event.values[0] < 0 && orientation != ExifInterface.ORIENTATION_ROTATE_180) {
                        // RIGHT
                        orientation = ExifInterface.ORIENTATION_ROTATE_180;
                        animation = getRotateAnimation(180);
                        degrees = 180;
                    }
                }
                if (animation != null) {
                    rotatingImage.startAnimation(animation);
                }
            }
        }
    }

    /**
     * Calculating the degrees needed to rotate the image imposed on the button
     * so it is always facing the user in the right direction
     *
     * @param toDegrees
     * @return
     */
    private RotateAnimation getRotateAnimation(float toDegrees) {
        float compensation = 0;

        if (Math.abs(degrees - toDegrees) > 180) {
            compensation = 360;
        }
        // When the device is being held on the left side (default position for
        // a camera) we need to add, not subtract from the toDegrees.
        if (toDegrees == 0) {
            compensation = -compensation;
        }
        // Creating the animation and the RELATIVE_TO_SELF means that he image
        // will rotate on it center instead of a corner.
        RotateAnimation animation = new RotateAnimation(degrees, toDegrees - compensation, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        // Adding the time needed to rotate the image
        animation.setDuration(250);
        // Set the animation to stop after reaching the desired position. With
        // out this it would return to the original state.
        animation.setFillAfter(true);

        return animation;
    }

    /**
     * STUFF THAT WE DON'T NEED BUT MUST BE HEAR FOR THE COMPILER TO BE HAPPY.
     */
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void showAlertDialog(final CharSequence message) {
        Cam_offline_PersonMeet.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        Cam_offline_PersonMeet.this);
                builder.setMessage(message)
                        .setCancelable(false)
                        .setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                    }
                                });
                builder.create().show();
            }
        });
    }





    public Dialog showProgressDialog(String title, String message) {
        final ProgressDialog dialog = ProgressDialog.show(this, title, message);
        return dialog;
    }
    /* Camera Call backs */
    ShutterCallback shutterCallback = new ShutterCallback() {
        @Override
        public void onShutter() {
            Log.d(TAG, "onShutter'd");
        }
    };

    /** Handles data for raw picture */
    PictureCallback rawCallback = new PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Log.d(TAG, "onPictureTaken - raw");
        }
    };
    /** Handles data for jpeg picture */
    PictureCallback jpegCallback = new PictureCallback() {
        @Override
        public void onPictureTaken(final byte[] data, Camera camera) {
            flBtnContainer.setVisibility(View.GONE);
            //ibRetake.setVisibility(View.VISIBLE);
            ibUse.setVisibility(View.VISIBLE);
            final Dialog dialog1 = showProgressDialog(null, "Please Wait...\n");
            try {
                t = new Thread(new Runnable() {

                    @Override
                    public void run() {

                        setErrorCallback(mErrorCallback);

                        Log.e("FileName", "BE_"+cdt +cdt );

                        Calendar c = Calendar.getInstance();
                        Long lDate = c.getTime().getTime();
                        FileOutputStream outStream = null;
                        try {
                            String RootDir = Environment.getExternalStorageDirectory()
                                    + File.separator + "ISD_BE/";

                            File file = new File(RootDir + "BE_"+cdt +".jpg");
                            outStream = new FileOutputStream(RootDir + "BE_"+cdt +".jpg");

                            outStream.write(data);
                            outStream.flush();
                            outStream.close();

                            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {

                                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
                            } else {

                                sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
                            }

                            Log.d(TAG, "onPictureTaken - wrote bytes: "
                                    + data.length);
                            NewProspectFragment.foto3="BE_"+cdt + ".jpg";
                            String path = "/storage/sdcard0/ISD_BE/" +"BE_"+cdt + ".jpg";
                            stopPreviewAndFreeCamera();
                            Log.i("success", "Done");


                        } catch (Exception e) {
                            Log.e(TAG, "Exception while writing image", e);
                            e.printStackTrace();
                        } finally {
                        }
                        dialog1.dismiss();
                    }
                });
                t.start();
            }
            catch(Exception ex){
                camera.stopPreview();
                //camera.setPreviewCallback(null);
                mCamera.release(); // written in documentation...
                mCamera = null;
                mCamera = Camera.open();

            }
        }
    };

    public final void setErrorCallback(ErrorCallback cb) {
        mErrorCallback = cb;
    }

    ErrorCallback mErrorCallback = new ErrorCallback() {
        @Override
        public void onError(int error, Camera camera) {
            Log.d("MiCam", "camera error detected");
            if (error == Camera.CAMERA_ERROR_SERVER_DIED) {
                Log.d("CameraDemo", "attempting to reinstantiate new camera");
                camera.stopPreview();
                //camera.setPreviewCallback(null);
                mCamera.release(); // written in documentation...
                mCamera = null;
                mCamera = Camera.open();

            }
        }
    };
    private void stopPreviewAndFreeCamera() {

        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    Uri saveMediaEntry(String imagePath, String displayName, String title,
                       String description, long dateTaken, int orientation, double lat,
                       double lon) {
        ContentValues v = new ContentValues();
        v.put(MediaColumns.TITLE, title);
        v.put(MediaColumns.DISPLAY_NAME, displayName);
        v.put(ImageColumns.DESCRIPTION, description);
        v.put(MediaColumns.DATE_ADDED, dateTaken);
        v.put(ImageColumns.DATE_TAKEN, dateTaken);
        v.put(MediaColumns.DATE_MODIFIED, dateTaken);
        v.put(MediaColumns.MIME_TYPE, "image/jpeg");
        v.put(ImageColumns.ORIENTATION, orientation);

        File f = new File(imagePath);
        File parent = f.getParentFile();
        String path = parent.toString().toLowerCase();
        String name = parent.getName().toLowerCase();
        v.put(Images.ImageColumns.BUCKET_ID, path.hashCode());
        v.put(Images.ImageColumns.BUCKET_DISPLAY_NAME, name);
        v.put(MediaColumns.SIZE, f.length());
        v.put(ImageColumns.LATITUDE, lat);
        v.put(ImageColumns.LONGITUDE, lon);
        f = null;

        v.put("_data", imagePath);
        ContentResolver c = getContentResolver();
        return c.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, v);
    }

    private void isExternalStoragePresent() {

        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        String RootDir = Environment.getExternalStorageDirectory()
                + File.separator + "ISD_BE";
        String state = Environment.getExternalStorageState();
        String path = Environment.getExternalStorageDirectory().getPath()
                .toString();
        Log.e("SD Path", path);
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
            File wallpaperDirectory = new File(RootDir);
            // have the object build the directory structure, if needed.
            wallpaperDirectory.mkdirs();
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
        if (!((mExternalStorageAvailable) && (mExternalStorageWriteable))) {
            Toast.makeText(getBaseContext(),
                    "SD card not present.Service Stopped", Toast.LENGTH_LONG)
                    .show();
        }
    }
    public void takePicsPeriodically_offline(final double lat, final double lon) {
    }
    public void insertJob(int Status, String rem, double lat, double lon,
                          String addr) {
        String sql_uid= null;


        dba = new DBdata(Cam_offline_PersonMeet.this);
        //int i = (int)
        dba.open();
        Cursor c = dba.login_get(1);
        startManagingCursor(c);
        sql_uid = c.getString(0);
        dba.close();
        String data = "'FOTO_Filename~" +"BE_"+cdt + ".jpg" + ";" + "LatLong~"
                + lat + "^" + lon + ";" + "Location~" + addr + ";IMEI~" + IMEI
                + ";";
        String datestamp = "',getdate(),'";
        String user = sql_uid;
        String sccode = "FOTO";
        String assignedto = sql_uid;
        String reportstatus = Integer.toString(Status);
        String informtech = "-";
        String informtechno = "0";
        String informcust = " ";
        String informcustno = "00";
        String remarks = "BE_"+cdt +".jpg";
        String flag = "NO";

        Log.i("Insert Job check",data + datestamp + user + sccode + assignedto + reportstatus
                + remarks);
        int d =(int) db.value_insert_tab_iformdata(data, datestamp, user, sccode,
                assignedto, reportstatus, informtech, informtechno,
                informcust, informcustno, remarks,flag);
        db.close();

        if (Status == 90) {
            Toast.makeText(getBaseContext(), "New entry recorded offline.",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "Record set for followup.",
                    Toast.LENGTH_LONG).show();
        }

        Log.i("Query", "Success SQlite Insert");
    }

    public void gps_check() {
        // Get Location Manager and check for GPS & Network location services
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
                ||  !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            // Build the alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Location Services Not Active");
            builder.setMessage("Please enable Location Services and GPS");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Show location settings when the user acknowledges the alert dialog
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            Dialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }
    }
    public String getPic() {
        SharedPreferences sp = getSharedPreferences("PICNAME", Context.MODE_PRIVATE);
        String s = sp.getString("PICNAME", "");
        return s;
    }

}

