package demo.core.com.dialogutils.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.core.com.dialogutils.R;


/**
 * 文件名：
 * 描述：自定义的dialog
 * 作者：fan
 * 时间：2019/5/24
 * 版权：
 */
public class DialogUtils {

    private Dialog mDialog;
    private Button sureBt, cancelBt;
    private TextView titleTv;
    private LinearLayout contentLlt, bottomLlt;
    private ImageView closeIv;
    private ClickCallBack mClickCallBack;


    public void showCustomView(Context context, String title, View view, ClickCallBack callBack) {
        initLayoutView(context, callBack);
        if (titleTv != null) {
            titleTv.setText(title);
        }
        if (closeIv != null) {
            closeIv.setVisibility(View.GONE);
        }
        if (contentLlt != null) {
            contentLlt.removeAllViews();
            contentLlt.addView(view);
        }
    }


    /**
     * 普通带有底部确定和取消的msg
     */
    public void showNormalMessage(Context context, String txt, String title, ClickCallBack back) {
        initLayoutView(context, back);
        if (sureBt != null) {
            sureBt.setText("确定");
        }
        if (titleTv != null) {
            if (TextUtils.isEmpty(title)) {
                titleTv.setText("消息提示");
            } else {
                titleTv.setText(title);
            }
        }
        if (closeIv != null) {
            closeIv.setVisibility(View.GONE);
        }
        if (contentLlt != null) {
            contentLlt.removeAllViews();
            TextView tv = new TextView(context);
            ViewGroup.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            ((LinearLayout.LayoutParams) p).setMargins(30, 30, 30, 60);
            tv.setTextSize(13);
            tv.setTextColor(Color.parseColor("#666666"));
            tv.setLayoutParams(p);
            tv.setText(txt);
            contentLlt.addView(tv);
        }
    }

    /**
     * 普通没有底部按钮的message
     */
    public void showNoBottomMessage(Context context, String txt, String title) {
        showNormalMessage(context, txt, title, null);
        if (closeIv != null) {
            closeIv.setVisibility(View.VISIBLE);
            closeIv.setOnClickListener(v -> dissMissLoginDialog());
        }
        if (bottomLlt != null) {
            bottomLlt.removeAllViews();
        }
    }


    private void initDialog(Context context, View view, float size) {
        mDialog = new Dialog(context, R.style.dialogTheme);
        mDialog.setContentView(view);
        Window window = mDialog.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        view.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * size), FrameLayout.LayoutParams.WRAP_CONTENT));
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }


    private void initLayoutView(Context context, ClickCallBack clickCallBack) {
        View view = LinearLayout.inflate(context, R.layout.dialog, null);
        this.mClickCallBack = clickCallBack;
        sureBt = view.findViewById(R.id.bt_login);
        cancelBt = view.findViewById(R.id.bt_cancel);
        closeIv = view.findViewById(R.id.iv_close);
        contentLlt = view.findViewById(R.id.llt_content);
        bottomLlt = view.findViewById(R.id.llt_bottom);
        titleTv = view.findViewById(R.id.tv_msg_title);

        if (mClickCallBack != null) {
            sureBt.setOnClickListener(v -> {
                dissMissLoginDialog();
                mClickCallBack.ok();
            });
            cancelBt.setOnClickListener(v -> {
                dissMissLoginDialog();
                mClickCallBack.cacel();
            });
        } else {
            sureBt.setOnClickListener(v -> {
                dissMissLoginDialog();
            });
        }
        initDialog(context, view, 0.5f);
    }

    public void dissMissLoginDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    public interface ClickCallBack {
        void ok();

        void cacel();
    }
}
