package lesh.egresatecnm.com.fragmentos.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import lesh.egresatecnm.com.fragmentos.R;


public class DataFragment extends Fragment {

private EditText txtName;
    private Button btnSend;
    private DataListener callback;
    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
      callback=(DataListener) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString()+"algo salio mal");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_data,container,false);
        //Logica para capturar los elementos de la UI
        txtName=(EditText) view.findViewById(R.id.txtName);
        btnSend=(Button) view.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    sendText(txtName.getText().toString());
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
private void sendText(String text) {
callback.sendData(text);
}
public interface DataListener{
    void sendData(String text);
}
}
