package adapters;

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
    public AlunoAcademiaAdapter(ArrayList<AlunoAcademia> alunos) {
        this.alunos = alunos;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return alunos.size();
    }

}
