package com.meetone.sdk.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.meetone.meetlib.MeetOneCallBack;
import com.meetone.meetlib.MeetOneManager;
import com.meetone.meetlib.SimpleWalletCallBack;
import com.meetone.meetlib.been.AppInfo;
import com.meetone.meetlib.been.Authorize;
import com.meetone.meetlib.been.CallBack;
import com.meetone.meetlib.been.Signature;
import com.meetone.meetlib.been.SimpleWalletLogin;
import com.meetone.meetlib.been.SimpleWalletPay;
import com.meetone.meetlib.been.Transaction;
import com.meetone.meetlib.been.Transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.cryptokylin.KylinSDK.KylinManager;
import io.cryptokylin.KylinSDK.been.Callback;
import io.cryptokylin.KylinSDK.been.KylinCallback;
import io.cryptokylin.KylinSDK.been.KylinContract;
import io.cryptokylin.KylinSDK.been.KylinLogin;
import io.cryptokylin.KylinSDK.been.KylinPay;
import io.cryptokylin.KylinSDK.been.KylinSign;

public class MainActivity extends AppCompatActivity {
    /**
     * 要与AndroidManifest.xml中申明的android:scheme="testdemo" 一样
     * testdemo 可自定义
     */
    public static String SCHEME = "testdemo";
    /**
     * 要与AndroidManifest.xml中申明的android:scheme="testkylindemo" 一样
     * testkylindemo 可自定义
     */
    public static String KYLIN_SCHEME = "testkylindemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 所有参数都可自定义
     * @return
     */
    public AppInfo getAppInfo() {
        MeetOneManager.getInstance().setDebugMode(true);
        AppInfo appInfo = new AppInfo();
        appInfo.setName("you_app_name");
        appInfo.setIcon("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3365018759,2226705862&fm=27&gp=0.jpg");
        appInfo.setDappCallbackScheme(SCHEME);
        appInfo.setDappRedirectURL("http://www.baidu.com");
        appInfo.setDescription("this is a demo");
        appInfo.setVersion("2.4.0");
        appInfo.setUuID(CommonUtils.getUUID(this));
        return appInfo;
    }

    /**
     * 获取EOs账号信息
     *
     * @param view
     */
    public void btnGetAccount(View view) {
        String description = "test demo";
        MeetOneManager.getInstance().getAccountInfo(this, description, getAppInfo(), new MeetOneCallBack() {
            @Override
            public void callBack(Map<String, Object> paramMap, CallBack callBack) {
                Log.d("MeetOneManager", paramMap.toString());
            }
        });
    }

    /**
     * 获取签名
     *
     * @param view
     */
    public void testSignature(View view) {
        Signature signature = new Signature();
        signature.setAccountName("aaaaa");
        signature.setFromChainId("可选，没填写默认当前网络链ID");
        signature.setData("raw data");
        signature.setDescription("this is a demo");
        MeetOneManager.getInstance().requestSignature(this, signature, getAppInfo(), new MeetOneCallBack() {
            @Override
            public void callBack(Map<String, Object> paramMap, CallBack callBack) {
                Log.d("MeetOneManager", paramMap.toString());
            }
        });
    }

    /**
     * 获取授权
     *
     * @param view
     */
    public void testAuthorize(View view) {
        Authorize authorize = new Authorize();
        authorize.setDescription("this is a demo");
        MeetOneManager.getInstance().requestAuthorize(this, authorize, getAppInfo(), new MeetOneCallBack() {
            @Override
            public void callBack(Map<String, Object> paramMap, CallBack callBack) {
                Log.d("MeetOneManager", paramMap.toString());
            }
        });
    }

    public void btnTransfer(View view) {
        Transfer transfer = new Transfer();
        transfer.setAmount("0.0001");
        transfer.setDescription("i like");
        transfer.setFrom("johntrump123");//from account name
        transfer.setTo("googlehenlan");//to account name
        transfer.setFromChainId("可选，没填写默认当前网络链ID");
        transfer.setToChainId("可选，没填写默认当前网络链ID");
        transfer.setTokenName("EOS");
        transfer.setTokenContract("eosio.token");
        transfer.setTokenPrecision(4);//set token Precision
        transfer.setMemo("aaaa transfers 12.0 EOS to bbbbb");
        MeetOneManager.getInstance().requestTransfer(this, transfer, getAppInfo(), new MeetOneCallBack() {
            @Override
            public void callBack(Map<String, Object> paramMap, CallBack callBack) {
                Log.d("MeetOneManager", paramMap.toString());
            }
        });
    }

    public void btnTransaction(View view) {
        Transaction transaction = new Transaction();
        transaction.setFromChainId("可选，没填写默认当前网络链ID");
        transaction.setDescription("test demo");
        String action1 = "{\"account\":\"eosio.token\",\"name\":\"transfer\",\"authorization\":[{\"actor\":\"johntrump123\", \"permission\":\"owner\"}],\"data\":{\"from\":\"johntrump123\", \"to\":\"googlehenlan\",\"quantity\":\"0.0001 EOS\",\"memo\":\"test\"}}";
        List<String> actions = new ArrayList<>();
        actions.add(action1);
        transaction.setActions(actions);
        String options = "{\"broadcast\":true}}";
        transaction.setOptions(options);
        MeetOneManager.getInstance().requestTransaction(this, transaction, getAppInfo(), new MeetOneCallBack() {
            @Override
            public void callBack(Map<String, Object> paramMap, CallBack callBack) {
                Log.d("MeetOneManager", paramMap.toString());
            }
        });
    }

