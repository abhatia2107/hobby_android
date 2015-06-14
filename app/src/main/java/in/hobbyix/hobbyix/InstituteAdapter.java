package in.hobbyix.hobbyix;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yashdeep Sharma on 14-06-2015.
 */

public class InstituteAdapter extends ArrayAdapter<PostItems> {
    ArrayList<PostItems> ArrayListPost;
    int resource;
    Context context;
    LayoutInflater layoutInflater;
    public InstituteAdapter(Context context,int resource,ArrayList<PostItems> objects){
        super(context,resource,objects);

        ArrayListPost = objects;
        this.resource = resource;
        this.context=context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView == null){
            convertView = layoutInflater.inflate(resource,null);
            viewHolder = new ViewHolder();
            //viewHolder.book_now = (Button)convertView.findViewById(R.id.item_book_now_button);
            viewHolder.institute_name = (TextView)convertView.findViewById(R.id.item_NameTextView);
            viewHolder.institute_class_type = (TextView)convertView.findViewById(R.id.item_ClassTypeTextView);
            viewHolder.institute_timing = (TextView)convertView.findViewById(R.id.item_TimingsTextView);
            viewHolder.institute_price = (TextView)convertView.findViewById(R.id.item_FessTextView);
            viewHolder.institute_address = (TextView)convertView.findViewById(R.id.item_AddressTextView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.institute_name.setText(ArrayListPost.get(position).getName());
        viewHolder.institute_address.setText(ArrayListPost.get(position).getAddress());
        viewHolder.institute_price.setText(ArrayListPost.get(position).getFees());
        viewHolder.institute_class_type.setText(ArrayListPost.get(position).getClassType());
        viewHolder.institute_timing.setText(ArrayListPost.get(position).getTimings());
        //viewHolder.book_now.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View v) {


            //}
        //});
        return convertView;
    }

    static class ViewHolder{
        public TextView institute_name;
        public TextView institute_class_type;
        public TextView institute_address;
        public TextView institute_timing;
        public TextView institute_price;
        public Button book_now;

    }
}