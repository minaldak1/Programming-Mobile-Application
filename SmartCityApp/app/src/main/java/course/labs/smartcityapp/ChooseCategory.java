package course.labs.smartcityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChooseCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        Button Sumbit = (Button)findViewById(R.id.submit);
        Sumbit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext() , "Saving & Processing" , Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                startActivity(intent);
            }
        });

        Button Cancel = (Button)findViewById(R.id.catcancel);
        Cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ChooseCategory.this , AfterLoginActivity.class);
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
