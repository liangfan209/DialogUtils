package demo.core.com.dialogutils;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import demo.core.com.dialogutils.utils.DialogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
