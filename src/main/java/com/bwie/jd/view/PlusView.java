package com.bwie.jd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bwie.jd.R;


/**
 * Created by d on 2017/12/11.
 */

public class PlusView extends LinearLayout {

    private EditText editText;
    private Button add;
    private Button revserse;
    private int mCount = 1;

    public PlusView(Context context) {
        super(context);
    }

    public PlusView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = View.inflate(context, R.layout.layout_plus, null);
        editText = (EditText) view.findViewById(R.id.content);
        add = (Button) view.findViewById(R.id.add);
        revserse = (Button) view.findViewById(R.id.revserse);

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = editText.getText().toString().trim();
                int count = Integer.valueOf(trim)+1;
                mCount = count;
                editText.setText(count+"");
                if(linsenter !=null){
                    linsenter.click(count);
                }
            }
        });


        revserse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = editText.getText().toString().trim();
                int count = Integer.valueOf(trim);
                if(count>1){
                    count--;
                    mCount = count;
                    editText.setText(count+"");
                }
                if(linsenter!=null){
                    linsenter.click(count);
                }

            }
        });

        addView(view);
    }

    public void setEditView(int num){
        if(editText!=null){
            editText.setText(num+"");
        }
    }

    public PlusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    ClickLisenter linsenter;
    public void setLinsenter(ClickLisenter linsenter){
        this.linsenter = linsenter;
    }


    public interface ClickLisenter{
        public void click(int count);
    }
}
