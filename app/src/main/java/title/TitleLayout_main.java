package title;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pku.codingma.client.R;

/**
 * Created by 不二阿坤 on 2017/12/31.
 */

public class TitleLayout_main extends LinearLayout {               //在布局中引入控件就会调用这个构造函数。
    public TitleLayout_main(Context context, AttributeSet attrs){  //两个参数的构造函数
        super(context,attrs);

        LayoutInflater.from(context).inflate(R.layout.title_main,this);//参数1想要加载的布局ID,第2个参数，
        // 为加载好的布局设置一个父布局，注意这里做了对自定义控件的引用，无需再次说明。
        Button titleBack = (Button) findViewById(R.id.title_back);//定位按钮
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });//为back按钮设置动作

    }

}