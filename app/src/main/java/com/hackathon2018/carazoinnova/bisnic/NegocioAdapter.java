package com.hackathon2018.carazoinnova.bisnic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NegocioAdapter extends ArrayAdapter<Negocios> {

    private Context mContext;
    private List<Negocios> moviesList;
    private Bitmap mIcon_val;

    public NegocioAdapter(@NonNull Context context, ArrayList<Negocios> list) {
        super(context, 0 , list);
        mContext = context;
        moviesList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.activity_list_item,parent,false);

        Negocios currentMovie = moviesList.get(position);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        ImageView image = (ImageView)listItem.findViewById(R.id.listview_image);
        URL newurl = null;
        try {
            newurl = new URL(currentMovie.getUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        image.setImageBitmap(mIcon_val);

        TextView name = (TextView) listItem.findViewById(R.id.txtTituloNegocio);
        name.setText(currentMovie.getName());

        TextView descripcion = (TextView) listItem.findViewById(R.id.tv_descripcion);
        descripcion.setText(currentMovie.getDescripcion());

        return listItem;
    }


}


