package course.labs.intentslab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExplicityLoadedActivity extends Activity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explicity_loaded_activity);

        mEditText = (EditText) findViewById(R.id.editText);

        Button enter = (Button) findViewById(R.id.button1);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterclicked();
            }
        });
    }

    private void enterclicked()
    {
        String input = mEditText.getText().toString();
        Intent i = new Intent(ExplicityLoadedActivity.this , ActivityLoaderActivity.class);
        i.putExtra("Message" , input);
        setResult(RESULT_OK , i);
        finish();
    }
}
