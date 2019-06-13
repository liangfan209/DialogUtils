package demo.core.com.dialogutils.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class PopupWindowList extends PopupWindow {

    public PopupWindowList(Context context, View view) {
        super(context);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setOutsideTouchable(true);
        setFocusable(true);
        setClippingEnabled(false); //设置为false能够该view越过父类（也就是dialog)大小的限制
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(view);
    }
}