    public void btnSimpleWalletLogin(View view) {
        MeetOneManager.getInstance().setDebugMode(true);
        SimpleWalletLogin login = new SimpleWalletLogin();
        login.setDappName("you_app_name");
        login.setDappIcon("https://static.ethte.com/tokens/icon/1/15_MEETONE_eosiomeetone.png");
        login.setUuID(CommonUtils.getUUID(this));
        login.setLoginUrl("可选");
        login.setLoginMemo("login Memo");
        login.setCallback(SCHEME + "://login");
        MeetOneManager.getInstance().requestLogin(this, login, new SimpleWalletCallBack() {
            @Override
            public void callBack(Map<String, Object> paramMap, String callBack) {
                Log.d("MeetOneManager", paramMap.toString());
            }
        });
    }

    public void btnSimpleWalletPay(View view) {
        MeetOneManager.getInstance().setDebugMode(true);
        SimpleWalletPay pay = new SimpleWalletPay();
        pay.setDappName("you_app_name");
        pay.setDappIcon("https://static.ethte.com/tokens/icon/1/15_MEETONE_eosiomeetone.png");
        pay.setFrom("johntrump123");//可选
        pay.setTo("googlehenlan");
        pay.setAmount("0.0001");
        pay.setSymbol("EOS");
        pay.setPrecision(4);
        pay.setContract("eosio.token");
        pay.setDesc("i am test");
        pay.setCallback(SCHEME + "://pay");//自定义回调协议

        MeetOneManager.getInstance().requestPay(this, pay, new SimpleWalletCallBack() {
            @Override
            public void callBack(Map<String, Object> paramMap, String callBack) {
                Log.d("MeetOneManager", paramMap.toString());
            }
        });
    }

    public void testKylinPay(View view) {
        KylinPay kylinPay = new KylinPay();
        kylinPay.setFrom("a账号"); //可选
        kylinPay.setTo("b账号");
        kylinPay.setTokenid("eos");
        kylinPay.setNum("0.0001");
        kylinPay.setMemo("test");
        kylinPay.setMsg("it is pay");
//      kylinPay.setActionid(); 可选
//      kylinPay.setUserid(); 可选
        kylinPay.setDappsymbol("dappone_c391d81c");
//      kylinPay.setAuthorization(""); 可选
        kylinPay.setCb(KYLIN_SCHEME);
        KylinManager.getInstance().requestPay(this, kylinPay, new KylinCallback() {
            @Override
            public void callBack(Map<String, String> paramMap, Callback callBack) {
                Log.d("MeetOneManager", paramMap.toString() + callBack);
            }
        });
    }

    public void testKylinLogin(View view) {
        KylinLogin kylinLogin = new KylinLogin();
        kylinLogin.setTokenid("eos");
        kylinLogin.setDappsymbol("dappone_c391d81c");
//      kylinLogin.setAuthorization("");可选
        kylinLogin.setCb(KYLIN_SCHEME);
        KylinManager.getInstance().requestLogin(this, kylinLogin, new KylinCallback() {
            @Override
            public void callBack(Map<String, String> paramMap, Callback callBack) {
                Log.d("MeetOneManager", paramMap.toString() + callBack);
            }
        });
    }

    public void testKylinSign(View view) {
        KylinSign kylinSign = new KylinSign();
        kylinSign.setTokenid("eos");
        kylinSign.setAccountname("a账号");
//      kylinSign.setCustomdata("");可选
        kylinSign.setMsg("it is sign");
        kylinSign.setDappsymbol("dappone_c391d81c");
//      kylinSign.setAuthorization("");可选
        kylinSign.setCb(KYLIN_SCHEME);
        KylinManager.getInstance().requestSign(this, kylinSign, new KylinCallback() {
            @Override
            public void callBack(Map<String, String> paramMap, Callback callBack) {
                Log.d("MeetOneManager", paramMap.toString() + callBack);
            }
        });
    }

    public void testKylinContract(View view) {
        KylinContract kylinContract = new KylinContract();
        kylinContract.setTokenid("eos");
        kylinContract.setDappsymbol("dappone_c391d81c");
//      kylinContract.setAuthorization("");可选
        kylinContract.setAccount("a账号");
        kylinContract.setAddress("公钥地址");
//      kylinContract.setOptions("");可选
//      kylinContract.setActionid("");可选
        kylinContract.setMsg("it is sign");
        kylinContract.setCb(KYLIN_SCHEME);

        String action1 = "{\"account\":\"eosio.token\",\"name\":\"transfer\",\"authorization\":[{\"actor\":\"a账号\", \"permission\":\"owner\"}],\"data\":{\"from\":\"a账号\", \"to\":\"b账号\",\"quantity\":\"0.0001 EOS\",\"memo\":\"test\"}}";
        List<String> actions = new ArrayList<>();
        actions.add(action1);
        kylinContract.setActions(actions);

        KylinManager.getInstance().requestContract(this, kylinContract, new KylinCallback() {
            @Override
            public void callBack(Map<String, String> paramMap, Callback callBack) {
                Log.d("MeetOneManager", paramMap.toString() + callBack);
            }
        });
    }
}
