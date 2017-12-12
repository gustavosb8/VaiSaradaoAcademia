package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ifma.edu.com.academia.R;

/**
 * Created by Gustavo Bastos on 12/12/2017.
 */

public class ViewHolderAlunoAcademia extends RecyclerView.ViewHolder{

    private TextView txtNomeAluno;
    private TextView txtModalidades;
    private TextView txtProfessor;

    public ViewHolderAlunoAcademia(View itemView) {
        super(itemView);
        txtNomeAluno = itemView.findViewById(R.id.lblAluno);
        txtModalidades = itemView.findViewById(R.id.lblModalidades);
        txtProfessor = itemView.findViewById(R.id.lblProfessor);
    }

    public TextView getTxtNomeAluno() {
        return txtNomeAluno;
    }

    public void setTxtNomeAluno(TextView txtNomeAluno) {
        this.txtNomeAluno = txtNomeAluno;
    }

    public TextView getTxtModalidades() {
        return txtModalidades;
    }

    public void setTxtModalidades(TextView txtModalidades) {
        this.txtModalidades = txtModalidades;
    }

    public TextView getTxtProfessor() {
        return txtProfessor;
    }

    public void setTxtProfessor(TextView txtProfessor) {
        this.txtProfessor = txtProfessor;
    }
}
