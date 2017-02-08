package com.qiantang.neighbourmother.ui.community;

import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.data.CreateCommHttp;
import com.qiantang.neighbourmother.business.request.CreateCommReq;
import com.qiantang.neighbourmother.model.CommObj;
import com.qiantang.neighbourmother.model.PostObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.ToastUtils;

/**
 * Created by quliang on 16-12-7.
 */

public class PublicCommActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private TextView send;
    private EditText content;
    private PostObj postObj;

    @Override
    public int getContentView() {
        return R.layout.activity_public_comm;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        send = (TextView) findViewById(R.id.send);
        content = (EditText) findViewById(R.id.content);
    }

    @Override
    public void initData() {
        postObj = (PostObj) getIntent().getSerializableExtra(IntentFinal.INTENT_POSTER_DETAIL_OBJ);
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        send.setOnClickListener(this);
    }

    @Override
    protected void updateUI(Message msg) {
        switch (msg.what) {
            case 1:
                closeProgressDialog();
                setResult(10);
                finish();

                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.send:
                String strContent = content.getText().toString().trim();
                if (TextUtils.isEmpty(strContent)) {
                    ToastUtils.toastLong(this, R.string.publiccomm_please_input_content);
                    return;
                }
                new CreateCommHttp(this, handler, new CreateCommReq(postObj.getPost_id(), strContent), 1);
                break;
        }
    }
}
