package ma.ensa.meryem.taikokgestionabsences;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListEtudiantAbsentAdapter extends ArrayAdapter<Etudiant> {

    DBHelper db = new DBHelper(getContext());
    List<Etudiant> etudiantList;
    LayoutInflater layoutInflater;


    public ListEtudiantAbsentAdapter(@NonNull Context context, int resource, @NonNull List<Etudiant> liste) {
        super(context, resource, liste);
        etudiantList = liste;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        final TextViewAbsence viewholderabs;

        if(view == null){
            view = layoutInflater.inflate(R.layout.activity_row_layout,null);
            viewholderabs = new TextViewAbsence();
            viewholderabs.nomEtudiant = (TextView) view.findViewById(R.id.Nom_Etdiant);
            viewholderabs.prenomEtudiant = (TextView) view.findViewById(R.id.Prenom_Etudiant);
            viewholderabs.nombreAbsence = (TextView) view.findViewById(R.id.Nombre_Absence);
            view.setTag(viewholderabs);
        }else {
            viewholderabs = (TextViewAbsence) view.getTag();
        }

        final Etudiant etudiant = etudiantList.get(position);
        int nb = db.CompterNombreAbsence(etudiant);
        viewholderabs.nombreAbsence.setText(Integer.toString(nb));
        viewholderabs.TextViewEtudiantAbs(etudiant);
        return view;
    }



}
