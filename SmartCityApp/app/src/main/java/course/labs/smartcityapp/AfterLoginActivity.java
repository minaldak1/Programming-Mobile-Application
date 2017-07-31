package course.labs.smartcityapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AfterLoginActivity extends AppCompatActivity {

    ImageView camerabutton;
    int GetRequestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        camerabutton = (ImageView)findViewById(R.id.captureButton);
        camerabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent , GetRequestCode);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GetRequestCode)
        {
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            Intent intent = new Intent(AfterLoginActivity.this , PreviewImageActivity.class);
            intent.putExtra("name" , bitmap);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int logout = item.getItemId();
        int help = item.getItemId();
        if(logout == R.id.menuitem)
        {
            finish();
        }
        else if (help == R.id.help)
        {
            Intent intent = new Intent(AfterLoginActivity.this ,HelpActivity.class );
            startActivity(intent);

        }
        else
        {
            Intent intent = new Intent(AfterLoginActivity.this , AboutUs.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
