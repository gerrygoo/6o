package mx.itesm.rmr.practicabd.basedatos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by roberto on 05/03/18.
 */

@Dao
public interface AlumnoDAO
{
    @Query("SELECT * FROM user")
    List<User> leerTodos();

    @Query("SELECT * FROM user WHERE uid = :matricula")
    User buscarPorMatricula(int matricula);

    @Query("SELECT * FROM user WHERE name = :nombre")
    User buscarPorNombre(String nombre);

    @Query("SELECT COUNT(*) FROM user")
    int contarAlumnos();

    @Insert
    void insertarAlumnos(User... users);

    @Query("DELETE FROM user")    // CUIDADO!!!
    void borrar();
}
