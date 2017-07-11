package course.labs.modernartui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private TextView rect1;
    private TextView rect2;
    private TextView rect3;
    private TextView rect4;
    private TextView rect5;

    int maximumProgress = 200;
    AlertDialog.Builder builder;

    private String dialogMessage = "Inspired by the works of MoMA artists. \n\nClick below to learn more!";
    private String urlString = "http://www.moma.org";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rect1 = (TextView) findViewById(R.id.rectangle1);
        rect2 = (TextView) findViewById(R.id.rectangle2);
        rect3 = (TextView) findViewById(R.id.rectangle3);
        rect4 = (TextView) findViewById(R.id.rectangle4);
        rect5 = (TextView) findViewById(R.id.rectangle5);

        SeekBar s = (SeekBar) findViewById(R.id.customSeekBar);
        s.setOnSeekBarChangeListener(customSeekBarListener);


    }

    private SeekBar.OnSeekBarChangeListener customSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            rect1.setBackgroundColor(Color.rgb((92 * progress / maximumProgress), (137 * progress / maximumProgress), 255));
            rect2.setBackgroundColor(Color.rgb((238 * progress / maximumProgress), (83 * progress / maximumProgress), 60));
            rect3.setBackgroundColor(Color.rgb((255 * progress / maximumProgress), 255, (255 * progress / maximumProgress)));
            rect4.setBackgroundColor(Color.rgb((242 * progress / maximumProgress), 0, 0));
            rect4.setBackgroundColor(Color.rgb((92 * progress / maximumProgress), 6, 255));

            if (progress == 0) {
                rect1.setBackgroundColor(Color.rgb(92, 137, 255));
                rect2.setBackgroundColor(Color.rgb(238, 83, 255));
                rect3.setBackgroundColor(Color.rgb(255, 7, 35));
                rect4.setBackgroundColor(Color.rgb(242, 235, 255));
                rect5.setBackgroundColor(Color.rgb(75, 4, 255));
            }


        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main , menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            builder = new AlertDialog.Builder(MainActivity.this, AlertDialog.THEME_HOLO_DARK);

            builder.setNegativeButton("Not Now?", onClick); // Not now button
            builder.setPositiveButton("Visit MoMA", onClick); // Visit button which launches browser to visit moma site.
            builder.setMessage(dialogMessage); // custom message


            AlertDialog dialog = builder.show();

            // Justifies the custom message as CENTERED.
            TextView alertMessage = (TextView) dialog.findViewById(android.R.id.message);
            alertMessage.setGravity(Gravity.CENTER);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    DialogInterface.OnClickListener onClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_NEGATIVE) {
                dialog.cancel();
            } else if (which == DialogInterface.BUTTON_POSITIVE) {

                Intent baseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                Intent chooserIntent = Intent.createChooser(baseIntent, "Load " + urlString + " with: ");
                startActivity(chooserIntent);
            }
        }
    };

}