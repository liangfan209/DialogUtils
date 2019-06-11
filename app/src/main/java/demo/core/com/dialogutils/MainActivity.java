package demo.core.com.dialogutils;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import demo.core.com.dialogutils.utils.DialogUtils;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    Dialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        tv = findViewById(R.id.tv_hello);
        tv.setOnClickListener(v->{
            TestPopupWindow mWindow = new TestPopupWindow(MainActivity.this);
            View rootView = v.getRootView();
            PopupWindowCompat.showAsDropDown(mWindow, rootView, 100, 100, Gravity.START);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        int id = item.getItemId();
        if (id == R.id.action_normal_msg) {
            new DialogUtils().showNormalMessage(this, "这个是消息内容", "普通消息", new DialogUtils.ClickCallBack() {
                @Override
                public void ok() {
                }
                @Override
                public void cacel() {
                }
            });
            return true;
        } else if (id == R.id.action_no_bottom_msg) {
            new DialogUtils().showNoBottomMessage(this,"消息的内容部分","没有底部按钮");
            return true;
        } else if (id == R.id.action_login) {
            View view = LinearLayout.inflate(this, R.layout.layout_login, null);
            RelativeLayout layout = view.findViewById(R.id.rlt_selectName);
            ImageView iv = view.findViewById(R.id.iv_select);
            layout.setOnClickListener(v->{
                iv.animate().rotationBy(90.f);
                TestPopupWindow mWindow = new TestPopupWindow(MainActivity.this);
                PopupWindowCompat.showAsDropDown(mWindow,layout, 0, 0, Gravity.START);
            });
            mDialog = new DialogUtils().showCustomView(this, "自定义Dialog", view, new DialogUtils.ClickCallBack() {
                @Override
                public void ok() {
                }
                @Override
                public void cacel() {
                }
            });


            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //创建一个已ListView为内容的popwindow



}
