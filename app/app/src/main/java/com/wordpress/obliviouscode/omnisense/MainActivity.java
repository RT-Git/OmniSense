package com.wordpress.obliviouscode.omnisense;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final List<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ListView lv = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,R.layout.list_item,list);
        lv.setAdapter(adapter);
        final EditText e = (EditText) findViewById(R.id.editText4);
        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String text = e.getText().toString();
              if(!text.isEmpty()){
                    list.add(text);
                    adapter.notifyDataSetChanged();
              }
            }
        });


        Button connect = (Button) findViewById(R.id.connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncAction().execute();
            }
        });

        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                adapter.notifyDataSetChanged();
            }});
    }

    private class AsyncAction extends AsyncTask<String, Void, String> {

        private Socket s;

        protected String doInBackground(String... args) {

            String test = "";
            try {
                s = new Socket(InetAddress.getByName("139.59.79.157"), 3301);
                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                do {
                    test = input.readLine();
                    list.add(test);
//                    return test;
                } while (!test.equals("END"));

//                ProgressDialog progressDialog = new ProgressDialog("kfsdf");
//                    if(!st.isEmpty())
//            OutputStream out = s.getOutputStream();
//            PrintWriter output = new PrintWriter(out);
//            output.println("Hello Android!");
                //read line(s)
                //Close connection
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }

            return test;
        }

        protected void onPostExecute(String result) {
            //resultis the data returned from doInbackground
//            t.setText(result);
            adapter.notifyDataSetChanged();
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
