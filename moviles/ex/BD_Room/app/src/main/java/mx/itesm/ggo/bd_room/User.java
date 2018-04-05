package mx.itesm.ggo.bd_room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerry on 3/7/18.
 */

@Entity(tableName = "user")
public class User {
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(String subscriptions) {
        this.subscriptions = subscriptions;
    }

    @PrimaryKey()
    @NonNull
    private String uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "projects")
    private String projects;

    @ColumnInfo(name = "cv")
    private String cv;

    @ColumnInfo(name = "subscriptions")
    private String subscriptions;
}