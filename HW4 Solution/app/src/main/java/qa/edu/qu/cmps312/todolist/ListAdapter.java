package qa.edu.qu.cmps312.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


class ListAdapter extends ArrayAdapter<toDo> {

    private List<toDo> objects = new ArrayList();
    private LayoutInflater inflater;

    ListAdapter(Context context, int resource, List<toDo> objects) {
        super(context, resource, objects);
        this.objects = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if( convertView == null ){
            //We must create a View:
            convertView = inflater.inflate(R.layout.each_row, parent, false);
        }

         TextView title = (TextView) convertView.findViewById(R.id.titleView);
        CheckBox completed = (CheckBox) convertView.findViewById(R.id.completeCheck);
        final CheckBox selected = (CheckBox) convertView.findViewById(R.id.selectBox);

        TextView date = (TextView) convertView.findViewById(R.id.dateView);
        TextView time = (TextView) convertView.findViewById(R.id.timeView);
        TextView priority = (TextView) convertView.findViewById(R.id.priorityView);

        title.setText(objects.get(position).getTitle());

        if(objects.get(position).getPriority() == 0)
            priority.setText("LOW");
        else if (objects.get(position).getPriority() ==1)
            priority.setText("MEDIUM");
        else if (objects.get(position).getPriority() ==2){
        priority.setText("HIGH");}

        date.setText(objects.get(position).getDate());
        time.setText(objects.get(position).getTime());

        final View tempConvert = convertView;

        completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked == true){
                   tempConvert.setBackgroundColor(Color.parseColor("#7F7F7F"));
               }else{
                   tempConvert.setBackgroundColor(Color.parseColor("#FFFFFF"));
               }
            }
        });


        selected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    new AlertDialog.Builder(getContext())
                            .setMessage("Do you want to delete this to do item?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    objects.remove(position);
                                    notifyDataSetInvalidated();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    selected.setChecked(false);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else{

                }
            }
        });


        if(objects.get(position).getDone() == true){
            convertView.setBackgroundColor(Color.parseColor("#7F7F7F"));
            completed.setChecked(true);
        } else{
            completed.setChecked(false);
            convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        return convertView;

    }







}
