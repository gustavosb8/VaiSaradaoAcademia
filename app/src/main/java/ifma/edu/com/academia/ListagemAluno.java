package ifma.edu.com.academia;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import adapters.AlunoAcademiaAdapter;
import dao.DAOAcademia;
import model.AlunoAcademia;
import util.AlunoAcademiaDiffCallBack;

public class ListagemAluno extends AppCompatActivity {

    private RecyclerView recyclerView;
    AlunoAcademiaAdapter adapter;
    LinearLayoutManager linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_aluno);

        recyclerView = (RecyclerView) findViewById(R.id.rcvListagemdeAlunos);
        linearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        DAOAcademia dao = new DAOAcademia(this);
        adapter = new AlunoAcademiaAdapter(this, dao.getAlunos());
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapter);

        try {
            EventBus.getDefault().register(this);
        }catch (Exception err){
            Toast.makeText(this, "Erro\nEventBus fail in register", Toast.LENGTH_SHORT);
        }

    }

    @Subscribe
    public void atualizaLista(ArrayList<AlunoAcademia> alunos){
        try{
            adapter = new AlunoAcademiaAdapter(this, alunos);
            recyclerView.setLayoutManager(linearLayout);
            recyclerView.setAdapter(adapter);
        }catch (Exception err){
            Toast.makeText(this, "Erro\nEventBus fail in refresh object", Toast.LENGTH_SHORT);
        }

    }
    /*
    public void updateAlunoAcademiaItens(ArrayList<AlunoAcademia> novosAlunos){
        final AlunoAcademiaDiffCallBack diffCallback = new AlunoAcademiaDiffCallBack(this.alunos, novosAlunos);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.alunos.clear();
        this.alunos.addAll(novosAlunos);
        diffResult.dispatchUpdatesTo(this);
    }
    */
}
