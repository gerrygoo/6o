package mx.itesm.ggo.bd_room;

import android.arch.persistence.room.Database;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class PrincipalActiv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new DBTask().execute();
    }

    private byte[] createImage(String file) {
        try{
            InputStream ent = getResources().getAssets().open(file);
            Bitmap bm = BitmapFactory.decodeStream(ent);

            int size = bm.getRowBytes() * bm.getHeight();
            ByteBuffer byteBuffer = ByteBuffer.allocate(size);
            bm.copyPixelsFromBuffer(byteBuffer);
            byte[] imageData = byteBuffer.array();
            return imageData;
        }catch (Exception e){
            Log.i("Creating image", "blew up");
        }finally {
            return null;
        }
    }

    private void grabarRegistro() {
        User user = new User();
        user.setCv( "" );
        user.setName("Juan");
        user.setUid("a%4DVY4");
        user.setProjects( "" );
        user.setSubscriptions( "" );

        DataBase db = DataBase.getINSTANCE(this);

        db.userDAO().insert(user);
        int i = db.userDAO().count();
        Log.i("Write done, ", "records: " + i);
    }

    private class DBTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            grabarRegistro();
            return null;
        }
    }

}
