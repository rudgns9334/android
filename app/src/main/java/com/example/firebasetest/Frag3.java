package com.example.firebasetest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.soundcloud.android.crop.Crop;

import java.io.File;

public class Frag3 extends Fragment implements View.OnClickListener {
    ImageView imageView;
    Button camera;
    Button share;
    NaviActivity activity;
    private Context mContext;

    public Frag3() {
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        activity=(NaviActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plus, container, false);

        camera = (Button) rootView.findViewById(R.id.camera);
        camera.setOnClickListener(this);

        imageView = (ImageView) rootView.findViewById(R.id.picture);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.camera:
                imageView.setImageDrawable(null);
                Crop.pickImage(getContext(),this);
                System.out.println(imageView);
//기존소스는 Crop.pickImage(Activity activity) 를 사용하나
//Fragment에서는 Crop.pickImage(Context context, Fragment fragment)를 사용
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == Activity.RESULT_OK) {
            // RESULT_OK 는 동작 성공을 의미하며 수치는 -1 인데, Fragment에는 없다.

            // 따라서, Activity에서 사용되는 RESULT_OK값을 가져와서 사용한다.

            Log.d("onActivityResult", "request pick");
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            Log.d("onActivityResult", "request crop");
            handleCrop(resultCode, result);
        }
    }



    private void beginCrop(Uri source) {
        Log.d("beginCrop", "start");
        Uri destination = Uri.fromFile(new File(getActivity().getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(getContext(),this);
        //start(Activity activity) 부분을 start(Context context, Fragment fragment)로 변경
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == Activity.RESULT_OK) {
            // Activity 의 RESULT_OK값을 사용
            Log.d("handleCrop", "RESULT_OK");
            imageView.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {
            Log.d("handleCrop", "RESULT_ERROR");
            Toast.makeText(getActivity(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();

            //Activity에서 사용되던 this는 Fragment에서 보통 getActivity() 또는 getContext() 로 변경해서 사용한다.
        }
    }

}
