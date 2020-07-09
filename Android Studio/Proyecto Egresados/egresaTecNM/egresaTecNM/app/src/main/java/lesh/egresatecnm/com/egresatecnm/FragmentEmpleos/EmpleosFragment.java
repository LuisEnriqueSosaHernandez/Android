package lesh.egresatecnm.com.egresatecnm.FragmentEmpleos;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import lesh.egresatecnm.com.egresatecnm.Adaptadores.MyAdapter;
import lesh.egresatecnm.com.egresatecnm.Interfaces.ListenerPag;
import lesh.egresatecnm.com.egresatecnm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmpleosFragment extends Fragment {
    private ListView ListaEmpleos;
    private List<String> Empleos;
    private ListenerPag callback;
    public EmpleosFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback=(ListenerPag) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString()+"algo salio mal");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_empleos, container, false);
        Inicializar(view);
        Empleos=new ArrayList<String>();
        Empleos.add("Coca");
        Empleos.add("Albaniloco");
        Empleos.add("Maestro");
        Empleos.add("Doctor");
        Empleos.add("Viene Viene");
        Empleos.add("Coca");
        Empleos.add("Albaniloco");
        Empleos.add("Maestro");
        Empleos.add("Doctor");
        Empleos.add("Viene Viene");
        Empleos.add("Coca");
        Empleos.add("Albaniloco");
        Empleos.add("Maestro");
        Empleos.add("Doctor");
        Empleos.add("Viene Viene");
        Empleos.add("Coca");
        Empleos.add("Albaniloco");
        Empleos.add("Maestro");
        Empleos.add("Doctor");
        Empleos.add("Viene Viene");
        Empleos.add("Coca");
        Empleos.add("Albaniloco");
        Empleos.add("Maestro");
        Empleos.add("Doctor");
        Empleos.add("Viene Viene");

       ListaEmpleos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               EnviaDatos(Empleos.get(position));
               return false;
           }
       });
        MyAdapter myAdapter=new MyAdapter(view.getContext(),R.layout.vista_empleos,Empleos);
        ListaEmpleos.setAdapter(myAdapter);
        // Inflate the layout forq this fragment
        return view;
    }
    private void EnviaDatos(String Empleo){
        callback.RecuperarDatos(Empleo);
    }
private void Inicializar(View view){
    ListaEmpleos=(ListView)view.findViewById(R.id.ListaEmpleos);
}
}
