package com.aknela.sribusaku;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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

public class PulsaActivity extends AppCompatActivity {


    FrameLayout btn_kembali;
    EditText et_nomorpulsa;
    LinearLayout btn10,btn20,btn25,btn50,btn100,btn150,btn200,btn250,btn500,btn1jt;
    int success;
    ConnectivityManager conMgr;
    ProgressDialog pDialog;
    TextView test;

    private String url = Server.URL + "pulsa.php";

    private static final String TAG = PulsaActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";
    String nomor;
    SharedPreferences sharedpreferences;
    public static final String TAG_NOMOR = "nomor";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulsa);

            btn_kembali = findViewById(R.id.btn_kembali);
            test = findViewById(R.id.test);
            btn10 = findViewById(R.id.btn_sepuluh);
            btn20 = findViewById(R.id.btn_duapuluh);
            btn25 = findViewById(R.id.btn_dualima);
            btn50 = findViewById(R.id.btn_limapuluh);
            btn100 = findViewById(R.id.btn_seratus);
            btn150 = findViewById(R.id.btn_seratuslimapuluh);
            btn200 = findViewById(R.id.btn_duaratus);
            btn250 = findViewById(R.id.btn_duaratuslimapuluh);
            btn500 = findViewById(R.id.btn_limaratus);
            btn1jt = findViewById(R.id.btn_satujuta);
            et_nomorpulsa = findViewById(R.id.et_nomorpulsa);

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

            sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context. MODE_PRIVATE);
            nomor = sharedpreferences.getString(TAG_NOMOR, null);
            test.setText(nomor);

            conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            {
                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection",
                            Toast.LENGTH_LONG).show();
                }
            }

            btn10.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    String nominal = "10000";
                    String nomor = test.getText().toString();
                    String nomorhp = et_nomorpulsa.getText().toString();
                    Log.i("test", nomorhp);
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        pulsa (nominal, nomor, nomorhp);

                    } else {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        btn20.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nominal = "20000";
                String nomor = test.getText().toString();
                String nomorhp = et_nomorpulsa.getText().toString();

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    pulsa (nominal, nomor, nomorhp);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn25.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nominal = "25000";
                String nomor = test.getText().toString();
                String nomorhp = et_nomorpulsa.getText().toString();

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    pulsa (nominal, nomor, nomorhp);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn50.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nominal = "50000";
                String nomor = test.getText().toString();
                String nomorhp = et_nomorpulsa.getText().toString();


                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    pulsa (nominal, nomor, nomorhp);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn100.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nominal = "100000";
                String nomor = test.getText().toString();
                String nomorhp = et_nomorpulsa.getText().toString();


                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    pulsa (nominal, nomor, nomorhp);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn150.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nominal = "150000";
                String nomor = test.getText().toString();
                String nomorhp = et_nomorpulsa.getText().toString();


                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    pulsa (nominal, nomor, nomorhp);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn200.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nominal = "200000";
                String nomor = test.getText().toString();
                String nomorhp = et_nomorpulsa.getText().toString();


                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    pulsa (nominal, nomor, nomorhp);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn250.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nominal = "250000";
                String nomor = test.getText().toString();
                String nomorhp = et_nomorpulsa.getText().toString();


                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    pulsa (nominal, nomor, nomorhp);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn500.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nominal = "500000";
                String nomor = test.getText().toString();
                String nomorhp = et_nomorpulsa.getText().toString();


                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    pulsa (nominal, nomor, nomorhp);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn1jt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nominal = "1000000";
                String nomor = test.getText().toString();
                String nomorhp = et_nomorpulsa.getText().toString();


                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    pulsa (nominal, nomor, nomorhp);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        }

        private void pulsa(final String nominal, final String nomor, final String nomorhp) {
            pDialog = new ProgressDialog(this);
            pDialog.setCancelable(false);
            pDialog.setMessage("Memproses ..");
            showDialog();

            StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.e(TAG, "Response: "+ response.toString());
                    hideDialog();

                    try {
                        JSONObject jObj = new JSONObject(response);
                        success = jObj.getInt(TAG_SUCCESS);

                        // Check for error node in json
                        if (success == 1) {




                            Log.e("Successfully!", jObj.toString());

                            Toast.makeText(getApplicationContext(),
                                    jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

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
                    params.put("nomorhp", nomorhp);
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
    }
