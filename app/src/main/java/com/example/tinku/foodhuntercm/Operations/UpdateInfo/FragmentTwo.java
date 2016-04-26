package com.example.tinku.foodhuntercm.Operations.UpdateInfo;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tinku.foodhuntercm.R;



import android.app.Fragment;
import com.example.tinku.foodhuntercm.R;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.app.Fragment;
import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.io.*;

import android.support.v7.widget.AppCompatButton;

import java.io.FilenameFilter;
import java.util.*;
import java.util.ArrayList;
import android.app.ListFragment;



/**
 * Created by tinku on 4/2/16.
 */


public class FragmentTwo extends Fragment {


    MediaPlayer player;
    Button stpbutton, song1, song2, song3;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("arg", "playSound: i am in music-----------");

        View v = inflater.inflate(R.layout.aboutus, container, false);


        return v;
    }

}


