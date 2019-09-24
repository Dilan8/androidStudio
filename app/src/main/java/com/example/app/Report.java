package com.example.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report2);
    }
    public void send_click(View v)
    {
        EditText Name = findViewById(R.id.Name);
        EditText Email = findViewById(R.id.Email);
        EditText Subject = findViewById(R.id.Subject);
        EditText message = findViewById(R.id.message);

        if(Name.getText().toString().equals(""))
            Name.setError("Mandatory feild");
        else if (Email.getText().toString().equals(""))
            Email.setError("Mandatory feild");
        else if (Subject.getText().toString().equals(""))
            Subject.setError("Mandatory feild");
        else if (message.getText().toString().equals(""))
            message.setError("Mandatory feild");
        else
        {
            Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mail to:"));
            i.putExtra(Intent.EXTRA_EMAIL, new String[] {"vasandarajdilan64@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, Subject.getText(). toString());
            i.putExtra(Intent.EXTRA_TEXT,  "dear vasandarajdilan, \n"
                    + message.getText().toString() + "\n  regards,"
                    + Email.getText().toString());

            try {
                startActivity(Intent.createChooser(i,"send mail"));
            }catch (android.content.ActivityNotFoundException ex)
            {
                Toast.makeText(this, "no mail app found ", Toast.LENGTH_SHORT).show();
            }
            catch(Exception ex)
            {
                Toast.makeText(this ,"unexpected error" + ex.toString(), Toast.LENGTH_SHORT).show();
            }

        }



    }

}
