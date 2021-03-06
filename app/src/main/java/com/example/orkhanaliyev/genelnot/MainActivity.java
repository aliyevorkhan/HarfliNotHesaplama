package com.example.orkhanaliyev.genelnot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Double vizeNotu=101.0;
    Double finalButNotu=101.0;
    Double hamBasariNotu = 0.0;
    Double tStandartNotu = 0.0;
    String harf = "NULL";
    Double standartSapma=101.0;
    Double ortalamaDegeri=101.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // tanimlamalar
        final EditText etSinifOrtalamasi = findViewById(R.id.etSinifOrtalamasi);
        etSinifOrtalamasi.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});
        final EditText etStandartSapma= findViewById(R.id.etStandartSapma);
        etStandartSapma.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});
        final EditText etVizeNotu= findViewById(R.id.etVizeNotu);
        etVizeNotu.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});
        final EditText etFinalButNotu= findViewById(R.id.etFinalButNotu);
        etFinalButNotu.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});
        Button btnHesapla= findViewById(R.id.btnHesapla);
        final Button btnClr= findViewById(R.id.btnclr);
        final TextView tvSonuc = findViewById(R.id.tvSonuc);
        //ImageView ivLogo = findViewById(R.id.ivLogo);
        //////////////////////////////////////////////////

        //ivLogo.setImageResource(R.drawable.logo);

        btnHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ortalamaDegeri = Double.parseDouble(etSinifOrtalamasi.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    ortalamaDegeri=101.0;
                }
                try {
                    standartSapma = Double.parseDouble(etStandartSapma.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    standartSapma=101.1;
                }
                try{
                    vizeNotu = Double.parseDouble(etVizeNotu.getText().toString());

                } catch (NumberFormatException e)
                {
                    e.printStackTrace();
                    vizeNotu=101.0;
                }
                try {
                    finalButNotu = Double.parseDouble(etFinalButNotu.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    finalButNotu=101.0;
                }
                hamBasariNotu = HamBasariNotuHesapla(vizeNotu,finalButNotu, hamBasariNotu);
                tStandartNotu = TStandartNotuHesapla(hamBasariNotu, ortalamaDegeri, standartSapma, tStandartNotu);
                harf = HarfeDonustur(hamBasariNotu,tStandartNotu,harf);

                if(finalButNotu < 45){
                    harf = "FF";
                }

                tvSonuc.setText(harf);

            }
        });

        btnClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etVizeNotu.setText(null);
                etFinalButNotu.setText(null);
                etSinifOrtalamasi.setText(null);
                etStandartSapma.setText(null);
                tvSonuc.setText("Sonucu görüntüle");

            }
        });
    }

    public double HamBasariNotuHesapla(double vizeNotu, double finalButNotu, double hamBasariNotu){
        if(vizeNotu==101)
            Toast.makeText(MainActivity.this, "Vize notunu giriniz",Toast.LENGTH_LONG).show();

        else if(finalButNotu==101)
            Toast.makeText(MainActivity.this, "Final but notunu giriniz",Toast.LENGTH_LONG).show();
        else {
            hamBasariNotu = (vizeNotu + finalButNotu)/2;
            return hamBasariNotu;
        }
        return 0;
    }

    public double TStandartNotuHesapla(double hamBasariNotu, double ortalamaDegeri, double standartSapma, double tStandartNotu){
        if (ortalamaDegeri==101)
            Toast.makeText(MainActivity.this, "Ortalamayi giriniz",Toast.LENGTH_LONG).show();
        else if(standartSapma==101)
            Toast.makeText(MainActivity.this, "Standart sapmayi giriniz",Toast.LENGTH_LONG).show();
        else {
            tStandartNotu = ((hamBasariNotu - ortalamaDegeri)/ standartSapma) * 10 + 50;
            return tStandartNotu;

        }
        return 0;
    }

    public String HarfeDonustur(double hamBasariNotu, double tStandartNotu, String harf){
            if(hamBasariNotu > 0.0 && hamBasariNotu<=42.5){ //kotu
                if(tStandartNotu<36){
                    harf = "FF";
                }
                else if (tStandartNotu>=36 && tStandartNotu<=40.99){
                    harf = "FD";
                }
                else if (tStandartNotu>= 41 && tStandartNotu<=45.99){
                    harf = "DD";
                }
                else if (tStandartNotu>= 46 && tStandartNotu<=50.99){
                    harf = "DC";
                }
                else if (tStandartNotu>= 51 && tStandartNotu<=55.99){
                    harf = "CC";
                }
                else if (tStandartNotu>= 56 && tStandartNotu<=60.99){
                    harf = "CB";
                }
                else if (tStandartNotu>= 61 && tStandartNotu<=65.99){
                    harf = "BB";
                }
                else if (tStandartNotu>= 66 && tStandartNotu<=70.99){
                    harf = "BA";
                }
                else if (tStandartNotu>= 71){
                    harf = "AA";
                }

            }


            else if(hamBasariNotu > 42.5 &&hamBasariNotu<=47.5){ //zayif
                if(tStandartNotu<34){
                    harf = "FF";
                }
                else if (tStandartNotu>=34 && tStandartNotu<=38.99){
                    harf = "FD";
                }
                else if (tStandartNotu>= 39 && tStandartNotu<=43.99){
                    harf = "DD";
                }
                else if (tStandartNotu>= 44 && tStandartNotu<=48.99){
                    harf = "DC";
                }
                else if (tStandartNotu>= 49 && tStandartNotu<=53.99){
                    harf = "CC";
                }
                else if (tStandartNotu>= 54 && tStandartNotu<=58.99){
                    harf = "CB";
                }
                else if (tStandartNotu>= 59 && tStandartNotu<=63.99){
                    harf = "BB";
                }
                else if (tStandartNotu>= 64 && tStandartNotu<=68.99){
                    harf = "BA";
                }
                else if (tStandartNotu>= 69){
                    harf = "AA";
                }
            }

            else if(hamBasariNotu > 47.5 && hamBasariNotu<=52.5){ //orta
                if(tStandartNotu<32){
                    harf = "FF";
                }
                else if (tStandartNotu>=32 && tStandartNotu<=36.99){
                    harf = "FD";
                }
                else if (tStandartNotu>= 37 && tStandartNotu<=41.99){
                    harf = "DD";
                }
                else if (tStandartNotu>= 42 && tStandartNotu<=46.99){
                    harf = "DC";
                }
                else if (tStandartNotu>= 47 && tStandartNotu<=51.99){
                    harf = "CC";
                }
                else if (tStandartNotu>= 52 && tStandartNotu<=56.99){
                    harf = "CB";
                }
                else if (tStandartNotu>= 57 && tStandartNotu<=61.99){
                    harf = "BB";
                }
                else if (tStandartNotu>= 62 && tStandartNotu<=66.99){
                    harf = "BA";
                }
                else if (tStandartNotu>= 67){
                    harf = "AA";
                }
            }


            else if(hamBasariNotu > 52.5 && hamBasariNotu<=57.5){ //ortanin ustu
                if(tStandartNotu<30){
                    harf = "FF";
                }
                else if (tStandartNotu>=30 && tStandartNotu<=34.99){
                    harf = "FD";
                }
                else if (tStandartNotu>= 35 && tStandartNotu<=39.99){
                    harf = "DD";
                }
                else if (tStandartNotu>= 40 && tStandartNotu<=44.99){
                    harf = "DC";
                }
                else if (tStandartNotu>= 45 && tStandartNotu<=49.99){
                    harf = "CC";
                }
                else if (tStandartNotu>= 50 && tStandartNotu<=54.99){
                    harf = "CB";
                }
                else if (tStandartNotu>= 55 && tStandartNotu<=59.99){
                    harf = "BB";
                }
                else if (tStandartNotu>= 60 && tStandartNotu<=64.99){
                    harf = "BA";
                }
                else if (tStandartNotu>= 65){
                    harf = "AA";
                }
            }


            else if(hamBasariNotu > 57.5 && hamBasariNotu<=62.5){ //iyi
                if(tStandartNotu<28){
                    harf = "FF";
                }
                else if (tStandartNotu>=28 && tStandartNotu<=32.99){
                    harf = "FD";
                }
                else if (tStandartNotu>= 33 && tStandartNotu<=37.99){
                    harf = "DD";
                }
                else if (tStandartNotu>= 38 && tStandartNotu<=42.99){
                    harf = "DC";
                }
                else if (tStandartNotu>= 43 && tStandartNotu<=47.99){
                    harf = "CC";
                }
                else if (tStandartNotu>= 48 && tStandartNotu<=52.99){
                    harf = "CB";
                }
                else if (tStandartNotu>= 53 && tStandartNotu<=57.99){
                    harf = "BB";
                }
                else if (tStandartNotu>= 58 && tStandartNotu<=62.99){
                    harf = "BA";
                }
                else if (tStandartNotu>= 63){
                    harf = "AA";
                }
            }

            else if(hamBasariNotu > 62.5 && hamBasariNotu<=70){ // cok iyi
                if(tStandartNotu<26){
                    harf = "FF";
                }
                else if (tStandartNotu>=26 && tStandartNotu<=30.99){
                    harf = "FD";
                }
                else if (tStandartNotu>= 31 && tStandartNotu<=35.99){
                    harf = "DD";
                }
                else if (tStandartNotu>= 36 && tStandartNotu<=40.99){
                    harf = "DC";
                }
                else if (tStandartNotu>= 41 && tStandartNotu<=45.99){
                    harf = "CC";
                }
                else if (tStandartNotu>= 46 && tStandartNotu<=50.99){
                    harf = "CB";
                }
                else if (tStandartNotu>= 51 && tStandartNotu<=55.99){
                    harf = "BB";
                }
                else if (tStandartNotu>= 56 && tStandartNotu<=60.99){
                    harf = "BA";
                }
                else if (tStandartNotu>= 61){
                    harf = "AA";
                }
            }


            else if(hamBasariNotu > 70 && hamBasariNotu<=80){ // mukemmel
                if(tStandartNotu<24){
                    harf = "FF";
                }
                else if (tStandartNotu>=24 && tStandartNotu<=28.99){
                    harf = "FD";
                }
                else if (tStandartNotu>= 29 && tStandartNotu<=33.99){
                    harf = "DD";
                }
                else if (tStandartNotu>= 34 && tStandartNotu<=38.99){
                    harf = "DC";
                }
                else if (tStandartNotu>= 39 && tStandartNotu<=43.99){
                    harf = "CC";
                }
                else if (tStandartNotu>= 44 && tStandartNotu<=48.99){
                    harf = "CB";
                }
                else if (tStandartNotu>= 49 && tStandartNotu<=53.99){
                    harf = "BB";
                }
                else if (tStandartNotu>= 54 && tStandartNotu<=58.99){
                    harf = "BA";
                }
                else if (tStandartNotu>= 59){
                    harf = "AA";
                }
            }


            else if (hamBasariNotu>=80 && hamBasariNotu <=100){ // ustun basari
                harf ="AA";
            }
            return harf;
    }


}
