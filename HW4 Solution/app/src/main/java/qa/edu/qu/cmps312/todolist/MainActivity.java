package qa.edu.qu.cmps312.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends Activity {
    private Context context = null;
    ArrayList<toDo> toDos = null;
    ListView listview = null;
    ListAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       toDos = new ArrayList<toDo>();


         listview = (ListView)findViewById(R.id.todosList);


         toDos = createArray();

        context = getApplicationContext();

        adapter = new ListAdapter(this, R.layout.each_row, toDos);
        listview.setAdapter(adapter);
        View footerView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer, null, false);

        Button addItem = (Button) footerView.findViewById(R.id.newToDo);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddItem.class);
                startActivityForResult(intent,1);
            }
        });

        listview.addFooterView(footerView);



    }

    private ArrayList<toDo> createArray() {

        for(int i=0;i<2;i++){
        String date = "2016,10,5";
        String time = "10:00 PM";
        toDo item = new toDo("EXAMPLE", 1,date,time,false);
        toDos.add(item);}
        return toDos;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String title = data.getStringExtra("title");
                String date = data.getStringExtra("date");
                String time = data.getStringExtra("time");
                Boolean done = data.getBooleanExtra("status",false);
                Integer priority = data.getIntExtra("priority",0);

                toDo newItem = new toDo(title,priority,date,time,done);
                toDos.add(newItem);
                adapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult




}
