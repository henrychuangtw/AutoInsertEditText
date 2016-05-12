package tw.henrychuang.autoinsertedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText_creditCard, mEditText_multiChars, mEditText_birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText_creditCard = (EditText)findViewById(R.id.edt_creditCard);
        mEditText_multiChars = (EditText)findViewById(R.id.edt_multiChars);
        mEditText_birthday = (EditText)findViewById(R.id.edt_birthday);

        mEditText_creditCard.addTextChangedListener(new AutoAddTextWatcher(mEditText_creditCard,
                "-",
                4, 8, 12));

        mEditText_multiChars.addTextChangedListener(new AutoAddTextWatcher(mEditText_multiChars,
                "##",
                new int[]{3, 6}));

        mEditText_birthday.addTextChangedListener(new AutoAddTextWatcher(mEditText_birthday,
                "/",
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(s.length() == 10){
                            if(isValidDate("yyyy/MM/dd", s.toString())){
                                mEditText_birthday.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            }else {
                                mEditText_birthday.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.ic_delete, 0);
                            }

                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                },
                4, 6));

    }


    private boolean isValidDate(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return date != null;
    }


}
