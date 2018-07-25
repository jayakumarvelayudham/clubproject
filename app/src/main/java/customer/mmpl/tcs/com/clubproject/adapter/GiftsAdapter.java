package customer.mmpl.tcs.com.clubproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import customer.mmpl.tcs.com.clubproject.R;
import customer.mmpl.tcs.com.clubproject.activity.Personal_Details;
import customer.mmpl.tcs.com.clubproject.modul.Movies;

/**
 * Created by BeAsT on 23-Mar-18.
 */

public class GiftsAdapter extends RecyclerView.Adapter<GiftsAdapter.MyViewHolder>{

    private static final String TAG = "StoreAdapter";
    private Context context;
    private List<Movies> movieList;
    public static SendValue msendValue;

    public GiftsAdapter(Context context, List<Movies> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public void Function_0(SendValue sendValue){
        msendValue = sendValue;
    }

    public static void Function_1(SendValue sendValue){
        msendValue = sendValue;
    }

    @Override
    public GiftsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gift_item_row, parent, false);

        return new GiftsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GiftsAdapter.MyViewHolder holder, int position) {
        final Movies movies = movieList.get(position);
        holder.name.setText(movies.getTitle());
        Picasso.with(context).load(movies.getImage()).into(holder.thumbnail);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = movies.getTitle();
                Log.e(TAG, "onClickkkkkkkkkkkkkkkkkkkkkkkkkkkk: " + a );
//                msendValue.method(a);
                Intent intent = new Intent(context,Personal_Details.class);
                intent.putExtra("IMAGE",movies.getImage());
                intent.putExtra("NAME",movies.getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price;
        public ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title1);
            thumbnail = itemView.findViewById(R.id.thumbnail1);
        }
    }

    public interface SendValue{
        void method(String s);
    }
}
