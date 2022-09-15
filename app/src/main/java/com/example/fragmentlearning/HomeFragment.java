package com.example.fragmentlearning;

import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {
    Button testButton;
    ListView localAudioList;
    File file;
    File[] list;
    List<String> myList;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        testButton = view.findViewById(R.id.testButton);
        localAudioList = view.findViewById(R.id.audioList);

        myList = new ArrayList<>();

        audioList();


        testButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Home", Toast.LENGTH_SHORT).show();


        });
        localAudioList.setOnItemClickListener((parent, viewList, position, id) -> {
            for (int i = 0; i < localAudioList.getChildCount(); i++) {
                if(position == i ){
                    localAudioList.getChildAt(i).setBackgroundColor(Color.DKGRAY);
                }else{
                    localAudioList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
            }

        });
        return view;
    }


    private void audioList(){
        try{
            file = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + "/Recordings");
            list = file.listFiles();
            Log.i("a", Arrays.toString(list));

            if (list != null) {
                for (File value : list) {
                    myList.add(value.getName());
                }
            }
            if(myList != null)  Collections.sort(myList);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                    R.layout.custom_listview_adapter, android.R.id.text1, myList);
            localAudioList.setAdapter(adapter); //Set all the file in the list.

        }
        catch (Exception e) {
            Log.i("Hata2:", String.valueOf(e));
        }

    }
}