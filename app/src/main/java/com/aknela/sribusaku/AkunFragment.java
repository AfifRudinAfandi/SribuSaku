package com.aknela.sribusaku;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment {


    public AkunFragment() {
        // Required empty public constructor
    }

    LinearLayout btn_logout,btn_syarat,btn_privasi;
    TextView txt_username, btn_isi;
    String nomor;
    SharedPreferences sharedpreferences;

    public static final String TAG_NOMOR = "nomor";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_akun, container, false);

        txt_username = view.findViewById(R.id.txt_username);
        btn_logout = view.findViewById(R.id.btn_logout);
        btn_isi = view.findViewById(R.id.btn_isisaldo);
        btn_syarat = view.findViewById(R.id.syarat);
        btn_privasi = view.findViewById(R.id.privasi);

        sharedpreferences = getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        nomor = getActivity().getIntent().getStringExtra(TAG_NOMOR);

        txt_username.setText("" + nomor);


        btn_isi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Topup.class);
                startActivity(intent);
            }
        });
        btn_syarat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SyaratKetentuan.class);
                startActivity(intent);
            }
        });
        btn_privasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KebijakanPrivasi.class);
                startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // update login session ke FALSE dan mengosongkan nilai id dan username
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(LoginActivity.session_status, false);
                editor.putString(TAG_NOMOR, null);
                editor.commit();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

}
