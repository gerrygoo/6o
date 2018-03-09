package mx.itesm.ggo.bd_room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by gerry on 3/7/18.
 */

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> get();

    @Query("SELECT * FROM user WHERE uid = :uid")
    User getUserByUid(String uid);

    @Query("SELECT COUNT(*) FROM user")
    int count();

    @Insert
    void insert(User... users);

    @Query("DELETE FROM user")
    void _clear();
}
