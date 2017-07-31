package course.labs.smartcityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
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
        int about = item.getItemId();
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
