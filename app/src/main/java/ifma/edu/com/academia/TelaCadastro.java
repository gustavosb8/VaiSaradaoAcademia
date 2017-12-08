package ifma.edu.com.academia;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import dao.DAOAcademia;
import model.AlunoAcademia;
import util.CriaBD;

public class TelaCadastro extends AppCompatActivity {

    private EditText edtxtAluno;
    private EditText edtxtEnderecoAluno;
    private RadioGroup rdgSexo;
    private EditText edtxtNascimento;
    private EditText edtxtCPFAluno;
    private EditText edtxtRGAluno;
    private CheckBox cboxMusculacao;
    private CheckBox cboxBalletFit;
    private CheckBox cboxMuayThai;
    private CheckBox cboxPilates;
    private EditText edtxtDataAdmissao;
    private Spinner spnProfessorAluno;
    private Button btnListar;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        edtxtAluno = (EditText) findViewById(R.id.edtxtAluno);
        edtxtEnderecoAluno = (EditText) findViewById(R.id.edtxtEnderecoAluno);
        rdgSexo = (RadioGroup) findViewById(R.id.rdgSexo);
        edtxtNascimento = (EditText) findViewById(R.id.edtxtNascimento);
        edtxtCPFAluno = (EditText) findViewById(R.id.edtxtCPFAluno);
        edtxtRGAluno = (EditText) findViewById(R.id.edtxtRGAluno);
        cboxMusculacao = (CheckBox) findViewById(R.id.cboxMusculacao);
        cboxBalletFit = (CheckBox) findViewById(R.id.cboxBalletFit);
        cboxMuayThai = (CheckBox) findViewById(R.id.cboxMuayThai);
        cboxPilates = (CheckBox) findViewById(R.id.cboxPilates);
        edtxtDataAdmissao = (EditText) findViewById(R.id.edtxtDataAdmissao);
        spnProfessorAluno = (Spinner) findViewById(R.id.spnProfessorAluno);

        btnListar = (Button) findViewById(R.id.btnListar);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        ArrayAdapter<String> adapterprofessor = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.professores));
        spnProfessorAluno.setAdapter(adapterprofessor);
    }

    public void salvar(View v){
        AlunoAcademia aluno = criaAluno();

        DAOAcademia daoAcademia = new DAOAcademia();

        if(daoAcademia.salvar(aluno)){
            Toast.makeText(this,"Cadastro Realizado!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Cadastro não Realizado!",Toast.LENGTH_SHORT).show();
        }

        /*
            Intent it = new Intent(this, TelaDadosCadastrados.class);
            it.putExtra("aluno", aluno);
            startActivity(it);
        */
    }

    public void listar(View v){

        DAOAcademia dao = new DAOAcademia();
        ArrayList<AlunoAcademia> alunos = dao.getAlunos();

        Intent it = new Intent(this, TelaDadosCadastrados.class);
        it.putExtra("alunos", alunos);
        startActivity(it);

    }

    private AlunoAcademia criaAluno(){
        AlunoAcademia aluno = new AlunoAcademia();
        aluno.setNome(this.edtxtAluno.getText().toString());
        aluno.setEndereco(this.edtxtEnderecoAluno.getText().toString());
        aluno.setCpf(this.edtxtCPFAluno.getText().toString());
        aluno.setRg(this.edtxtRGAluno.getText().toString());
        aluno.setDataAdmissao(this.edtxtDataAdmissao.getText().toString());
        aluno.setDataNascimento(this.edtxtNascimento.getText().toString());
        aluno.setModalidade(getModalidade());
        aluno.setSexo(getSexo());
        aluno.setProfessor(this.spnProfessorAluno.getSelectedItem().toString());

        return aluno;
    }

    //metodo temporário para simular ManyToMany
    private String getModalidade(){
        String modalidades = "";

        if(this.cboxBalletFit.isChecked()){
            modalidades = modalidades + " BalletFit; ";
        }

        if(this.cboxMuayThai.isChecked()){
            modalidades = modalidades + " MuayThai; ";
        }

        if(this.cboxMusculacao.isChecked()){
            modalidades = modalidades +" Musculação; ";
        }

        if(this.cboxPilates.isChecked()){
            modalidades = modalidades + " Pilates; ";
        }

        return modalidades;
    }

    private String getSexo(){
        int opcaoradio;
        String sexo ="";

        opcaoradio = rdgSexo.getCheckedRadioButtonId();
        switch (opcaoradio) {
            case R.id.rdbtMasculino:
                sexo = "Masculino";
                break;
            case R.id.rdbtbFeminino:
                sexo = "Femenino";
                break;
        }
        return sexo;
    }
}
