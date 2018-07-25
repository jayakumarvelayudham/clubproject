package customer.mmpl.tcs.com.clubproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import customer.mmpl.tcs.com.clubproject.R;

/**
 * Created by BeAsT on 20-Jul-18.
 */

public class GalleryFragmentAdapter extends RecyclerView.Adapter<GalleryFragmentAdapter.ViewHolder>{

    private static final String TAG = "GalleryFragmentAdapter";
    private Context context;
    private String[] items;

    public GalleryFragmentAdapter(Context context,String[] items) {
        this.context = context;
        this.items = items;
    }

    public void Update(String[] items){
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_gallery_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        String s = items.get(position);
        final Uri uri = Uri.parse(items[position]);
        Picasso.with(context).load(uri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.images);
        }
    }
}
