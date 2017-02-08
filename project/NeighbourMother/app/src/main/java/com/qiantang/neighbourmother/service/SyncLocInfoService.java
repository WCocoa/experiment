package com.qiantang.neighbourmother.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.data.UserAddressSubHttp;
import com.qiantang.neighbourmother.business.request.UserAddressSubReq;
import com.qiantang.neighbourmother.business.response.UserInfoResp;
import com.qiantang.neighbourmother.logic.UserContacts;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SyncLocInfoService extends IntentService {
    public static final String FOO_PARAM1="asynData";
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.qiantang.neighbourmother.service.action.FOO";
    private static final String ACTION_BAZ = "com.qiantang.neighbourmother.service.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.qiantang.neighbourmother.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.qiantang.neighbourmother.service.extra.PARAM2";

    public SyncLocInfoService() {
        super("SyncLocInfoService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, SyncLocInfoService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, SyncLocInfoService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        if(param1.equals(FOO_PARAM1)){
            UserInfoResp userInfoResp=UserContacts.getUserInfo(this);
            UserAddressSubReq userAddressSubReq=new UserAddressSubReq();
            userAddressSubReq.setProvince(userInfoResp.getProvince());
            userAddressSubReq.setCity(userInfoResp.getCity());
            userAddressSubReq.setDistrict(userInfoResp.getDistrict());
            userAddressSubReq.setUser_community(userInfoResp.getUser_community());
  new UserAddressSubHttp(this,userAddressSubReq,1);

        }
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }

//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//        }
//    };
}
