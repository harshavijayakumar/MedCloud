package com.example.tinku.foodhuntercm.Operations.UpdateInfo;

/* Import appropriate libraries */
import android.app.Fragment;
import com.example.tinku.foodhuntercm.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tinku on 4/2/16.
 */
/* Fragment for showing about us information on the screen */
public class AboutUsFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /* Inflate About us information here */
        View v = inflater.inflate(R.layout.aboutus, container, false);
        return v;
    }
}


