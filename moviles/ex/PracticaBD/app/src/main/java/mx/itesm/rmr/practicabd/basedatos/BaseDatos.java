package mx.itesm.rmr.practicabd.basedatos;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by roberto on 05/03/18.
 */

@Database(entities = {User.class}, version = 1)
public abstract class BaseDatos extends RoomDatabase
{
    private static BaseDatos INSTANCE;

    public abstract AlumnoDAO alumnoDAO();

    public static BaseDatos getInstance(Context contexto) {
        if (INSTANCE==null) {
            INSTANCE = Room.databaseBuilder(contexto.getApplicationContext(),
                    BaseDatos.class,
                    "baseDatos")
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
