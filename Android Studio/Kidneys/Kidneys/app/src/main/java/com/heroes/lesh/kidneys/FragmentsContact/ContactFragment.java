package com.heroes.lesh.kidneys.FragmentsContact;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.R;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {
    //Variables globales
    private ListenerPag callback;
    private View view;
    @BindView(R.id.floatingMessageContact)
    FloatingActionButton floatingMessageContact;
    @BindView(R.id.textViewContact)
    TextView textViewContact;
    @BindDrawable(R.mipmap.ic_contacto)
    Drawable ic_contacto;
    @BindString(R.string.redactarcorreo)
    String redactarcorreo;
    @BindString(R.string.textcontacto)
    String textcontacto;
    private Unbinder unbinder;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            //Inicializacion del callback de la interfaz
            callback = (ListenerPag) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contact, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializarToast();
        setTexto();
        floatingMessageContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment(8);
            }
        });
        floatingMessageContact.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(redactarcorreo, ic_contacto);
                toast.show();
                return false;
            }
        });
        return view;
    }

    //Metodo para cambiar el texto
    private void setTexto() {
        textViewContact.setText(Html.fromHtml(textcontacto));
    }

    private void sendFragment(int num) {
        callback.openFragment(num);
    }

    //Metodo para inicializar el toast
    private void inicializarToast() {
        layoutInflater = getActivity().getLayoutInflater();
        viewToast = layoutInflater.inflate(R.layout.custom_toast, null);
        txtToast = viewToast.findViewById(R.id.txtToast);
        imgToast = viewToast.findViewById(R.id.imgToast);
        toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(viewToast);
    }

    private void changeToast(String text, Drawable imgToastIcono) {
        txtToast.setText(text);
        imgToast.setImageDrawable(imgToastIcono);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
