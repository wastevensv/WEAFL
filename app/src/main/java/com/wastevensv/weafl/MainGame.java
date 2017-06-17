package com.wastevensv.weafl;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainGame extends AppCompatActivity {
    private final double ELEMENT_ADVANTAGE = 2;

    private enum Elementals {Water, Earth, Air, Fire}

    private TextView txtWaterNPC, txtEarthNPC, txtAirNPC, txtFireNPC;
    private Button btnWaterNPC, btnEarthNPC, btnAirNPC, btnFireNPC;

    private TextView txtWater, txtEarth, txtAir, txtFire;
    private Button btnWater, btnEarth, btnAir, btnFire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        txtWaterNPC = (TextView)findViewById(R.id.txtWaterNPC);
        txtEarthNPC = (TextView)findViewById(R.id.txtEarthNPC);
        txtAirNPC = (TextView)findViewById(R.id.txtAirNPC);
        txtFireNPC = (TextView)findViewById(R.id.txtFireNPC);

        btnWaterNPC = (Button)findViewById(R.id.btnWaterNPC);
        btnEarthNPC = (Button)findViewById(R.id.btnEarthNPC);
        btnAirNPC = (Button)findViewById(R.id.btnAirNPC);
        btnFireNPC = (Button)findViewById(R.id.btnFireNPC);


        txtWater = (TextView)findViewById(R.id.txtWater);
        txtEarth = (TextView)findViewById(R.id.txtEarth);
        txtAir = (TextView)findViewById(R.id.txtAir);
        txtFire = (TextView)findViewById(R.id.txtFire);

        btnWater = (Button)findViewById(R.id.btnWater);
        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAttack(Elementals.Water);
            }
        });
        btnEarth = (Button)findViewById(R.id.btnEarth);
        btnEarth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAttack(Elementals.Earth);
            }
        });
        btnAir = (Button)findViewById(R.id.btnAir);
        btnAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAttack(Elementals.Air);
            }
        });
        btnFire = (Button)findViewById(R.id.btnFire);
        btnFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAttack(Elementals.Fire);
            }
        });
    }

    public void onAttack(Elementals plAtk) {

        btnWater.setEnabled(true);
        btnEarth.setEnabled(true);
        btnAir.setEnabled(true);
        btnFire.setEnabled(true);

        if(plAtk == Elementals.Water) {
            String val_str = txtWater.getText().toString();
            int val = Integer.parseInt(val_str);
            val++;

            txtWater.setText(String.valueOf(val));

            btnWater.setEnabled(false);
        } else if (plAtk == Elementals.Earth) {
            String val_str = txtEarth.getText().toString();
            int val = Integer.parseInt(val_str);
            val++;

            txtEarth.setText(String.valueOf(val));

            btnEarth.setEnabled(false);
        } else if (plAtk == Elementals.Air) {
            String val_str = txtAir.getText().toString();
            int val = Integer.parseInt(val_str);
            val++;

            txtAir.setText(String.valueOf(val));

            btnAir.setEnabled(false);
        } else if (plAtk == Elementals.Fire) {
            String val_str = txtFire.getText().toString();
            int val = Integer.parseInt(val_str);
            val++;

            txtFire.setText(String.valueOf(val));

            btnFire.setEnabled(false);
        }

        Elementals npcAtk = getNPCAtk();

        btnWaterNPC.setEnabled(true);
        btnEarthNPC.setEnabled(true);
        btnAirNPC.setEnabled(true);
        btnFireNPC.setEnabled(true);

        if(npcAtk == Elementals.Water) {
            String val_str = txtWaterNPC.getText().toString();
            int val = Integer.parseInt(val_str);
            val++;

            txtWaterNPC.setText(String.valueOf(val));

            btnWaterNPC.setEnabled(false);
        } else if (npcAtk == Elementals.Earth) {
            String val_str = txtEarthNPC.getText().toString();
            int val = Integer.parseInt(val_str);
            val++;

            txtEarthNPC.setText(String.valueOf(val));

            btnEarthNPC.setEnabled(false);
        } else if (npcAtk == Elementals.Air) {
            String val_str = txtAirNPC.getText().toString();
            int val = Integer.parseInt(val_str);
            val++;

            txtAirNPC.setText(String.valueOf(val));

            btnAirNPC.setEnabled(false);
        } else if (npcAtk == Elementals.Fire) {
            String val_str = txtFireNPC.getText().toString();
            int val = Integer.parseInt(val_str);
            val++;

            txtFireNPC.setText(String.valueOf(val));

            btnFireNPC.setEnabled(false);
        }



        Toast.makeText(getApplicationContext(), plAtk.toString() + " vs " + npcAtk.toString(), Toast.LENGTH_SHORT).show();
        if(testPlayerWin(plAtk, npcAtk) == 1) {
            Toast.makeText(getApplicationContext(), "Player Win", Toast.LENGTH_SHORT).show();
        } else if(testPlayerWin(plAtk, npcAtk) == 0) {
            Toast.makeText(getApplicationContext(), "Tie", Toast.LENGTH_SHORT).show();
        } else if(testPlayerWin(plAtk, npcAtk) == -1) {
            Toast.makeText(getApplicationContext(), "NPC Win", Toast.LENGTH_SHORT).show();
        }
    }

    private Elementals getNPCAtk() {
        Random r = new Random();
        int choice = r.nextInt(4);
        if(choice == 0) {
            if(btnWaterNPC.isEnabled()) {
                return Elementals.Water;
            } else {
                return Elementals.Earth;
            }
        } else if(choice == 1) {
            if(btnEarthNPC.isEnabled()) {
                return Elementals.Earth;
            } else {
                return Elementals.Air;
            }
        } else if(choice == 2) {
            if(btnAirNPC.isEnabled()) {
                return Elementals.Air;
            } else {
                return Elementals.Fire;
            }
        } else {
            if(btnFireNPC.isEnabled()) {
                return Elementals.Fire;
            } else {
                return Elementals.Water;
            }
        }
    }

    private double getMultiplier(Elementals a, Elementals b) {
        if(a == Elementals.Water && b == Elementals.Earth) { // Strong Ratios
            return ELEMENT_ADVANTAGE;
        } else if (a == Elementals.Earth && b == Elementals.Air) {
            return ELEMENT_ADVANTAGE;
        } else if (a == Elementals.Air && b == Elementals.Fire) {
            return ELEMENT_ADVANTAGE;
        } else if (a == Elementals.Fire && b == Elementals.Water) {
            return ELEMENT_ADVANTAGE;
        } else if(b == Elementals.Water && a == Elementals.Earth) { // Strong Ratios
            return 1/ELEMENT_ADVANTAGE;
        } else if (b == Elementals.Earth && a == Elementals.Air) {
            return 1/ELEMENT_ADVANTAGE;
        } else if (b == Elementals.Air && a == Elementals.Fire) {
            return 1/ELEMENT_ADVANTAGE;
        } else if (b == Elementals.Fire && a == Elementals.Water) {
            return 1/ELEMENT_ADVANTAGE;
        } else {
            return 1;
        }
    }

    private int testPlayerWin(Elementals playerAtk, Elementals npcAtk) {
        double playerAtkLvl = 0;
        if(playerAtk == Elementals.Water) {
            String val_str = txtFire.getText().toString();
            playerAtkLvl = Integer.parseInt(val_str);
        } else if(playerAtk == Elementals.Earth) {
            String val_str = txtEarth.getText().toString();
            playerAtkLvl = Integer.parseInt(val_str);
        } else if(playerAtk == Elementals.Air) {
            String val_str = txtAir.getText().toString();
            playerAtkLvl = Integer.parseInt(val_str);
        } else if(playerAtk == Elementals.Fire) {
            String val_str = txtFire.getText().toString();
            playerAtkLvl = Integer.parseInt(val_str);
        }

        double npcAtkLvl = 0;
        if(npcAtk == Elementals.Water) {
            String val_str = txtWaterNPC.getText().toString();
            npcAtkLvl = Integer.parseInt(val_str);
        } else if(npcAtk == Elementals.Earth) {
            String val_str = txtEarthNPC.getText().toString();
            npcAtkLvl = Integer.parseInt(val_str);
        } else if(npcAtk == Elementals.Air) {
            String val_str = txtAirNPC.getText().toString();
            npcAtkLvl = Integer.parseInt(val_str);
        } else if(npcAtk == Elementals.Fire) {
            String val_str = txtFireNPC.getText().toString();
            npcAtkLvl = Integer.parseInt(val_str);
        }

        if((playerAtkLvl * getMultiplier(playerAtk, npcAtk)) > npcAtkLvl) {
            return 1;
        } else if((playerAtkLvl * getMultiplier(playerAtk, npcAtk)) == npcAtkLvl) {
            return 0;
        } else {
            return -1;
        }
    }
}
