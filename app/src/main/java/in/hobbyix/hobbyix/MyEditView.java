package in.hobbyix.hobbyix;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Yashdeep Sharma on 10-06-2015.
 */
public class MyEditView extends EditText {

    public MyEditView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "icomoon.ttf");
            setTypeface(tf);
        }
    }

}