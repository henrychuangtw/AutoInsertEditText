package tw.henrychuang.lib;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


/**
 * Created by henry.chuang on 2016/5/12.
 */
public class AutoAddTextWatcher implements TextWatcher {
    private CharSequence mBeforeTextChanged;
    private TextWatcher mTextWatcher;
    private int[] mArray_pos;
    private EditText mEditText;
    private String mAppentText;

    public AutoAddTextWatcher(EditText editText, String appendText, int... position){
        this.mEditText = editText;
        this.mAppentText = appendText;
        this.mArray_pos = position.clone();
    }
    public AutoAddTextWatcher(EditText editText, String appendText, TextWatcher textWatcher, int... position){
        this(editText, appendText, position);
        this.mTextWatcher = textWatcher;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mBeforeTextChanged = s.toString();

        if(mTextWatcher != null)
            mTextWatcher.beforeTextChanged(s, start, count, after);

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        for (int i = 0; i < mArray_pos.length; i++) {
            if(((mBeforeTextChanged.length() - mAppentText.length() * i) == (mArray_pos[i] - 1) &&
                    (s.length() - mAppentText.length() * i) == mArray_pos[i])){
                mEditText.append(mAppentText);

                break;
            }

            if(((mBeforeTextChanged.length() - mAppentText.length() * i) == mArray_pos[i] &&
                    (s.length() - mAppentText.length() * i) == (mArray_pos[i] + 1))){
                int idx_start = mArray_pos[i] + mAppentText.length() * i;
                int idx_end = Math.min(idx_start + mAppentText.length(), s.length());

                String sub = mEditText.getText().toString().substring(idx_start,  idx_end);

                if(!sub.equals(mAppentText)){
                    mEditText.getText().insert(s.length() - 1, mAppentText);
                }

                break;
            }

            if(mAppentText.length() > 1 &&
                    (mBeforeTextChanged.length() - mAppentText.length() * i) == (mArray_pos[i] + mAppentText.length()) &&
                    (s.length() - mAppentText.length() * i) == (mArray_pos[i] + mAppentText.length() - 1)){
                int idx_start = mArray_pos[i] + mAppentText.length() * i;
                int idx_end = Math.min(idx_start + mAppentText.length(), s.length());

                mEditText.getText().delete(idx_start, idx_end);

                break;
            }

        }

        if(mTextWatcher != null)
            mTextWatcher.onTextChanged(s, start, before, count);

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(mTextWatcher != null)
            mTextWatcher.afterTextChanged(s);

    }

}
