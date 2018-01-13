package adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dao.DAOAcademia;
import ifma.edu.com.academia.R;
import ifma.edu.com.academia.TelaDadosCadastrados;
import ifma.edu.com.academia.TelaEditar;
import model.AlunoAcademia;
import util.AlunoAcademiaDiffCallBack;

/**
 * Created by Gustavo Bastos on 07/12/2017.
 * AULA 2
 */

public class AlunoAcademiaAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<AlunoAcademia> alunos;
    DAOAcademia dao;

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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final AlunoAcademia aluno = alunos.get(position);
        ViewHolderAlunoAcademia avh = (ViewHolderAlunoAcademia)holder;
        avh.getTxtNomeAluno().setText(aluno.getNome());
        avh.getTxtModalidades().setText(aluno.getModalidade());
        avh.getTxtProfessor().setText(aluno.getProfessor());




        avh.getImageButtonEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new DAOAcademia(context);
                try{


                    //Tentativa de chamar activity
                    Intent it = new Intent(context, TelaEditar.class);
                    it.putExtra("aluno", aluno);
                    context.startActivity(it);


                    updateAlunoAcademiaItens(dao.getAlunos());

                }catch(Exception err){
                    Toast.makeText(context, err.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public void updateAlunoAcademiaItens(ArrayList<AlunoAcademia> novosAlunos){
        final AlunoAcademiaDiffCallBack diffCallback = new AlunoAcademiaDiffCallBack(this.alunos, novosAlunos);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.alunos.clear();
        this.alunos.addAll(novosAlunos);
        diffResult.dispatchUpdatesTo(this);
    }
}
