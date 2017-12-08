package util;

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

public class Adaptador extends RecyclerView.Adapter {

    ArrayList<AlunoAcademia> alunos;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Adaptador(ArrayList<AlunoAcademia> alunos) {
        this.alunos = alunos;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Adaptador onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_tela_dados_cadastrados, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder((TextView) v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }




    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return alunos.size();
    }

}
