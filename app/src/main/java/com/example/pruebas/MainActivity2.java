package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {

    Button btnRegresar;
    EditText txtEmpresa, txtDependencia, txtSerie, txtSubserie, txtCaja, txtResultado;
    String id;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        requestQueue = Volley.newRequestQueue(this);


        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            id = extras.getString("id");

        }

        initUI();
        readUser();


        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void initUI(){

        //buttons
        btnRegresar = findViewById(R.id.btnRegresar);

        //txt

        txtResultado = findViewById(R.id.txtResultado);
        txtEmpresa = findViewById(R.id.txtEmpresa);
        txtDependencia = findViewById(R.id.txtDependencia);
        txtSerie = findViewById(R.id.txtSerie);
        txtSubserie = findViewById(R.id.txtSubserie);
        txtCaja = findViewById(R.id.txtCaja);
    }

    private void readUser(){
        String URL = "http://10.0.18.177/lectorqr/buscar.php?id=" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String empresa, dependencia, serie, subserie, num_Caja, id;
                        try {
                            empresa = response.getString("empresa");
                            dependencia = response.getString("dependencia");
                            serie = response.getString("serie");
                            subserie = response.getString("subserie");
                            num_Caja = response.getString("num_Caja");
                            id = response.getString("id");


                            txtEmpresa.setText(empresa);
                            txtDependencia.setText(dependencia);
                            txtSerie.setText(serie);
                            txtSubserie.setText(subserie);
                            txtCaja.setText(num_Caja);
                            txtResultado.setText(id);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );

        requestQueue.add(jsonObjectRequest);
    }
}