package mx.itesm.ggo.bd_room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by gerry on 3/7/18.
 */
@Database(entities = {User.class}, version = 1)
public abstract class DataBase extends RoomDatabase{
    private static DataBase INSTANCE;

    public abstract UserDAO userDAO();

    public static DataBase getINSTANCE(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        DataBase.class,
                    "dataBase"
                    )
                    .build();

        }
        return INSTANCE;
    }

    public static void destroyInstance() { INSTANCE = null; }
}
