package ma.ensa.meryem.taikokgestionabsences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MettreAjourAbsence extends AppCompatActivity {

    DBHelper db = new DBHelper(this);

    private Button ButtonMettreAJourAbsence;
    private EditText id;
    private EditText observation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mettrea_a_jour_absence);
        ButtonMettreAJourAbsence = (Button) findViewById(R.id.EnregistrerObservation);
        id = (EditText) findViewById(R.id.idAbsenceEtud);
        observation = (EditText) findViewById(R.id.ObservationAbsence);

        ButtonMettreAJourAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AjoutObservation();
                Intent intent = new Intent(getApplicationContext(), ListAbsence.class);
                startActivity(intent);
            }
        });

    }

    public void AjoutObservation(){
        String identifiant = id.getText().toString();
        String Observatio = observation.getText().toString();

        Boolean resultat = db.AjoutObservation(identifiant, Observatio);

        if(resultat == true) {
            Toast.makeText(MettreAjourAbsence.this, "Observation Ajoutée!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MettreAjourAbsence.this, "Erreur! ", Toast.LENGTH_LONG).show();
        }
    }



    }





