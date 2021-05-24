package com.example.folhapagamento;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText salaBru = findViewById(R.id.salaBru);
        Button btnCalc = findViewById(R.id.btnCalc);
        final TextView salaLiq = findViewById(R.id.salaLiq);


        // Botão para calcular
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double imc = calcsalaBru(Double.parseDouble(salaBru.getText().toString()));
                    calcsalaBru ( imc, salaLiq);
                }
                catch (Exception e) {
                    Log.d("Erro:",e.toString());
                    salaBru.setText(getText(R.string.err));
                }
                hideKeyBoard();
            }
        });

    }


    // Cálculo do salario bruto
    private double calcsalaBru(double salaBru) {

        double ir = salaBru * 0.11  ; //ir
        double inss = salaBru * 0.08 ; //inss
        double sind = salaBru * 0.05  ; //sind
        double resp = salaBru - ir - inss - sind;


        return resp;
    }

    // Obtem cálculo e formula resposta
    private void SalaLiq(double calc,double descInss, TextView tv){
        DecimalFormat df = new DecimalFormat("#.00");


        String resp = "Seu salário liqido é : "+df.format(calc)+"\n";

        tv.setText(resp);
    }
    // Esconde o teclado
    public void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if(view!= null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}