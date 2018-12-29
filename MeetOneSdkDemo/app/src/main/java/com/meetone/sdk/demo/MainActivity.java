package com.meetone.sdk.demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.meetone.meetlib.MeetOneCallBack;
import com.meetone.meetlib.MeetOneManager;
import com.meetone.meetlib.been.AppInfo;
import com.meetone.meetlib.been.Authorize;
import com.meetone.meetlib.been.CallBack;
import com.meetone.meetlib.been.Signature;

import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static String getUUID(Context context) {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        String identity = preference.getString("identity_android", null);
        if (identity == null) {
            //UUID uuid = new  java.util.UUID(64,64);
            //uuid.randomUUID()
            identity = UUID.randomUUID().toString();
            //identity = java.util.UUID.randomUUID().toString();
            preference.edit().putString("identity_android", identity).commit();
        }
        return identity;
    }

    public void testSignature(View view) {
        MeetOneManager.getInstance().setDebugMode(true);
        AppInfo appInfo = new AppInfo();
        appInfo.setName("moreone");
        appInfo.setIcon("app icon");
        appInfo.setDappCallbackScheme("moreone");
        appInfo.setDappRedirectURL("http://www.baidu.com");
        appInfo.setDescription("this is a demo");
        appInfo.setVersion("2.4.0");
        appInfo.setUuID(getUUID(this));

        Signature signature = new Signature();
        signature.setAccountName("nosuchmethod");
        signature.setData("moreone");
        signature.setDescription("this is a demo");
        MeetOneManager.getInstance().requestSignature(this, signature, appInfo, new MeetOneCallBack() {
            @Override
            public void callBack(Map<String, Object> paramMap, CallBack callBack) {
                Log.d("MeetOneManager", paramMap.toString());
            }
        });
    }


    public void testAuthorize(View view) {
        MeetOneManager.getInstance().setDebugMode(true);
        AppInfo appInfo = new AppInfo();
        appInfo.setName("moreone");
        appInfo.setIcon("app icon");
        appInfo.setDappCallbackScheme("moreone");
        appInfo.setDappRedirectURL("http://www.baidu.com");
        appInfo.setDescription("this is a demo");
        appInfo.setVersion("2.4.0");
        appInfo.setUuID(getUUID(this));

        Authorize authorize = new Authorize();
        authorize.setDescription("memo");
        MeetOneManager.getInstance().requestAuthorize(this, authorize, appInfo, new MeetOneCallBack() {
            @Override
            public void callBack(Map<String, Object> paramMap, CallBack callBack) {
                Log.d("MeetOneManager", paramMap.toString());
            }
        });
    }
}
