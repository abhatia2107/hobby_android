package in.hobbyix.hobbyix;

import android.widget.CheckBox;

/**
 * Created by Yashdeep Sharma on 06-06-2015.
 */
public class CheckBoxString {

    String code = null;
    String name = null;
    boolean selected = false;

    public CheckBoxString( String name,String code, boolean selected) {
        super();
        this.code=code;
        this.name = name;
        this.selected = selected;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}