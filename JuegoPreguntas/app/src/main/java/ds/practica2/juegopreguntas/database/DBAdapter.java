package ds.practica2.juegopreguntas.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;

import ds.practica2.juegopreguntas.preguntas.TipoPregunta;

public class DBAdapter {

    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DBAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DBAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DBAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    /**
     * Crea una consulta de recuperacion de datos
     * @param consulta Consulta sql a realizar
     * @param idError Tag para identificar posible error en el log
     * @return Cursor con el resultado
     */
    private Cursor makeSql(String consulta, String idError){

        try
        {
            String sql =consulta;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                mCur.moveToNext();
            }
            return mCur;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, idError +" >> " + mSQLException.toString());
            throw mSQLException;
        }

    }

    public Cursor getTestData()
    {
        try
        {
            String sql ="SELECT * FROM preguntas";

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                mCur.moveToNext();
            }
            return mCur;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor getPreguntas() {

        return makeSql(DBParams.sqlGetPreguntas, "getPreguntas");
    }

    public void updateAcierto(int idPartida, TipoPregunta tipo, int categoria) {

        try
        {
            ContentValues estadisticas = new ContentValues();
            estadisticas.put(DBParams.getEstadisticasIdjuego(), idPartida);
            estadisticas.put(DBParams.getEstadisticasIdcategoria(), categoria); // TODO Categoria no controlada
            estadisticas.put(DBParams.getEstadisticasIdtipo(), 1);
            estadisticas.put(DBParams.getEstadisticasAcierto(), 1);

            mDb.insert(DBParams.getTableNameEstadisticas(), null, estadisticas);
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }


    }

    public void updateFallo(int idPartida, TipoPregunta tipo, int categoria) {

        try
        {
            ContentValues estadisticas = new ContentValues();
            estadisticas.put(DBParams.getEstadisticasIdjuego(), idPartida);
            estadisticas.put(DBParams.getEstadisticasIdcategoria(), categoria); // TODO Categoria no controlada
            estadisticas.put(DBParams.getEstadisticasIdtipo(), 1);
            estadisticas.put(DBParams.getEstadisticasAcierto(), 0);

            long resultado = mDb.insert(DBParams.getTableNameEstadisticas(), null, estadisticas);
            Log.d(TAG, "Valor updateFallo: " + resultado );
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor getIdJuego() {

        return makeSql(DBParams.sqlGetIDJuego, "getIDJuego");
    }

    public void addJuego(String tipoJuego) {

        ContentValues juegoContent = new ContentValues();
        juegoContent.put("tipo", tipoJuego);

        long resultado = mDb.insert("partidas", null, juegoContent);
        Log.d(TAG, "Valor updateFallo: " + resultado );

    }

    public Cursor getNumPartidas() {

        return makeSql(DBParams.sqlGetNumParditas, "GetNumPartidas");
    }

    public Cursor getNumAciertos() {
        return makeSql(DBParams.sqlGetNumAciertos, "GetNumAciertos");
    }

    public Cursor getNumFallos() {
        return makeSql(DBParams.sqlGetNumFallos, "GetNumFallos");
    }
}