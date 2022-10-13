package com.example.grampanchayatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    RadioGroup radioGroup;
    RadioButton radioButton;
    Spinner jilha,taluka,village;
    ArrayList<String> arrayList;
    ArrayAdapter<CharSequence> adapter_jilha,adapter_tal,adapter_village;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radioGroup=findViewById(R.id.radiogp);
        jilha=findViewById(R.id.spinner);
        taluka=findViewById(R.id.spinner1);
        village=findViewById(R.id.spinner2);

        arrayList=new ArrayList<>();

         adapter_jilha=ArrayAdapter.createFromResource(this,R.array.jilha,
                android.R.layout.simple_spinner_item);
        adapter_jilha.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        jilha.setAdapter(adapter_jilha);

       jilha.setOnItemSelectedListener(this);

       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               switch (checkedId)
               {
                   case R.id.radioButton:
                       Toast.makeText(MainActivity.this, "Selected ७/१२", Toast.LENGTH_SHORT).show();
                       break;

                   case R.id.radioButton2:
                       Toast.makeText(MainActivity.this, "Selected 8अ", Toast.LENGTH_SHORT).show();
                       break;

                   case R.id.radioButton3:
                       Toast.makeText(MainActivity.this, "Selected मालमत्ता", Toast.LENGTH_SHORT).show();
                       break;
               }
           }
       });


    }





    //SpinnerJilha
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //taluka

        if (position==0)
        {
            adapter_tal= ArrayAdapter.createFromResource(this,R.array.TalukaAmravati,
                    android.R.layout.simple_spinner_item);
            adapter_tal.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        }
        if (position==1)
        {
            adapter_tal= ArrayAdapter.createFromResource(this,R.array.TalukaPune,
                    android.R.layout.simple_spinner_item);
            adapter_tal.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        }
        if (position==2)
        {
            adapter_tal= ArrayAdapter.createFromResource(this,R.array.TalukaSangli,
                    android.R.layout.simple_spinner_item);
           adapter_tal.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        }
            taluka.setAdapter(adapter_tal);

        //Village


        if (position==0)
        {
            adapter_village= ArrayAdapter.createFromResource(this,R.array.Villageamravati,
                    android.R.layout.simple_spinner_item);
            adapter_village.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        }
        if (position==1)
        {
           adapter_village= ArrayAdapter.createFromResource(this,R.array.VillageHaveli,
                    android.R.layout.simple_spinner_item);
            adapter_village.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        }
        if (position==2)
        {
            adapter_village= ArrayAdapter.createFromResource(this,R.array.VillageMiraj,
                    android.R.layout.simple_spinner_item);
            adapter_village.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        }
        village.setAdapter(adapter_village);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }



}