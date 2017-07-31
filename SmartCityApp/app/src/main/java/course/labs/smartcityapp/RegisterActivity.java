package course.labs.smartcityapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText Email, Password, Name , ConfirmPassword;
    Button Register;
    String NameHolder,PasswordHolder , EmailHolder , ConfirmPasswordHolder;
    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabaseObj;
    Cursor cursor;
    String F_Result = "Not_Found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Register = (Button)findViewById(R.id.buttonRegister);

        Email = (EditText)findViewById(R.id.registerEmail);
        Password = (EditText)findViewById(R.id.registerPassword);
        ConfirmPassword = (EditText)findViewById(R.id.registerconfirmPassword);
        Name = (EditText)findViewById(R.id.registerName);

        sqLiteHelper = new SQLiteHelper(this);

        Register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // Creating SQLite database if dose n't exists
                SQLiteDataBaseBuild();

                // Creating SQLite table if dose n't exists.
                SQLiteTableBuild();

                // Checking EditText is empty or Not.
                CheckEditTextStatus();

                EmptyEditTextAfterDataInsert();


            }
        });

    }

    public void CheckEditTextStatus()
    {
        NameHolder = Name.getText().toString() ;
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        ConfirmPasswordHolder = ConfirmPassword.getText().toString();

        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(EmailHolder);


        if(NameHolder.isEmpty() || EmailHolder.isEmpty() || PasswordHolder.isEmpty() || ConfirmPasswordHolder.isEmpty())
        {
            Toast.makeText(RegisterActivity.this , "All Fields Required" , Toast.LENGTH_LONG).show();
        }
        else if(!PasswordHolder.equals(ConfirmPasswordHolder))
        {
            Toast.makeText(RegisterActivity.this , "Password Does Not Match" , Toast.LENGTH_LONG).show();
        }
        else if(!m.find())
        {
            Toast.makeText(RegisterActivity.this , "Your Email Not Valid" , Toast.LENGTH_LONG).show();
        }
        else
        {
            CheckingEmailAlreadyExistsOrNot();
        }
    }
    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void CheckingEmailAlreadyExistsOrNot()
    {

        // Opening SQLite database write permission.
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

        while (cursor.moveToNext()) {

            if (cursor.isFirst()) {

                cursor.moveToFirst();

                // If Email is already exists then Result variable value set as Email Found.
                F_Result = "Email Found";

                // Closing cursor.
                cursor.close();
            }
        }

        // Calling method to check final result and insert data into SQLite database.
        CheckFinalResult();

    }

    public void CheckFinalResult(){

        // Checking whether email is already exists or not.
        if(F_Result.equalsIgnoreCase("Email Found"))
        {

            // If email is exists then toast msg will display.
            Toast.makeText(RegisterActivity.this,"Email Already Exists",Toast.LENGTH_LONG).show();

        }
        else {

            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();

        }

        F_Result = "Not_Found" ;

    }

    // SQLite table build method.
    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME + "(" + SQLiteHelper.Table_Column_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL, " + SQLiteHelper.Table_Column_1_Name + " VARCHAR, " + SQLiteHelper.Table_Column_2_Email + " VARCHAR, " + SQLiteHelper.Table_Column_3_Password + " VARCHAR);");

    }

    public void InsertDataIntoSQLiteDatabase()
    {
        String SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (name,email,password) VALUES('"+NameHolder+"', '"+EmailHolder+"', '"+PasswordHolder+"');";

        // Executing query.
        sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

        // Closing SQLite database object.
        sqLiteDatabaseObj.close();

        // Printing toast message after done inserting.
        Toast.makeText(RegisterActivity.this,"User Registered Successfully", Toast.LENGTH_LONG).show();

    }
    public void EmptyEditTextAfterDataInsert(){

        Name.getText().clear();

        Email.getText().clear();

        Password.getText().clear();
        ConfirmPassword.getText().clear();

    }


}
