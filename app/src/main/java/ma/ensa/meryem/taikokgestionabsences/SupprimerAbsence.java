package ma.ensa.meryem.taikokgestionabsences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SupprimerAbsence extends AppCompatActivity {
    DBHelper db = new DBHelper(this);


    private Button ButtonSupprimerAbsence;
    private EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer_absence);
        ButtonSupprimerAbsence = (Button) findViewById(R.id.SupprimerAb);
        id = (EditText) findViewById(R.id.idAbsence);



        ButtonSupprimerAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supprimer();
                Intent intent = new Intent(getApplicationContext(), ListAbsence.class);
                startActivity(intent);
            }
        });



    }

    public void supprimer(){
        String ID = id.getText().toString();
        int result = db.supprimerAbsence(ID);
        if(result == 1) {
            Toast.makeText(SupprimerAbsence.this, "Absence Supprimée ! ", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(SupprimerAbsence.this, "Erreur !", Toast.LENGTH_LONG).show();
        }
    }



}
