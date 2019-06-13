package demo.core.com.dialogutils;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import demo.core.com.dialogutils.utils.DialogUtils;
import demo.core.com.dialogutils.view.PopupWindowList;

public class MainActivity extends AppCompatActivity {

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
            new DialogUtils().showNoBottomMessage(this, "消息的内容部分", "没有底部按钮");
            return true;
        } else if (id == R.id.action_login) {
            //自定义dialog中的xml
            View view = LinearLayout.inflate(this, R.layout.layout_login, null);
            RelativeLayout layout = view.findViewById(R.id.rlt_selectName);
            ImageView iv = view.findViewById(R.id.iv_select);
            TextView tvName = view.findViewById(R.id.tv_name);

            //popupWindow中的xml
            View popView = LayoutInflater.from(this).inflate(R.layout.layout_popup,
                    null, false);
            popView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            ListView lv = popView.findViewById(R.id.lv);
            List<String> strings = Arrays.asList(new String[]{"张三", "李四", "王五", "二麻子", "老李子","二狗子"});
            lv.setAdapter(new ArrayAdapter(this, R.layout.item_pop, strings));
            PopupWindowList mWindow = new PopupWindowList(MainActivity.this, popView);
            mWindow.setOnDismissListener(() -> {
                iv.animate().rotationBy(-90.f);   //方向朝右
            });
            layout.setOnClickListener(v -> {
                iv.animate().rotationBy(90.f);  //方向朝下
                PopupWindowCompat.showAsDropDown(mWindow, layout, 0, 0, Gravity.START);
            });

            lv.setOnItemClickListener((parent, view1, position, id1) -> {
                String itemStr = (String) parent.getAdapter().getItem(position);
                tvName.setText(itemStr);
                mWindow.dismiss();
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

}
