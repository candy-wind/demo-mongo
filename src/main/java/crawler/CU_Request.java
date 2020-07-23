package crawler;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author candy-wind
 * @Data: 2020-07-22 14:24
 * @Version 1.0
 */
public class CU_Request {

    private static Random random = new Random();
    private static  String mobile = "";


    public static void main(String[] args) {

        LoginInfo loginInfo =new LoginInfo("1231");
        CU_Request cmRequest = new CU_Request();
        cmRequest.loadCaptchaCode(loginInfo);
        try {
            cmRequest.doSendLoginRandombySms(null,loginInfo);
            BufferedReader bufr1 = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
            System.out.println("输入短信验证码：");
            String smsCode = bufr1.readLine();
            cmRequest.login(loginInfo,smsCode,null);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    private String loadCaptchaCode(LoginInfo loginInfo) {
        try {
            String url = "https://uac.10010.com/portal/mallLogin.jsp";
            Request request1 = new Request.Builder()
                    .url(url)
                    .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                    .header("Accept-Encoding","gzip, deflate, br")
                    .header("Accept-Language","zh-CN,zh;q=0.9")
                    .header("Connection","keep-alive")
                    .get()
                    .build();
            Response response1  = getClient(loginInfo).newCall(request1).execute();
//            System.out.println(response1.body().string());

            url = "https://uac.10010.com/portal/Service/CheckNeedVerify?callback=jQuery1720707694072391392_"+System.currentTimeMillis()+"&userName="+mobile+"&pwdType=02&_="+System.currentTimeMillis();
            Request request2 = new Request.Builder()
                    .url(url)
                    .header("Accept","text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01")
                    .header("Accept-Encoding","gzip, deflate, br")
                    .header("Accept-Language","zh-CN,zh;q=0.9")
                    .header("Connection","keep-alive")
                    .header("Referer","https://uac.10010.com/portal/homeLoginNew")
                    .get()
                    .build();
            Response response2  = getClient(loginInfo).newCall(request2).execute();
            System.out.println(response2.body().string());


            url = "https://uac.10010.com/portal/Service/CheckUsernumber";
            RequestBody formBodyCheck3 = new FormBody.Builder()
                    .add("user_id", mobile)
                    .build();
            Request request3 = new Request.Builder()
                    .url(url)
                    .header("Accept", "application/json, text/javascript, */*; q=0.01")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Referer", "https://uac.10010.com/portal/homeLoginNew")
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("X-Requested-With", "XMLHttpRequest")
                    .post(formBodyCheck3)
                    .build();
            Response response3 =getClient(loginInfo).newCall(request3).execute();
            String content = response3.body().string();
            System.out.println(content);

            url ="https://uac.10010.com/portal/Service/CheckAgreement?callback=jQuery1720707694072391392_"+System.currentTimeMillis()+"&userId="+mobile+"&userType=01&unicomNumber=0&_="+System.currentTimeMillis();
            Request request4 = new Request.Builder()
                    .url(url)
                    .header("Accept","text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01")
                    .header("Accept-Encoding","gzip, deflate, br")
                    .header("Accept-Language","zh-CN,zh;q=0.9")
                    .header("Connection","keep-alive")
                    .header("Referer","https://uac.10010.com/portal/homeLoginNew")
                    .get()
                    .build();
            Response response4  = getClient(loginInfo).newCall(request4).execute();
            System.out.println(response4.body().string());

            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void doSendLoginRandombySms(String captchaCode, LoginInfo loginInfo) {
        try {
            Thread.sleep(random.nextInt(500));
            String url ="https://uac.10010.com/portal/Service/SendMSG?callback=jQuery1720707694072391392_"+System.currentTimeMillis()+"&req_time="+System.currentTimeMillis()+"&mobile="+mobile+"&unicom_number=0&_="+System.currentTimeMillis();
            Request request5 = new Request.Builder()
                    .url(url)
                    .header("Accept","text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01")
                    .header("Accept-Encoding","gzip, deflate, br")
                    .header("Accept-Language","zh-CN,zh;q=0.9")
                    .header("Connection","keep-alive")
                    .header("Referer","https://uac.10010.com/portal/homeLoginNew")
                    .get()
                    .build();
            Response response5  = getClient(loginInfo).newCall(request5).execute();
            System.out.println(response5.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     *
     * @author tlg
     * @throws Exception
     */
    private void login(LoginInfo loginInfo, String smsCode,String p) {
        try {
            String smsp = encryptPassword(smsCode);
            System.out.println(smsp);
            //https://uac.10010.com/portal/Service/MallLogin?callback=jQuery1720707694072391392_1595399146275&req_time=1595405273287&redirectURL=http%3A%2F%2Fwww.10010.com&userName=15776574262&password=0170eed8f00a33cb53e0de4f2416cd77aeb207c304d0ad3e0f53e2f4f9fce081b985a5e84e691a38634fc9b8dda8bd98fa550dad6167d151e7321da83e78580710a9832da4b6fcd1844d4a581cf970097a0f7914e8c7b34d25fa0a62ca2daa320000&pwdType=02&productType=01&redirectType=01&rememberMe=1&agreementFlag=1&unicomNumber=0&_=1595405273330
            String url ="https://uac.10010.com/portal/Service/MallLogin?callback=jQuery1720707694072391392_"+System.currentTimeMillis()+"5&req_time="+System.currentTimeMillis()+"&redirectURL=http%3A%2F%2Fwww.10010.com&userName="+mobile+"&password="+smsp+"&pwdType=02&productType=01&redirectType=01&rememberMe=1&agreementFlag=1&unicomNumber=0&_="+System.currentTimeMillis();
            Request request5 = new Request.Builder()
                    .url(url)
                    .header("Accept","text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01")
                    .header("Accept-Encoding","gzip, deflate, br")
                    .header("Accept-Language","zh-CN,zh;q=0.9")
                    .header("Connection","keep-alive")
                    .header("Referer","https://uac.10010.com/portal/homeLoginNew")
                    .get()
                    .build();
            Response response5  = getClient(loginInfo).newCall(request5).execute();
            System.out.println(response5.body().string());
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private static String encryptPassword(String str) throws Exception {
        try {
            ScriptProcessor pageProcessor = ScriptProcessorBuilder.custom()
                    .scriptFromClassPathFile("js/Rsa-test.js").thread(1).build();
            return (String) pageProcessor.processObject("encrypt", str);
        } catch (Exception e) {

        }
        return null;
    }

    private OkHttpClient getClient(final LoginInfo loginInfo) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(5,1, TimeUnit.SECONDS))
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(25,TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .cookieJar(new CookieJar() {
                    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                        loginInfo.getCookieList().addAll(list);
                    }

                    public List<okhttp3.Cookie> loadForRequest(HttpUrl httpUrl) {
                        return loginInfo.getCookieList();
                    }
                });
        ;
//        if (null != loginInfo.getHttpProxy() && StringUtils.isNotEmpty(loginInfo.getHttpProxy().getIp())) {
//            builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("124.172.232.49",8010)));
//        }

        return builder.build();
    }

}
