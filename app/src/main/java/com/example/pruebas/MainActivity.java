package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnScan, btnBuscar;
    EditText txtEmpresa, txtDependencia, txtSerie, txtSubserie, txtCaja, txtResultado;


    RequestQueue requestQueue;

    private static final String URL1 = "http://10.0.18.131/lectorqr/save.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();


        btnBuscar.setOnClickListener(this);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Lector - DATA 3000 SAS");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Escaneo Cancelado", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                txtResultado.setText(result.getContents());
            }
        } else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initUI(){

        //buttons
        btnBuscar = findViewById(R.id.btnBuscar);
        btnScan = findViewById(R.id.btnScan);

        //txt

        txtResultado = findViewById(R.id.txtResultado);
        txtEmpresa = findViewById(R.id.txtEmpresa);
        txtDependencia = findViewById(R.id.txtDependencia);
        txtSerie = findViewById(R.id.txtSerie);
        txtSubserie = findViewById(R.id.txtSubserie);
        txtCaja = findViewById(R.id.txtCaja);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if ( id == R.id.btnBuscar){

            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("id", txtResultado.getText().toString().trim());
            startActivity(intent);

        }
            txtResultado.setText("");
    }

}