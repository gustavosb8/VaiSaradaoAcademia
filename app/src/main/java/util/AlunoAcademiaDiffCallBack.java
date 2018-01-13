package util;

import android.support.v7.util.DiffUtil;

import java.util.ArrayList;
import java.util.List;

import model.AlunoAcademia;

/**
 * Created by Ankit Sinhal.
 * Disponível em: https://android.jlelse.eu/smart-way-to-update-recyclerview-using-diffutil-345941a160e0
 */

public class AlunoAcademiaDiffCallBack extends DiffUtil.Callback{

    private final ArrayList<AlunoAcademia> listaAntiga;
    private final ArrayList<AlunoAcademia> listaNova;

    public AlunoAcademiaDiffCallBack(ArrayList<AlunoAcademia> antiga, ArrayList<AlunoAcademia> nova) {
        this.listaAntiga = antiga;
        this.listaNova = nova;
    }


    @Override
    public int getOldListSize() {
        return this.listaAntiga.size();
    }

    @Override
    public int getNewListSize() {
        return this.listaNova.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return listaAntiga.get(oldItemPosition).getId() == listaNova.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final AlunoAcademia alunoAntigo = listaAntiga.get(oldItemPosition);
        final AlunoAcademia alunoNovo = listaAntiga.get(newItemPosition);

        return alunoAntigo.getNome().equals(alunoNovo.getNome()) && alunoAntigo.getRg().equals(alunoNovo.getRg());
    }

    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

}
