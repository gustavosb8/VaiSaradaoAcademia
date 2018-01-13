package ifma.edu.com.academia;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import dao.DAOAcademia;
import model.AlunoAcademia;

public class TelaEditar extends AppCompatActivity  {

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
    private Button btnEditar;


    AlunoAcademia aluno = (AlunoAcademia) getIntent().getExtras().getSerializable("aluno");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar);

        try{
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

        btnEditar = (Button) findViewById(R.id.btnEditar);

        ArrayAdapter<String> adapterprofessor = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.professores));
        spnProfessorAluno.setAdapter(adapterprofessor);


            criaFormulario(aluno);
        }catch (Exception er){
            Toast.makeText(this,"Erro aqui",Toast.LENGTH_SHORT).show();
        }


    }

    public void editar(){
        AlunoAcademia alunoNovo = criaAluno();
        try{
            DAOAcademia daoAcademia = new DAOAcademia(this);

            if(daoAcademia.editarPorId(aluno.getId(), alunoNovo)){
                Toast.makeText(this,"Edição Realizado!",Toast.LENGTH_SHORT).show();
                setResult(1);
                finish();
            }else{

                Toast.makeText(this,"Edição não Realizado!",Toast.LENGTH_SHORT).show();
                setResult(-1);
                finish();
            }
        }catch (Exception err){
            Toast.makeText(this,err.getMessage(),Toast.LENGTH_SHORT).show();
        }
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

    private void criaFormulario(AlunoAcademia alunoAntigo){
        this.edtxtAluno.setText(alunoAntigo.getNome().toString());
        this.edtxtEnderecoAluno.setText(alunoAntigo.getEndereco().toString());
        this.edtxtCPFAluno.setText(alunoAntigo.getCpf());
        this.edtxtRGAluno.setText(alunoAntigo.getRg().toString());
        this.edtxtDataAdmissao.setText(alunoAntigo.getDataAdmissao().toString());
        this.edtxtNascimento.setText(alunoAntigo.getDataNascimento().toString());
        if(alunoAntigo.getSexo().equals("Masculino")){
            this.rdgSexo.check(R.id.rdbtMasculino);
        }else{
            this.rdgSexo.check(R.id.rdbtbFeminino);
        }
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


