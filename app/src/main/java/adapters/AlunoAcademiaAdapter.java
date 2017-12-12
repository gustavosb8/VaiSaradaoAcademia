package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ifma.edu.com.academia.R;
import model.AlunoAcademia;

/**
 * Created by Gustavo Bastos on 07/12/2017.
 * AULA 2
 */

public class AlunoAcademiaAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<AlunoAcademia> alunos;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public AlunoAcademiaAdapter(Context context, ArrayList<AlunoAcademia> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View alunoRecycle = LayoutInflater.from(context).inflate(R.layout.layout_aluno_recyclerview, null);
        ViewHolderAlunoAcademia avh = new ViewHolderAlunoAcademia(alunoRecycle);
        return avh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlunoAcademia aluno = alunos.get(position);
        ViewHolderAlunoAcademia avh = (ViewHolderAlunoAcademia)holder;
        avh.getTxtNomeAluno().setText(aluno.getNome());
        avh.getTxtModalidades().setText(aluno.getModalidade());
        avh.getTxtProfessor().setText(aluno.getProfessor());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return alunos.size();
    }

}
