package com.aknela.sribusaku;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aknela.sribusaku.app.AppController;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Topup extends AppCompatActivity {

    int success;
    ConnectivityManager conMgr;
    ProgressDialog pDialog;
    EditText et_topup;
    TextView test,jumlahtf;
    Button btn_depo;
    FrameLayout info_topup;

    private String url = Server.URL + "topup.php";

    private static final String TAG = Topup.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";
    String nomor;
    SharedPreferences sharedpreferences;
    public static final String TAG_NOMOR = "nomor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);

        jumlahtf = (TextView) findViewById(R.id.jumlahtf);
        test = (TextView) findViewById(R.id.test);
        info_topup = (FrameLayout) findViewById(R.id.info_topup);
        info_topup.setVisibility(View.INVISIBLE);



        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, MODE_PRIVATE);
        nomor = sharedpreferences.getString(TAG_NOMOR, null);
        test.setText(nomor);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.btn_kembali_lainnya);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

            conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            {
                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection",
                            Toast.LENGTH_LONG).show();
                }
            }

            btn_depo = (Button) findViewById(R.id.btn_depo);
            et_topup = (EditText) findViewById(R.id.et_topup);


            btn_depo.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    String nominal = et_topup.getText().toString();
                    String nomor = test.getText().toString();


                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        checkTopup (nominal, nomor);

                    } else {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

        private void checkTopup(final String nominal, final String nomor) {
            pDialog = new ProgressDialog(this);
            pDialog.setCancelable(false);
            pDialog.setMessage("Memproses ..");
            showDialog();

            StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.e(TAG, "Register Response: "+ response.toString());
                    hideDialog();

                    try {
                        JSONObject jObj = new JSONObject(response);
                        success = jObj.getInt(TAG_SUCCESS);

                        // Check for error node in json
                        if (success == 1) {

                            jumlahtf.setText("Jumlah Yang Harus Dibayar : Rp" + nominal);
                            info_topup.setVisibility(View.VISIBLE);


//                            Log.e("Successfully Register!", jObj.toString());

                            Toast.makeText(getApplicationContext(),
                                    jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                            et_topup.setText("");

                        } else {
                            Toast.makeText(getApplicationContext(),
                                    jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        }
                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, " Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();

                    hideDialog();

                }
            }){


                @Override
                protected Map<String, String> getParams() {
                    // Posting parameters to login url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("nominal", nominal);
                    params.put("nomor", nomor);

                    return params;
                }

            };

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
        }

        private void showDialog() {
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hideDialog() {
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
//
//        @Override
//        public void onBackPressed() {
//            intent = new Intent(RegisterActivity.this, LoginActivity.class);
//            finish();
//            startActivity(intent);
//    }
}
