package tw.org.iii.a05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>>data;  //資料異動度 低ArrayList/高LinkedList
    private String[] from = {"title","mesg"};
    private int[] to ={R.id.item_title,R.id.item_mesg};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        initListView();
    }
    private void initListView(){
        data = new LinkedList<>();

        for(int i = 0 ; i<20 ; i++){
            HashMap<String,String> dd = new HashMap<>();
            int rand = (int)(Math.random()*49+1);
            dd.put(from[0],"Title"+rand); //結構是key_value,一般這種型態是使用put
            dd.put(from[1] ,"Content"+rand);
            data.add(dd);   //迴圈跑十圈，創造十個dd
        }
        adapter = new SimpleAdapter(
                this,data,
                R.layout.item_test1,from,to
                );
        listView.setAdapter(adapter);  //已有SimpleAdapter變數adapter直接將變數帶入即可
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringBuffer sb = new StringBuffer();
                sb.append("Title"+data.get(position).get("title")+"\n");
                sb.append("Mesg"+data.get(position).get("mesg")+"\n");
                sb.append("Detail"+data.get(position).get("detail")+"\n");
                displayDetail(sb.toString());
             }
        });
    }
        private void displayDetail(String mesg){
        new AlertDialog.Builder(this)
                .setMessage(mesg)
                .create()
                .show();
        }

    public void addItem(View view) {
        HashMap<String,String> dd = new HashMap<>();
        int rand = (int)(Math.random()*49+100);
        dd.put(from[0],"Title"+rand); //結構是key_value,一般這種型態是使用put
        dd.put(from[1] ,"Content:"+rand);
        dd.put("detail","Detail:"+rand);
        data.add(dd);   //迴圈跑十圈，創造十個dd
        adapter.notifyDataSetChanged();  //資料有異動，則發布及時通知給listView
    }
}

