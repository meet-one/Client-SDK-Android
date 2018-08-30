Client-SDK-Android    
==============
Android  client SDK for DApps.Will release in MEET.ONE 2.0

# Features
- **Get EOS Account**: DApp can request EOS Authorization for an EOS Account.
- **EOS Transfer**: DApp can send EOS Transferation.
- **Push EOS Transactions**: DApp can push EOS Transactions.

Installation
==============

**1. Copy 'meetlib.aar' to your app lib**  

**2. Reference meetlib library to your app**

> 2.1  Copy the following code to android in your app's build.gradle 
 
    
```
repositories {
    flatDir {
        dirs 'libs'
    }
}
```  



 
    
> 2.2 Copy the following code to dependencies in your app's build.gradle 

    
```
compile(name: 'meetlib', ext: 'aar')
```
 
     
    
**3. Copy code to your AndroidManifest.xml**


```xml
<activity android:name="com.meetone.meetlib.RouterActivity">
    <intent-filter>
    <action android:name="android.intent.action.VIEW" />
    <category android:name="android.intent.category.DEFAULT" />
    <category  android:name="android.intent.category.BROWSABLE" />
    <data android:scheme="Custom Scheme" />
    </intent-filter>
</activity>
```
  
        
**Notice: you need modify "Custom Scheme" to Your DApp's scheme.**
 
# Usage

### 1.Request EOS Authorization

**Code Samples**


```java
Authorize authorize = new Authorize();
authorize.setDescription("get account info ");

AppInfo appInfo = new AppInfo();
appInfo.setName("app name");
appInfo.setIcon("app icon");
appInfo.setDappCallbackScheme("http");
appInfo.setDappRedirectURL("http://www.baidu.com");
appInfo.setDescription("this is a demo");
appInfo.setVersion("app version");
MeetOneManager.getInstance().requestAuthorize(this,authorize,appInfo, new MeetOneCallBack() {
    @Override
    public void callBack(Map<String, String> paramMap, CallBack callBack) {
        Log.d("MeetOneManager",paramMap.toString()+"  "+callBack.getData());
    }
});
```

        
### 2.EOS Transfer

**Code Samples**


```java
AppInfo appInfo = new AppInfo();
appInfo.setName("app name");
appInfo.setIcon("app icon");
appInfo.setDappCallbackScheme("http");
appInfo.setDappRedirectURL("http://www.baidu.com");
appInfo.setDescription("this is a demo");
appInfo.setVersion("app version");

Transfer transfer = new Transfer();
transfer.setAmount("0.0001");
transfer.setDescription("i like");
transfer.setFrom("aaaa");//from account name
transfer.setTo("bbbb");//to account name
transfer.setTokenName("EOS");
transfer.setTokenContract("eosio.token");
transfer.setTokenPrecision(4);//set token Precision
transfer.setMemo("aaaa transfers 12.0 EOS to bbbbb");

MeetOneManager.getInstance().requestTransfer(this, transfer, appInfo, new MeetOneCallBack() {
    @Override
    public void callBack(Map<String, String> paramMap, CallBack callBack) {
        Log.d("MeetOneManager", paramMap.toString() + "  " + callBack.getData());
    }
});
```

        
### 3.Push EOS Transactions

**Code Samples**


```java
AppInfo appInfo = new AppInfo();
appInfo.setName("app name");
appInfo.setIcon("app icon");
appInfo.setDappCallbackScheme("http");
appInfo.setDappRedirectURL("http://www.baidu.com");
appInfo.setDescription("this is a demo");
appInfo.setVersion("app version");


Transaction transaction = new Transaction();
transaction.setDescription("test demo");
String action1 = "{\"account\":\"eosio.token\",\"name\":\"transfer\",\"authorization\":[{\"actor\":\"aaaa\", \"permission\":\"owner\"}],\"data\":{\"from\":\"aaaa\", \"to\":\"bbbb\",\"quantity\":\"0.0001 EOS\",\"memo\":\"test\"}}";
List<String> actions = new ArrayList<>();
actions.add(action1);
transaction.setActions(actions);
String options = "{\"broadcast\":true}}";
transaction.setOptions(options);
MeetOneManager.getInstance().requestTransaction(this, transaction, appInfo, new MeetOneCallBack() {
    @Override
    public void callBack(Map<String, String> paramMap, CallBack callBack) {
        Log.d("MeetOneManager", paramMap.toString() + "  " + callBack.getData());
    }
});
```
