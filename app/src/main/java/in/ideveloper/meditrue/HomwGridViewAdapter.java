package in.ideveloper.meditrue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pardeep on 16-10-2016.
 */
public class HomwGridViewAdapter extends BaseAdapter {


    ArrayList<HomeGridContent> arrayList=new ArrayList<HomeGridContent>();

    public HomwGridViewAdapter() {
        arrayList.add(new HomeGridContent("Add Medicine",R.drawable.plusplus));
        arrayList.add(new HomeGridContent("Medicine History",R.drawable.history));
        arrayList.add(new HomeGridContent("Upload",R.drawable.upload));
        arrayList.add(new HomeGridContent("  Search \nMedicines",R.drawable.pills));
        arrayList.add(new HomeGridContent("Update Medicines",R.drawable.pills));
        arrayList.add(new HomeGridContent("Download",R.drawable.downloadfiles));
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.grid_inner_view, parent, false);

        ImageView imageView=(ImageView)view.findViewById(R.id.imageview_innerView);
        imageView.setImageResource(arrayList.get(position).getImageRes());
        TextView textView=(TextView)view.findViewById(R.id.textview_innerView);
        textView.setText(arrayList.get(position).getText());
        return view;
    }
}
