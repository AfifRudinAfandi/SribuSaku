package com.aknela.sribusaku;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aknela.sribusaku.app.AppController;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment implements View.OnClickListener {

    ProgressDialog pDialog;
    ConnectivityManager conMgr;

    int success;

    TextView txt_saldo, hai;

    private String url = Server.URL + "saldo.php";
    private static final String TAG = LoginActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";

    public final static String TAG_NOMOR = "nomor";

    SharedPreferences sharedpreferences;

    String tag_json_obj = "json_obj_req";
    String nomor;

    public static final String my_shared_preferences = "my_shared_preferences";

    public BerandaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);



//        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshview);
//// set color schemes on refresh view
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorBiru,
//                R.color.colorPrimary);
//// implement refresh listener
//        swipeRefreshLayout .setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//
//            @Override
//            public void onRefresh() {
//
////                // call this method for repopulating recycler view with new data
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
//            }
//        });








        txt_saldo = (TextView) view.findViewById(R.id.txt_saldo);
        hai = (TextView) view.findViewById(R.id.hai);
        sharedpreferences = getActivity().getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
//        nomor = sharedpreferences.getString(TAG_NOMOR, null);
        nomor = getActivity().getIntent().getStringExtra(TAG_NOMOR);

//        hai.setText(nomor);

        String tipe = "transaksi";
//        String nomor = hai.getText().toString();

        Log.i("tes nomor",  nomor);

        checkSaldo(nomor, tipe);

        Button tombol = (Button)view.findViewById(R.id.btn_sedekah);
        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SliderBawahSedekah bottomSheet = new SliderBawahSedekah();
                bottomSheet.show(getFragmentManager(),"exampleBottomSheet");
            }
        });
        CardView cardlain = (CardView)view.findViewById(R.id.lainnya);
        cardlain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityLainnya.class);
                startActivity(intent);
            }
        });

        CardView cardPulsa = (CardView)view.findViewById(R.id.btn_pulsa);
        cardPulsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PulsaActivity.class);
                startActivity(intent);
            }
        });

        CardView cardZakat = (CardView)view.findViewById(R.id.btn_zakat);
        cardZakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ZakatActivity.class);
                startActivity(intent);
            }
        });

        Button button = (Button)view.findViewById(R.id.btn_topup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Topup.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    private void checkSaldo(final String nomor, final String tipe) {

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("respon", "Login Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {
                        String total = jObj.getString("total");
                        txt_saldo.setText("Rp" + total);

                        Log.i("Berhasil cek saldo!", jObj.toString());
                    } else {
                        Log.e("Error cek saldo!", jObj.toString());
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to saldo url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomor", nomor);
                params.put("tipe", tipe);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

}