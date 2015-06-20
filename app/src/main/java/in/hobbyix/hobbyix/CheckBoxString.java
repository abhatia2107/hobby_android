package in.hobbyix.hobbyix;

import android.widget.CheckBox;

/**
 * Created by Yashdeep Sharma on 06-06-2015.
 */
public class CheckBoxString {

    String code = null;
    String name = null;
    boolean selected = false;

    public CheckBoxString( String name, boolean selected) {
        super();

        this.name = name;
        this.selected = selected;
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