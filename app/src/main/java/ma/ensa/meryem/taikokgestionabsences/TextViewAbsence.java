package ma.ensa.meryem.taikokgestionabsences;

import android.widget.TextView;

public class TextViewAbsence {

    public TextView nomEtudiant;
    public TextView prenomEtudiant;
    public TextView nombreAbsence;

    public void TextViewEtudiantAbs(Etudiant etudiantAbs){
        nomEtudiant.setText(etudiantAbs.getNomEtudiant());
        prenomEtudiant.setText(etudiantAbs.getPrenomEtudiant());

    }




}
