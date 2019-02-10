package com.example.anderson.lojadelivrosvirtual;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.StringCharacterIterator;

public class MainActivity extends AppCompatActivity {

    Button btnscanner;


    //FOI CRIADAS AS VARIAVEIS PARA ESSA CLASSE QUE SERÁ MAIS AFRENTE ANEXADAS AS VAR. DA TELA GRÁFICA
    Button btOk;
    CheckBox cbCsharp, cbLinux, cbEvangelio, cbFisica, cbMatematica, cbPortugues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnscanner = ( Button ) findViewById(R.id.btnscanner);
        final Activity activity = this;
        btnscanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("CameraSCANNER");
                integrator.setCameraId(0);
                integrator.initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() != null) {
                alert(result.getContents());

            }else {
                alert("Scan cancelado!!");
            }

        } else
            super.onActivityResult(requestCode, resultCode, data);
    }


  private void alert(String msg){

      Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
  }
}
