package com.aknela.sribusaku;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class FAQFragment extends Fragment implements View.OnClickListener {


    public FAQFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_faq, container, false);

        final LinearLayout btn = (LinearLayout) view.findViewById(R.id.btn_faq_beli);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQFragment faqFragment = new FAQFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framebeli,new FaqBeliFragment());
                fragmentTransaction.commit();
            }
        });

        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.btn_back);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQFragment faqFragment = new FAQFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framebeli,new BlankFragment());
                fragmentTransaction.commit();
            }
        });

        LinearLayout btn2 = (LinearLayout) view.findViewById(R.id.btn_faq_bayar);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQFragment faqFragment = new FAQFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framebayar,new FaqBayar());
                fragmentTransaction.commit();
            }
        });

        FrameLayout frameLayout2 = (FrameLayout) view.findViewById(R.id.btn_back2);
        frameLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQFragment faqFragment = new FAQFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framebayar,new BlankFragment());
                fragmentTransaction.commit();
            }
        });
//
//        LinearLayout btn3 = (LinearLayout) view.findViewById(R.id.btn_faq_zakat);
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FAQFragment faqFragment = new FAQFragment();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.framezakat,new FaqZakat());
//                fragmentTransaction.commit();
//            }
//        });
//
//        FrameLayout frameLayout3 = (FrameLayout) view.findViewById(R.id.btn_back3);
//        frameLayout3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FAQFragment faqFragment = new FAQFragment();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.framezakat,new BlankFragment());
//                fragmentTransaction.commit();
//            }
//        });
//
//        LinearLayout btn4 = (LinearLayout) view.findViewById(R.id.btn_faq_topup);
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FAQFragment faqFragment = new FAQFragment();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frametopup,new FaqTopup());
//                fragmentTransaction.commit();
//            }
//        });
//
//        FrameLayout frameLayout4 = (FrameLayout) view.findViewById(R.id.btn_back4);
//        frameLayout4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FAQFragment faqFragment = new FAQFragment();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frametopup,new BlankFragment());
//                fragmentTransaction.commit();
//            }
//        });
        return view;
    }
    @Override
    public void onClick(View v) {
    }
}
