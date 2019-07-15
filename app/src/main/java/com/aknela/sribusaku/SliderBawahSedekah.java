package com.aknela.sribusaku;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class SliderBawahSedekah extends BottomSheetDialogFragment {

    int success;
    ConnectivityManager conMgr;
    ProgressDialog pDialog;
    TextView test;
    View close;
    Button btn_lanjutkan;

    private String url = Server.URL + "sedekah.php";

    private static final String TAG = Topup.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";
    String nomor;
    SharedPreferences sharedpreferences;
    public static final String TAG_NOMOR = "nomor";
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.slider_bawah_sedekah, container, false);

            btn_lanjutkan = v.findViewById(R.id.btn_sedekah);
            test = v.findViewById(R.id.test);


            sharedpreferences = getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context. MODE_PRIVATE);
            nomor = sharedpreferences.getString(TAG_NOMOR, null);
            test.setText(nomor);

            conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            {
                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection",
                            Toast.LENGTH_LONG).show();
                }
            }

            btn_lanjutkan.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    // TODO Auto-generated method stub
                    String nominal = "1000";
                    String nomor = test.getText().toString();



                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        sedekah (nominal, nomor);
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            return v;
        }

        private void sedekah(final String nominal, final String nomor) {
            context = this.getActivity();
            pDialog = new ProgressDialog(context);
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



                            Log.e("Successfully!", jObj.toString());
                            Toast.makeText(getActivity().getApplicationContext(),
                                    jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                            dismiss();


                        } else {
                            Toast.makeText(getActivity().getApplicationContext(),
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
                    Toast.makeText(getActivity().getApplicationContext(),
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
    }
