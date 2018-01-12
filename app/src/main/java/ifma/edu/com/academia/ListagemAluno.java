package ifma.edu.com.academia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.LinearLayout;

import adapters.AlunoAcademiaAdapter;
import dao.DAOAcademia;

public class ListagemAluno extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_aluno);

        recyclerView = (RecyclerView) findViewById(R.id.rcvListagemdeAlunos);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        DAOAcademia dao = new DAOAcademia(this);
        AlunoAcademiaAdapter adapter = new AlunoAcademiaAdapter(this, dao.getAlunos());
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapter);
    }
}
