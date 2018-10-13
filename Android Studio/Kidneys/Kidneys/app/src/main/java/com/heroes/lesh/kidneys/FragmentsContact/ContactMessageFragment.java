package com.heroes.lesh.kidneys.FragmentsContact;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heroes.lesh.kidneys.R;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactMessageFragment extends Fragment {
    //Variables globales
    private View view;
    @BindView(R.id.editTextToContact)
    EditText editTextToContact;
    @BindView(R.id.editTextSubjectContact)
    EditText editTextSubjectContact;
    @BindView(R.id.editTextMessageContact)
    EditText editTextMessageContact;
    @BindView(R.id.buttonSendContact)
    Button buttonSendContact;
    @BindString(R.string.correocontacto)
    String correocontacto;
    @BindString(R.string.correovacio)
    String correovacio;
    @BindString(R.string.enviarcorreo)
    String enviarcorreo;
    @BindString(R.string.errorcorreo)
    String errorcorreo;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    private Unbinder unbinder;
    private Intent emailIntent;
    private String subject;
    private String message;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    private TextView txtToast;
    private ImageView imgToast;
    @BindColor(R.color.colorAzul)
    int colorAzul;
    @BindColor(R.color.colorMarron)
    int colorMarron;
    @BindColor(R.color.colorRosado)
    int colorRosado;
    private SharedPreferences preferences;
    public ContactMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contact_message, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        inicializarToast();
        changeColors();
        editTextToContact.setText(correocontacto);
        editTextToContact.setInputType(InputType.TYPE_NULL);
        editTextSubjectContact.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        editTextMessageContact.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        buttonSendContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = editTextSubjectContact.getText().toString();
                message = editTextMessageContact.getText().toString();
                if (TextUtils.isEmpty(message)) {
                    changeToast(correovacio, ic_harmful);
                    toast.show();
                } else {
                    sendEmail(subject, message);
                }
            }
        });
        return view;
    }
    //Metodo para inicializar componentes
    private void inicializar(){
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
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

    //Metodo para cambiar los parametros del toast
    private void changeToast(String text, Drawable imgToastIcono) {
        txtToast.setText(text);
        imgToast.setImageDrawable(imgToastIcono);
    }

    //Metodo para hacer el intento de enviar el email
    protected void sendEmail(String subject, String message) {
        String[] TO = {correocontacto}; //aquí pon tu correo
        String[] CC = {""};
        emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
// Esto podrás modificarlo si quieres, el asunto y el cuerpo del mensaje
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, enviarcorreo));
            getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);

        } catch (android.content.ActivityNotFoundException ex) {
            changeToast(errorcorreo, ic_harmful);
        }
    }
    //Metodo para recuperar el color requerido
    private void changeColors(){
        switch (preferences.getInt("ColorApp", 1)){
            case 1:
                setColorsApp(colorAzul);
                break;
            case 2:
                setColorsApp(colorMarron);
                break;
            case 3:
                setColorsApp(colorRosado);
                break;
        }
    }
    //Metodo para cambiar los colores de la app
    private void setColorsApp(int color){
        buttonSendContact.setBackgroundColor(color);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
