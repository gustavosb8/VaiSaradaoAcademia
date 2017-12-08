package dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.widget.Toast;

import java.util.ArrayList;

import model.AlunoAcademia;
import util.DBGateway;

/**
 * Created by Gustavo Bastos on 07/12/2017.
 */

public class DAOAcademia {

    private DBGateway gw;
    private ArrayList<AlunoAcademia> alunos = new ArrayList<>();
    private Cursor cursor;

    public boolean salvar(AlunoAcademia aluno){
        long resultado;

        ContentValues parametros = new ContentValues();
        
        parametros.put("nome", aluno.getNome());
        parametros.put("endereco", aluno.getEndereco());
        parametros.put("sexo", aluno.getSexo());
        parametros.put("dataNascimento", aluno.getDataNascimento());
        parametros.put("cpf", aluno.getCpf());
        parametros.put("rg", aluno.getRg());
        parametros.put("modalidade", aluno.getModalidade());
        parametros.put("dataAdmissao", aluno.getDataAdmissao());
        parametros.put("professorResp", aluno.getProfessor());

        try{
            resultado = gw.getDatabase().insert("aluno", null, parametros);
            if(resultado > 0){
                return true;
            }else {
                return false;
            }
        }catch (SQLException erro){
            return false;
        }
    }

    public ArrayList<AlunoAcademia> getAlunos() {
        alunos = new ArrayList<>();
        String colunas [] = {"nome", "endereco", "sexo", "dataNascimento", "cpf", "rg","modalidade", "dataAdmissao","professorResp"};
        cursor = gw.getDatabase().query(false,"aluno",colunas,null,null,null,null,null,null);

        if(cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                AlunoAcademia aluno = new AlunoAcademia();
                aluno.setNome(cursor.getString(0));
                aluno.setEndereco(cursor.getString(1));
                aluno.setSexo(cursor.getString(2));
                aluno.setDataNascimento(cursor.getString(3));
                aluno.setCpf(cursor.getString(4));
                aluno.setRg(cursor.getString(5));
                aluno.setModalidade(cursor.getString(6));
                aluno.setDataAdmissao(cursor.getString(7));
                aluno.setProfessor(cursor.getString(8));

                alunos.add(aluno);

                aluno = null;
            }

            return alunos;
        }
        else{
            return alunos;
        }
    }
}
