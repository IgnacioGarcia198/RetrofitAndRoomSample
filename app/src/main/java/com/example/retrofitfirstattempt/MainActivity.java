package com.example.retrofitfirstattempt;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.retrofitfirstattempt.api.GetDataService;
import com.example.retrofitfirstattempt.api.RetrofitClientInstance;
import com.example.retrofitfirstattempt.data.RetroPhotoRepository;
import com.example.retrofitfirstattempt.model.RetroPhoto;
import com.example.retrofitfirstattempt.ui.RetroPhotoViewModel;
import com.example.retrofitfirstattempt.ui.ViewModelFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private int albumId;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ProgressDialog progressDoalog;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        //viewModel = ViewModelProviders new ViewModelFactory(RetroPhotoRepository.getInstance(this))
        generateDataList();
        RetroPhotoViewModel viewModel = ViewModelProviders.of(
                this,new ViewModelFactory(RetroPhotoRepository.getInstance(this)))
                .get(RetroPhotoViewModel.class);

        progressDoalog.show();

        viewModel.init(1);
        viewModel.getRetroPhotoList().observe(this, list -> {
            adapter.setDataList(list);
            progressDoalog.dismiss();
        });

        Spinner spinner = findViewById(R.id.spinner);
        EditText editText = findViewById(R.id.editText);

        albumId = 1;
        Integer[] ints = new Integer[100];
        for(int i = 0; i < ints.length;i++) {
            ints[i]=i+1;
        }

        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_item,ints);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                albumId = position+1;
                log("albumId = "+albumId);
                progressDoalog.show();
                viewModel.init(albumId);
                viewModel.getRetroPhotoList().observe(MainActivity.this, list -> {
                    adapter.setDataList(list);
                    progressDoalog.dismiss();
                });
                //valueTV.setText(String.valueOf(mCount));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.findPhotosByTitleInAlbum(s.toString(),albumId).observe(
                        MainActivity.this,dataList -> {
                    adapter.setDataList(dataList);
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList() {
        RecyclerView recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void log(String msg) {
        Log.d(LOG_TAG,msg);
    }
}
