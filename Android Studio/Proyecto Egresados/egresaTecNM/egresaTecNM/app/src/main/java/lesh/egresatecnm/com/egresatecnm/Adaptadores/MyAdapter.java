package lesh.egresatecnm.com.egresatecnm.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import lesh.egresatecnm.com.egresatecnm.R;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<String> Empleos;
    public MyAdapter(Context context, int layout, List<String>Empleos){
        this.context=context;
        this.layout=layout;
        this.Empleos=Empleos;

    }

    @Override
    public int getCount() {

        return this.Empleos.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.Empleos.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ViewHolderPattern
        ViewHolder holder;
        if(convertView==null){
            //Inflamos la vista que nos ha llegado con nuestro layout personalizado
            LayoutInflater layoutInflater=LayoutInflater.from(this.context);
            convertView=layoutInflater.inflate(this.layout,null);
            holder=new ViewHolder();
            //Referenciamos el evento a modificar y lo rellenamos
            holder.txtEmpleo=(TextView)convertView.findViewById(R.id.txtEmpleo);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        //Traemos el valor actual dependiente de la posicion
        String currentName=Empleos.get(position);
        holder.txtEmpleo.setText(currentName);
        //devolvemos inflados y modificados los datos lel xD jejjej Baia Baia
        return convertView;
    }
    static class ViewHolder {
        private TextView txtEmpleo;
    }
}
