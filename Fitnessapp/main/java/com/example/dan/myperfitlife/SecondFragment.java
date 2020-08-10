package com.example.dan.myperfitlife;

import android.app.Fragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class SecondFragment extends Fragment {

    View myView;
    ArrayAdapter<String> adapter;
    EditText editText;
    ArrayList<String> itemList;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_task, container, false);
        String[] items={"10 miles","2 miles","4 miles"};
        itemList=new ArrayList<String>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(getActivity(),R.layout.list_item,R.id.txtview,itemList);
        ListView listV=(ListView)myView.findViewById(R.id.list);
        listV.setAdapter(adapter);
        editText=(EditText)myView.findViewById(R.id.txtInput);
        Button btAdd=(Button)myView.findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem=editText.getText().toString();
                // add new item to arraylist
                itemList.add(newItem);
                // notify listview of data changed
                sendNotification();
                adapter.notifyDataSetChanged();
            }

        });
        return myView;
    }
    public void sendNotification() {

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getActivity(), "123")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("SUCCESS!")   //this is the title of notification
                        .setColor(101)
                        .setContentText("This workout was added to the list");   //this is the message showed in notification
        Intent intent = new Intent(getActivity(), SecondFragment.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        // Add as notification
        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

}
