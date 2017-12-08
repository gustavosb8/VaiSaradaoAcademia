package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Gustavo Bastos on 07/12/2017.
 */

public class DBGateway {
    private static DBGateway DBGateway;
    private SQLiteDatabase bd;

    public DBGateway(Context context){
        CriaBD criaBD = new CriaBD(context);
        this.bd = criaBD.getWritableDatabase();
    }

    public static DBGateway getInstance(Context context){
        if(DBGateway == null){
            DBGateway = new DBGateway(context);
            return DBGateway;
        }
        return DBGateway;
    }

    public SQLiteDatabase getDatabase(){
        return this.bd;
    }
}
