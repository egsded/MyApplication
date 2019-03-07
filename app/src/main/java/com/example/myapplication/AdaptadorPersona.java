package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdaptadorPersona extends RecyclerView.Adapter<AdaptadorPersona.PersonaviewHolder> implements View.OnClickListener{
    private List<Persona> lp;
    private Context context;
    private View.OnClickListener eventoholderclick;
    Integer po;
    public AdaptadorPersona(List<Persona> lp, Context context) {
        this.lp = lp;
        this.context = context;
    }
    @NonNull
    @Override
    public PersonaviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_view, viewGroup, false);
        v.setOnClickListener(this);
        return new PersonaviewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull PersonaviewHolder personaviewHolder, final int i) {
        po=i;
        final Persona p = lp.get(i);
        personaviewHolder.iv1.setImageDrawable(context.getResources().getDrawable(R.drawable.img2));
        personaviewHolder.txtnombre.setText(lp.get(i).getNombre());
        personaviewHolder.txtapellido.setText(lp.get(i).getApellido());
        personaviewHolder.txtedad.setText(lp.get(i).getEdad().toString());
        personaviewHolder.txttelefono.setText(lp.get(i).getTelefono());
        personaviewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), lp.get(i).getTelefono(), Toast.LENGTH_SHORT).show();
                /*Intent t = new Intent(Intent.ACTION_CALL);
                t.setData(Uri.parse("tel:"+ p.getTelefono()));
                if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    return;
                }
                v.getContext().startActivity(t);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return lp.size();
    }

    @Override
    public void onClick(View v) {
        if (eventoholderclick !=null){
            eventoholderclick.onClick(v);
        }
    }

    class PersonaviewHolder extends RecyclerView.ViewHolder{
        ImageView iv1;
        TextView txtnombre, txtapellido, txtedad, txttelefono;
        public PersonaviewHolder(@NonNull View itemView) {
            super(itemView);
            iv1 = itemView.findViewById(R.id.iv1);
            txtnombre = itemView.findViewById(R.id.txtNombre);
            txtapellido = itemView.findViewById(R.id.txtApellido);
            txtedad = itemView.findViewById(R.id.txtEdad);
            txttelefono = itemView.findViewById(R.id.txtTelefono);
        }
    }
}
