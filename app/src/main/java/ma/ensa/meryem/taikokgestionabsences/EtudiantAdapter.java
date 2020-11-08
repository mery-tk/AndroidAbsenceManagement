package ma.ensa.meryem.taikokgestionabsences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EtudiantAdapter extends BaseAdapter {

    private static final String TAG = "EtudiantAdapter";

    private DBHelper db;
    private List<Etudiant> ListeEtudiants;
    private LayoutInflater layoutInflater;

    public EtudiantAdapter(@NonNull Context context, @NonNull List<Etudiant> listeEtudiants) {
        ListeEtudiants = listeEtudiants;
        layoutInflater = LayoutInflater.from(context);
        db = new DBHelper(context);

    }

    @Override
    public int getCount() {
        return (ListeEtudiants == null) ? 0 : ListeEtudiants.size();
    }

    @Override
    public Object getItem(int position) {
        return ListeEtudiants.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (view == null)
            view = layoutInflater.inflate(R.layout.activity_check_absence, null);
        TextView nom = (TextView) view.findViewById(R.id.NomEtudiant);
        TextView prenom = (TextView) view.findViewById(R.id.PrenomEtudiant);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.Checkbox);

        final Etudiant etudiant = ListeEtudiants.get(position);
        nom.setText(etudiant.getNomEtudiant());
        prenom.setText(etudiant.getPrenomEtudiant());
        checkBox.setChecked(etudiant.isChecked());


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                if (isChecked) {
                    db.marquerAbsence(etudiant);
                }
                etudiant.setChecked(isChecked);
            }
        });


        return view;
    }


}
