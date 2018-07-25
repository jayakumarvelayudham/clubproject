package customer.mmpl.tcs.com.clubproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import customer.mmpl.tcs.com.clubproject.R;
import customer.mmpl.tcs.com.clubproject.modul.Alluser_Module;



public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {

    private static final String TAG = "StoreAdapter";
    private Context context;
    private List<Alluser_Module> alluser_modules;
    public PassMethodInterface passMethodInterface;

    public StoreAdapter(Context context, List<Alluser_Module> alluser_modules) {
        this.context = context;
        this.alluser_modules = alluser_modules;
//        this.passMethodInterface = ((PassMethodInterface) context);
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Alluser_Module alluser_module = alluser_modules.get(position);
        holder.person_name.setText(alluser_module.getF_name());
//        holder.person_age.setText(alluser_module.g());
        Picasso.with(context).load(alluser_module.getImage_url()).into(holder.person_photo);
//        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(context,Personal_Details.class);
//                intent.putExtra("IMAGE",movies.getImage());
//                intent.putExtra("NAME",movies.getTitle());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return alluser_modules.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView person_name, person_age;
        public ImageView person_photo;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            person_name = itemView.findViewById(R.id.person_name);
            person_age = itemView.findViewById(R.id.person_age);
            person_photo = itemView.findViewById(R.id.person_photo);
        }

        @Override
        public void onClick(View v) {
            Alluser_Module alluser_module = alluser_modules.get(getAdapterPosition());
            passMethodInterface.Method_Interface(alluser_module.getUser_id());
        }
    }

    public interface PassMethodInterface{
        void Method_Interface(String id);
    }

    public void aMethod(PassMethodInterface passMethodInterface){
        this.passMethodInterface = passMethodInterface;
    }


}
