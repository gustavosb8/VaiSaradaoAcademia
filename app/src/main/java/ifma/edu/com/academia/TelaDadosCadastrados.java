package ifma.edu.com.academia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import model.AlunoAcademia;
import util.Adaptador;

public class TelaDadosCadastrados extends AppCompatActivity {

    private TextView txtNomeAluno;
    private TextView txtEnderecoAluno;
    private TextView txtSexoAluno;
    private TextView txtNascimentoAluno;
    private TextView txtCpfAluno;
    private TextView txtRgAluno;
    private TextView txtModalidadeAluno;
    private TextView txtAdmissaoAluno;
    private TextView txtProfessorAluno;

    private RecyclerView rView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_dados_cadastrados);

        /*
        txtNomeAluno = (TextView) findViewById(R.id.txtMostraNome);
        txtEnderecoAluno = (TextView) findViewById(R.id.txtMostraEndereco);
        txtSexoAluno = (TextView) findViewById(R.id.txtMostraSexo);
        txtNascimentoAluno = (TextView) findViewById(R.id.txtMostraNascimento);
        txtCpfAluno = (TextView) findViewById(R.id.txtMostraCPF);
        txtRgAluno = (TextView) findViewById(R.id.txtMostraRG);
        txtModalidadeAluno = (TextView) findViewById(R.id.txtMostraModalidade);
        txtAdmissaoAluno = (TextView) findViewById(R.id.txtMostraAdmissao);
        txtProfessorAluno = (TextView) findViewById(R.id.txtMostraProfessor);
        */

        rView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        rView.setLayoutManager(mLayoutManager);

        mAdapter = new Adaptador(getAlunoDaIntent());
        rView.setAdapter(mAdapter);

    }

    private void buscaLista(){
        ArrayList<AlunoAcademia> alunos = getAlunoDaIntent();

        AlunoAcademia aluno = alunos.get(0);

        if(aluno != null){
            txtNomeAluno.setText(aluno.getNome());
            txtEnderecoAluno.setText(aluno.getEndereco());
            txtSexoAluno.setText(aluno.getSexo());
            txtNascimentoAluno.setText(aluno.getDataNascimento());
            txtCpfAluno.setText(aluno.getCpf());
            txtRgAluno.setText(aluno.getRg());
            txtModalidadeAluno.setText(aluno.getModalidade());
            txtAdmissaoAluno.setText(aluno.getDataAdmissao());
            txtProfessorAluno.setText(aluno.getProfessor());
        }
        else{
            Toast.makeText(this, "Aluno Não Recebido Por mim! T.T", Toast.LENGTH_LONG).show();
        }

    }

    private ArrayList<AlunoAcademia> getAlunoDaIntent(){
        return (ArrayList<AlunoAcademia>) getIntent().getSerializableExtra("alunos");
    }
}
