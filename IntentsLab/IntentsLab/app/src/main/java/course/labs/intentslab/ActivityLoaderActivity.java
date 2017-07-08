package course.labs.intentslab;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityLoaderActivity extends Activity {

    static private final int GET_TEXT_REQUEST_CODE = 1;
    static private final String URL = "http://www.google.com";
    static private final String TAG = "Lab-Intents";

    static private final String CHOOSER_TEXT = "Load " + URL + " with:";

    private TextView mUserTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_activity);

        mUserTextView = (TextView) findViewById(R.id.textView);

        Button explicit = (Button) findViewById(R.id.explicit);
        explicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startExplicitActivation();
            }
        });

        Button implicit = (Button) findViewById(R.id.implicit);
        implicit.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                startImplicitActivation();
            }
        });

    }

    private void startExplicitActivation()
    {
        Intent explicit = new Intent(ActivityLoaderActivity.this , ExplicityLoadedActivity.class);
        startActivityForResult(explicit ,GET_TEXT_REQUEST_CODE );
    }

    private void startImplicitActivation()
    {
        Intent baseIntent = new Intent(Intent.ACTION_VIEW , Uri.parse(URL));
        Intent ChooserIntent = Intent.createChooser(baseIntent , CHOOSER_TEXT);
        startActivity(ChooserIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GET_TEXT_REQUEST_CODE && resultCode == RESULT_OK)
        {
            String output = data.getStringExtra("Message");
            mUserTextView.setText(output);
        }

    }
}
