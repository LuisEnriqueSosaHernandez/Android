package itver.donapps.models;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import itver.donapps.R;

public class ListDonorsAdapter extends RecyclerView.Adapter<ListDonorsAdapter.ListDonorsViewHolder> {

    private final Context context;
    private List<User> users;


    public ListDonorsAdapter(final Context context, List<User> users){
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ListDonorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_donors_item, parent , false);
        ListDonorsViewHolder viewHolder = new ListDonorsViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListDonorsViewHolder holder, int position) {
        holder.bindData(context, users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ListDonorsViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView profileImage;
        public ImageView callImage;
        public ImageView imageMessageDonor;
        public TextView nameDonor;
        public TextView cityDonor;
        public TextView phone;
        public TextView blood;

        public ListDonorsViewHolder(View itemView) {
            super(itemView);
            profileImage = (CircleImageView) itemView.findViewById(R.id.imageViewProfileFound);
            callImage = (ImageView) itemView.findViewById(R.id.imageCallDonor);
            nameDonor = (TextView) itemView.findViewById(R.id.txtNameProfileFound);
            cityDonor = (TextView) itemView.findViewById(R.id.txtCityProfileFound);
            phone = (TextView) itemView.findViewById(R.id.txtPhoneProfileFound);
            blood=(TextView) itemView.findViewById(R.id.txtBloodProfileFound);
            imageMessageDonor=(ImageView) itemView.findViewById(R.id.imageMessageDonor);
        }

        public void bindData(final Context context, final User user){
            nameDonor.setText("Nombre: " + user.getName()+" "+user.getLastName());
            cityDonor.setText("Ciudad: "+ Cities.getCity(user.getCity()));
            phone.setText("Telefono: " + user.getPhone());
            blood.setText("Tipo de sangre: "+user.getBloodGroup());
            callImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentPhone = new Intent(Intent.ACTION_DIAL,
                            Uri.parse("tel:"+user.getPhone()));
                    if(TextUtils.isEmpty(user.getPhone())){
                        Toast.makeText(context,"El usuario no proporcionó su número de teléfono",Toast.LENGTH_SHORT).show();
                    }else {
                        context.startActivity(intentPhone);
                    }
                }
            });
            imageMessageDonor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] TO = {user.getEmail()}; //aquí pon tu correo
                    String[] CC = {""};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_CC, CC);
// Esto podrás modificarlo si quieres, el asunto y el cuerpo del mensaje

                    try {
                        context.startActivity(Intent.createChooser(emailIntent, "Enviar"));

                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
                    }
                }
            });
 /*           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(user, getAdapterPosition());
                }
            });
            */

        }
    }

    /*public interface OnItemClickListener{
        void onItemClick(User user, int position);
    }

    */


}
