package ma.ensa.meryem.taikokgestionabsences;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Menu1 extends AppCompatActivity {

    DBHelper db = new DBHelper(this);
    private Button buttonAjoutClasse, buttonUpdateClasse, buttonSupprimerClasse;
    ListView listViewClasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);
        buttonAjoutClasse = findViewById(R.id.NouvelleClasse);
        buttonUpdateClasse  =findViewById(R.id.ModifierClasse);
        buttonSupprimerClasse =findViewById(R.id.SupprimerClasse);

        listViewClasse = findViewById(R.id.ListViewCl);
        retournerClasses();

        buttonAjoutClasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(getApplicationContext(), NouvelleClasse.class);
                 startActivity(intent);
            }
        });

        buttonUpdateClasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent = new Intent(getApplicationContext(), MettreAjourClasse.class);
                 startActivity(intent);
            }
        });

        buttonSupprimerClasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent = new Intent(getApplicationContext(), SupprimerClasse.class);
                startActivity(intent);
            }
        });




    }



    public void retournerClasses(){

        final ArrayList<String> liste = db.retournerListeClasses();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,liste);
        listViewClasse.setAdapter(arrayAdapter);


        listViewClasse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   Intent intent = new Intent(getApplicationContext(),choisirFichierClasse.class);
                  startActivity(intent);
            }
        });





    }




    }











