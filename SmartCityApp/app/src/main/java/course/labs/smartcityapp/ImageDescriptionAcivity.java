package course.labs.smartcityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ImageDescriptionAcivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_description_acivity);

        Button next = (Button)findViewById(R.id.decnextbutton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ImageDescriptionAcivity.this , ChooseCategory.class);
                startActivity(intent);

            }
        });

        Button cancel = (Button)findViewById(R.id.decancelbutton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImageDescriptionAcivity.this , AfterLoginActivity.class);
                startActivity(intent);
            }
        });
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
            Intent intent = new Intent(this ,HelpActivity.class );
            startActivity(intent);

        }
        else
        {
            Intent intent = new Intent(this , AboutUs.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
