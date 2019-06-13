### 自定义一个Dialog, 自定义一个PopupWindow

##### 先上图，不废话

![image](https://img-blog.csdnimg.cn/20190613131513221.gif)

##### 1.首先构造出自定义的view，inflate xxx.xml,传给自定义的dialog中

```java
View view = LinearLayout.inflate(context, R.layout.dialog, null);
```



##### 2.然后new出一个系统的Dialog，更改它的样式

```java
  /**
     * @param context 上下文
     * @param view  自定义的view
     * @param size  宽度的
     */
    private void initDialog(Context context, View view, float size) {
        mDialog = new Dialog(context, R.style.dialogTheme);
        mDialog.setContentView(view);
        Window window = mDialog.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        //设置这个dialog的宽高
        view.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * size), FrameLayout.LayoutParams.WRAP_CONTENT));
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }
```

##### 3.设置一个dialog的样式

```xml
<style name="dialogTheme" parent="@android:style/Theme.Dialog">
        <!-- 边框 -->
        <item name="android:windowFrame">@null</item>
        <!-- 是否浮现在activity之上 -->
        <item name="android:windowIsFloating">true</item>
        <!-- 半透明 -->
        <item name="android:windowIsTranslucent">true</item>
        <!-- 无标题 -->
        <item name="android:windowNoTitle">true</item>
        <item name="android:background">@android:color/transparent</item>
        <!-- 背景透明 -->
        <item name="android:windowBackground">@android:color/transparent</item>
        <!-- 模糊 -->
        <item name="android:backgroundDimEnabled">true</item>
        <!-- 遮罩层 -->
        <item name="android:backgroundDimAmount">0.5</item>
    </style>
```

##### 4.做到以上3步就可以弹出一个自定义的dialog出来，然后将其show出来

---

### 二、如何自定义PopupWindow

##### 1.和自定义dialog非常类似，首先是要构建出一个view

```java
  View popView = LayoutInflater.from(this).inflate(R.layout.layout_popup,
```

##### 2.构建出一个popupWindow

```java
PopupWindowList mWindow = new PopupWindowList(MainActivity.this, popView);

.....
    
//类对象    
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
```

##### 3.将其展示出来

```java
//构建一个popupWindow 
PopupWindowList mWindow = new PopupWindowList(MainActivity.this, popView);
//在其下方显示
PopupWindowCompat.showAsDropDown(mWindow, layout, 0, 0, Gravity.START);
```



### 三、通过xml画出一个三角形，通过代码动画旋转90度作为动画效果

##### 1.将triangle_selecter.xml中画出箭头朝右的三角形。  让这个xml填充到ImageView的src中

```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:id="@+id/shape_id">
        <rotate
            android:fromDegrees="45"
            android:pivotX="15%"
            android:pivotY="-35%">
            <shape android:shape="rectangle">
                <solid android:color="@color/colorPrimary" />
            </shape>
        </rotate>
    </item>
</layer-list>
```

```xml
 <ImageView
            android:id="@+id/iv_select"
            android:layout_width="18dp"
            android:layout_height="18dp"
            android:layout_alignParentRight="true"
            android:layout_centerVertical="true"
            android:background="@drawable/triangle_selecter" />
```

2.代码实例化出ImageView,然后将其旋转90度

```java
mWindow.setOnDismissListener(() -> {
    iv.animate().rotationBy(-90.f);   //方向朝右
});
layout.setOnClickListener(v -> { //这里的layou是点击的部分
    iv.animate().rotationBy(90.f);  //方向朝下
});
```



