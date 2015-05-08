package ds.practica2.juegopreguntas.database;


import java.io.IOException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

        try
        {
            String sql ="select titulo, idcategoria, respuesta, dificultad, correcta, recurso, tipopregunta from preguntas left JOIN respuestas on preguntas.rowid=idpregunta";

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

    public void updateAcierto(int idPartida, TipoPregunta tipo, int categoria) {

        try
        {
            ContentValues estadisticas = new ContentValues();
            estadisticas.put("idjuego", idPartida);
            estadisticas.put("idcategoria", categoria); // TODO Categoria no controlada
            estadisticas.put("idtipo", 1);
            estadisticas.put("acierto", 1);

            mDb.insert("estadisticas", null, estadisticas);
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
            estadisticas.put("idjuego", idPartida);
            estadisticas.put("idcategoria", categoria); // TODO Categoria no controlada
            estadisticas.put("idtipo", 1);
            estadisticas.put("acierto", 0);

            long resultado = mDb.insert("estadisticas", null, estadisticas);
            Log.d(TAG, "Valor updateFallo: " + resultado );
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public Cursor getIdJuego() {

        try
        {
            String sql = "select count(*) from partidas";

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

    public void addJuego(String tipoJuego) {

        ContentValues juegoContent = new ContentValues();
        juegoContent.put("tipo", tipoJuego);

        long resultado = mDb.insert("partidas", null, juegoContent);
        Log.d(TAG, "Valor updateFallo: " + resultado );

    }
}