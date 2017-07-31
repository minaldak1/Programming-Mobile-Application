package course.labs.smartcityapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button LogInButton ;
    TextView Registerbutton , forgetbtton;
    EditText Email, Password ;
    CheckBox Showpass;

    SQLiteDatabase sqLiteDatabaseObj;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String TempPassword = "NOT_FOUND" ;
    public static final String UserEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogInButton = (Button)findViewById(R.id.buttonLogin);

        Registerbutton = (TextView)findViewById(R.id.registerbutton);
        forgetbtton = (TextView)findViewById(R.id.forgetbutton);

        Email = (EditText)findViewById(R.id.loginemail);
        Password = (EditText)findViewById(R.id.loginPassword);

        Showpass = (CheckBox)findViewById(R.id.showpassword);

        sqLiteHelper = new SQLiteHelper(this);

        Showpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(!isChecked)
                {
                    Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else
                {
                    Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }

            }
        });

        LogInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String EmailHolder = Email.getText().toString();
                String PasswordHolder = Password.getText().toString();

                if(EmailHolder.isEmpty() || PasswordHolder.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();
                }
                else
                {
                    sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
                    cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

                    while (cursor.moveToNext())
                    {

                        if (cursor.isFirst())
                        {

                            cursor.moveToFirst();

                            // Storing Password associated with entered email.
                            TempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Password));

                            // Closing cursor.
                            cursor.close();
                        }
                    }
                    if(TempPassword.equalsIgnoreCase(PasswordHolder))
                    {
                        Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();

                        // Going to AfterLogin activity after login success message.
                        Intent intent = new Intent(MainActivity.this, AfterLoginActivity.class);

                        startActivity(intent);
                        EmptyEditTextAfterDataInsert();
                    }
                    else if (EmailHolder.isEmpty() || PasswordHolder.isEmpty())
                    {
                        Toast.makeText(MainActivity.this , "Please Enter UserName or Password" , Toast.LENGTH_LONG).show();
                        EmptyEditTextAfterDataInsert();
                    }
                    else {

                        //If any of login EditText empty then this block will be executed.
                        Toast.makeText(MainActivity.this,"UserName or Password.is wrong ,Please  Try Again.",Toast.LENGTH_LONG).show();
                        EmptyEditTextAfterDataInsert();

                    }
                }

            }
        });

        Registerbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgetbtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }
    public void EmptyEditTextAfterDataInsert()
    {


        Email.getText().clear();

        Password.getText().clear();

    }

}
