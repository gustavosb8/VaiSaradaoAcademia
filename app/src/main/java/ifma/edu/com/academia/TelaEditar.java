package ifma.edu.com.academia;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import adapters.AlunoAcademiaAdapter;
import dao.DAOAcademia;
import model.AlunoAcademia;
import util.AlunoAcademiaDiffCallBack;

public class TelaEditar extends AppCompatActivity {

    private AlunoAcademia aluno;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar);

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

        ArrayAdapter<String> adapterprofessor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.professores));
        spnProfessorAluno.setAdapter(adapterprofessor);

        aluno = (AlunoAcademia) getIntent().getExtras().getSerializable("aluno");

        criaFormulario(aluno);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar();
            }
        });
    }

    public void editar() {
        DAOAcademia dao = new DAOAcademia(this);
        try {
            if (dao.editarPorId(aluno.getId(), criaAluno())) {
                Toast.makeText(this, "Editado com sucesso", Toast.LENGTH_SHORT).show();

                /*
                Intent it = new Intent(this, ListagemAluno.class);
                startActivity(it);
                */
                EventBus.getDefault().post(dao.getAlunos());
                finish();

            }
        } catch (Exception err) {
            finish();
        }

    }

    private void criaFormulario(AlunoAcademia antigoAluno) {
        edtxtAluno.setText(antigoAluno.getNome());
        edtxtEnderecoAluno.setText(antigoAluno.getEndereco());

        if (antigoAluno.getSexo().equals("Masculino")) {
            rdgSexo.check(R.id.rdbtMasculino);
        } else {
            rdgSexo.check(R.id.rdbtbFeminino);
        }

        edtxtNascimento.setText(antigoAluno.getDataNascimento());
        edtxtCPFAluno.setText(antigoAluno.getCpf());
        edtxtRGAluno.setText(antigoAluno.getRg());
        edtxtDataAdmissao.setText(antigoAluno.getDataAdmissao());
    }

    private AlunoAcademia criaAluno() {
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
    private String getModalidade() {
        String modalidades = "";

        if (this.cboxBalletFit.isChecked()) {
            modalidades = modalidades + " BalletFit; ";
        }

        if (this.cboxMuayThai.isChecked()) {
            modalidades = modalidades + " MuayThai; ";
        }

        if (this.cboxMusculacao.isChecked()) {
            modalidades = modalidades + " Musculação; ";
        }

        if (this.cboxPilates.isChecked()) {
            modalidades = modalidades + " Pilates; ";
        }

        return modalidades;
    }

    private String getSexo() {
        int opcaoradio;
        String sexo = "";

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
