package gachon.mpclass.pearth;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SetLocationActivity extends AppCompatActivity {

    GpsTracker gpsTracker;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    String area, areaTown;
    String SIGUN = "";
    String SIDO = "";
    TextView textView;
    ArrayAdapter<CharSequence> adspin1, adspin2;
    Button complete_btn;
    Button gps_btn;

    private FirebaseAuth firebaseAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v("<<>>>><<>>>","SetLocationActivity");
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        setContentView(R.layout.activity_set_location);

        if (!checkLocationServicesStatus()) {

            showDialogForLocationServiceSetting();
        } else {

            checkRunTimePermission();
        }

        getSupportActionBar().setTitle("?????? ?????? ?????????");
        Toolbar toolbar = findViewById(R.id.my_toolbar);


        gps_btn = (Button) findViewById(R.id.gps_btn);
        complete_btn = (Button) findViewById(R.id.complete_btn);
        final Spinner spin1 = (Spinner)findViewById(R.id.spinnerArea);
        final Spinner spin2 = (Spinner)findViewById(R.id.spinnerArea2);

        //adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_do, R.layout.spinneritem);
        adspin1 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do, R.layout.spinneritem);
        // adspin1.setDropDownViewResource(R.layout.spinneritem);
        adspin1.setDropDownViewResource(R.layout.spinneritem);
        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                area=adspin1.getItem(position).toString();
                if (adspin1.getItem(position).equals("???????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Seoul, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            areaTown=adspin2.getItem(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if (adspin1.getItem(position).equals("???????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Busan, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if (adspin1.getItem(position).equals("???????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Daegu, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            areaTown=adspin2.getItem(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if (adspin1.getItem(position).equals("???????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Gwangju, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("???????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Incheon, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("???????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Daejeon, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("???????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Ulsan, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("?????????????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Sejong, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("?????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Gyeonggi, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("?????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Gangwon, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Chungbuk,R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Chungnam, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Jeonbuk, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Jeonnam, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }

                else if (adspin1.getItem(position).equals("????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Gyeongbuk, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }

                else if (adspin1.getItem(position).equals("????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Gyeongnam, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }

                else if (adspin1.getItem(position).equals("?????????????????????")){
                    adspin2 = ArrayAdapter.createFromResource(SetLocationActivity.this, R.array.spinner_do_Jeju, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetLocationActivity.this, MapActivity.class);
                Bundle mybundle = new Bundle();

                String[] areas = area.split(" ");
                if(areas.length == 2){
                    SIGUN = area.split(" ")[0];
                    SIDO = area.split(" ")[1] + " " + areaTown;
                }else {
                    SIGUN = areas[0];
                    SIDO = areaTown;
                }

                mybundle.putString("SIGUN", SIGUN);
                mybundle.putString("SIDO", SIDO);
                mybundle.putString("Case", "List");


                Toast.makeText(SetLocationActivity.this, SIGUN + " " + SIDO, Toast.LENGTH_LONG).show();



                intent.putExtras(mybundle);
                startActivity(intent);
                finish();


            }
        });

        gps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gpsTracker = new GpsTracker(SetLocationActivity.this);
                Intent intent = new Intent(SetLocationActivity.this, MapActivity.class);
                Bundle mybundle = new Bundle();

                double latitude = gpsTracker.getLatitude();
                double longitude = gpsTracker.getLongitude();

                String address = getCurrentAddress(latitude, longitude);

                Log.d("address", address);
                String split[] = address.split(" ");
                area = split[2];
                int isShort = 0;
                if(!split[3].substring(split[3].length()-1).equals("???")) {
                    area = area + " " + split[3];

                    if(split.length > 4) {
                        if (split[4].substring(split[4].length() - 1).equals("???")) {
                            areaTown = split[4];
                        }
                    }
                    else isShort = 1;
                }
                else {
                    areaTown = split[3];
                }

                String[] areas = area.split(" ");
                if(areas.length == 2){
                    SIGUN = area.split(" ")[0];
                    if(isShort == 0) {
                        SIDO = area.split(" ")[1] + " " + areaTown;
                    }
                    else{
                        SIDO = area.split(" ")[1];
                    }
                }else {
                    SIGUN = areas[0];
                    SIDO = areaTown;
                }

                mybundle.putString("SIGUN", SIGUN);
                mybundle.putString("SIDO", SIDO);
                mybundle.putString("Case", "GPS");

                Toast.makeText(SetLocationActivity.this, SIGUN + " " + SIDO, Toast.LENGTH_LONG).show();

                intent.putExtras(mybundle);
                startActivity(intent);
                finish();


            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, @NonNull String[] permissions, @NonNull int[] grandResults) {

        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // ?????? ????????? PERMISSIONS_REQUEST_CODE ??????, ????????? ????????? ???????????? ??????????????????

            boolean check_result = true;


            // ?????? ???????????? ??????????????? ???????????????.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if (check_result) {

                //?????? ?????? ????????? ??? ??????
                ;
            } else {
                // ????????? ???????????? ????????? ?????? ????????? ??? ?????? ????????? ??????????????? ?????? ???????????????.2 ?????? ????????? ????????????.

                if (ActivityCompat.shouldShowRequestPermissionRationale(SetLocationActivity.this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(SetLocationActivity.this, REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(SetLocationActivity.this, "???????????? ?????????????????????. ?????? ?????? ???????????? ???????????? ??????????????????.", Toast.LENGTH_LONG).show();
                    finish();


                } else {

                    Toast.makeText(SetLocationActivity.this, "???????????? ?????????????????????. ??????(??? ??????)?????? ???????????? ???????????? ?????????. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission() {

        //????????? ????????? ??????
        // 1. ?????? ???????????? ????????? ????????? ???????????????.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(SetLocationActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(SetLocationActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. ?????? ???????????? ????????? ?????????
            // ( ??????????????? 6.0 ?????? ????????? ????????? ???????????? ???????????? ????????? ?????? ????????? ?????? ???????????????.)


            // 3.  ?????? ?????? ????????? ??? ??????


        } else {  //2. ????????? ????????? ????????? ?????? ????????? ????????? ????????? ???????????????. 2?????? ??????(3-1, 4-1)??? ????????????.

            // 3-1. ???????????? ????????? ????????? ??? ?????? ?????? ????????????
            if (ActivityCompat.shouldShowRequestPermissionRationale(SetLocationActivity.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. ????????? ???????????? ?????? ?????????????????? ???????????? ????????? ????????? ???????????? ????????? ????????????.
                Toast.makeText(SetLocationActivity.this, "??? ?????? ??????????????? ?????? ?????? ????????? ???????????????.", Toast.LENGTH_LONG).show();
                // 3-3. ??????????????? ????????? ????????? ?????????. ?????? ????????? onRequestPermissionResult?????? ???????????????.
                ActivityCompat.requestPermissions(SetLocationActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. ???????????? ????????? ????????? ??? ?????? ?????? ???????????? ????????? ????????? ?????? ?????????.
                // ?????? ????????? onRequestPermissionResult?????? ???????????????.
                ActivityCompat.requestPermissions(SetLocationActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }


    public String getCurrentAddress(double latitude, double longitude) {

        //????????????... GPS??? ????????? ??????
        Geocoder geocoder = new Geocoder(SetLocationActivity.this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //???????????? ??????
            Toast.makeText(SetLocationActivity.this, "???????????? ????????? ????????????", Toast.LENGTH_LONG).show();
            return "???????????? ????????? ????????????";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(SetLocationActivity.this, "????????? GPS ??????", Toast.LENGTH_LONG).show();
            return "????????? GPS ??????";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(SetLocationActivity.this, "?????? ?????????", Toast.LENGTH_LONG).show();
            return "?????? ?????????";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString() + "\n";

    }

    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SetLocationActivity.this);
        builder.setTitle("?????? ????????? ????????????");
        builder.setMessage("?????? ???????????? ???????????? ?????? ???????????? ???????????????.\n"
                + "?????? ????????? ???????????????????");
        builder.setCancelable(true);
        builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //???????????? GPS ?????? ???????????? ??????
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS ????????? ?????????");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }




    //???????????? ?????? ???????????? ?????? ??????
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //????????? ?????????
    private void hideActionBar () {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_record) {
            Intent homeIntent = new Intent(this, RecordActivity.class);
            startActivity(homeIntent);
        }
        if (id == R.id.action_analysis) {
            Intent settingIntent = new Intent(this, Analysis.class);
            startActivity(settingIntent);
        }
        if (id == R.id.action_share) {
            Intent shareIntent = new Intent(this, Shareboard.class);
            startActivity(shareIntent);
        }
        if (id == R.id.action_plant) {
            Intent plantIntent = new Intent(this, GrowingPlantActivity.class);
            startActivity(plantIntent);
        }
        if(id==R.id.action_checklist)
        {
            Intent intent=new Intent(this,CheckListActivity.class);
            String uid = user.getUid();
            Log.v("uid: ",uid);
          intent.putExtra("uid",uid);
            startActivity(intent);
        }
        if(id==R.id.action_UserProfile)
        {
            Intent intent=new Intent(this,UserProfileActivity.class);
            String uid = user.getUid();
            Log.v("uid: ",uid);
            intent.putExtra("uid",uid);
            startActivity(intent);
        }
        if(id==R.id.action_post){
            Intent PostIntent = new Intent(this, Userpost.class);
            startActivity(PostIntent);
        }
        if(id==R.id.action_store) {
            Intent intent = new Intent(this, SetLocationActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_back) {
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}


