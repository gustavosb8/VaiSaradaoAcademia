package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Taynara Garces on 27/11/2017.
 */

public class CriaBD extends SQLiteOpenHelper {

    private static final String DB_NAME = "bdacademia.bd";
    private static final int DB_VERSION = 1;
    private String comandosql;

    public CriaBD(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        comandosql = "CREATE TABLE aluno (" +
                "idAluno    INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nome                    VARCHAR (50)," +
                "endereco                VARCHAR (50)," +
                "sexo                     VARCHAR (20)," +
                "dataNascimento             VARCHAR(20)," +
                "cpf           VARCHAR(20)," +
                "rg          VARCHAR (20)," +
                "modalidade    VARCHAR (200)," +
                "dataAdmissao    VARCHAR(20)," +
                "professorResp    VARCHAR (50))";
        db.execSQL(comandosql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoantiga, int novaversao) {
        comandosql = "DROP TABLE IF EXISTS bdacademia";
        db.execSQL(comandosql);
        onCreate(db);
    }
}
