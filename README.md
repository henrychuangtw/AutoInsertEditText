# AutoInsertEditText
Auto insert text into EditText.<br/>
AutoAddTextWatcher will insert text into EditText at positions you are setted.<br/>
AutoAddTextWatcher will delete text in EditText at positions you are setted, when text length bigger than 1.
<br/><br/>

How to Use
-----------
Add char at positions
```Java
mEditText_creditCard.addTextChangedListener(new AutoAddTextWatcher(mEditText_creditCard,
                "-",
                4, 8, 12));
```

Add and delete chars at positions
```Java
mEditText_multiChars.addTextChangedListener(new AutoAddTextWatcher(mEditText_multiChars,
                "##",
                new int[]{3, 6}));
```

Add char at positions, and callback listener
```Java
mEditText_birthday.addTextChangedListener(new AutoAddTextWatcher(mEditText_birthday,
                "/",
                new TextWatcher() {},
                4, 6));
```

ScreenShot
-----------
![](app/src/main/assets/demo_001.png)
