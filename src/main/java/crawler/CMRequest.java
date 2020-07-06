package crawler;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author candy-wind
 * @Data: 2020-06-14 14:51
 * @Version 1.0
 */

@Component
public class CMRequest {

    String[] channelIds  = new String[]{"12002","12003","12034"};
    private static Random random = new Random();
    private  String channelId = channelIds[random.nextInt(channelIds.length)];
//    private static  String mobile = "";
    private static  String mobile = "";


    //可以成功的   12002 ---》https://shop.10086.cn/mall_451_459.html?forcelogin=1

    private static String getRandomParams(){
                        //黑龙江，北京，安徽，重庆，福建,广东，广西，甘肃,贵州, 河北，河南,海南，湖北,湖南，吉林，江苏,江西,辽宁,内蒙古，宁夏，青海,上海,四川,山东，山西，陕西
        //天津，新疆 西藏 云南 浙江
        String[] backUrl = new String[]{"http://www.10086.cn/index/hl/index_451_459.html","http://www.10086.cn/index/bj/index_100_100.html"
        ,"http://www.10086.cn/index/ah/index_551_551.html","http://www.10086.cn/index/ah/index_551_550.html","http://www.10086.cn/index/ah/index_551_552.html"
        ,"http://www.10086.cn/index/ah/index_551_553.html","http://www.10086.cn/index/ah/index_551_554.html","http://www.10086.cn/index/ah/index_551_555.html"
        ,"http://www.10086.cn/index/ah/index_551_556.html","http://www.10086.cn/index/ah/index_551_567.html","http://www.10086.cn/index/cq/index_230_230.html","http://www.10086.cn/index/fj/index_591_591.html"
                ,"http://www.10086.cn/index/fj/index_591_592.html","http://www.10086.cn/index/fj/index_591_593.html","http://www.10086.cn/index/fj/index_591_594.html"
                ,"http://www.10086.cn/index/fj/index_591_595.html","http://www.10086.cn/index/fj/index_591_596.html","http://www.10086.cn/index/fj/index_591_597.html"
                ,"http://www.10086.cn/index/fj/index_591_598.html","http://www.10086.cn/index/fj/index_591_599.html"
        ,"http://www.10086.cn/index/gd/index_200_200.html","http://www.10086.cn/index/gd/index_200_660.html","http://www.10086.cn/index/gd/index_200_662.html"
                ,"http://www.10086.cn/index/gd/index_200_663.html","http://www.10086.cn/index/gd/index_200_668.html","http://www.10086.cn/index/gd/index_200_750.html","http://www.10086.cn/index/gd/index_200_751.html"
                ,"http://www.10086.cn/index/gx/index_771_771.html","http://www.10086.cn/index/gs/index_931_931.html"
        ,"http://www.10086.cn/index/gz/index_851_851.html","http://www.10086.cn/index/he/index_311_311.html","http://www.10086.cn/index/ha/index_371_371.html"
        ,"http://www.10086.cn/index/hi/index_898_898.html","http://www.10086.cn/index/hb/index_270_270.html","http://www.10086.cn/index/hb/index_270_718.html"
                ,"http://www.10086.cn/index/hn/index_731_731.html","http://www.10086.cn/index/hb/index_270_710.html","http://www.10086.cn/index/hb/index_270_711.html"
                ,"http://www.10086.cn/index/hb/index_270_712.html","http://www.10086.cn/index/hb/index_270_713.html","http://www.10086.cn/index/hb/index_270_714.html"
                ,"http://www.10086.cn/index/hb/index_270_715.html","http://www.10086.cn/index/hb/index_270_716.html","http://www.10086.cn/index/hb/index_270_717.html"
        ,"http://www.10086.cn/index/jl/index_431_431.html","http://www.10086.cn/index/js/index_250_250.html","http://www.10086.cn/index/jx/index_791_791.html"
        ,"http://www.10086.cn/index/ln/index_240_240.html","http://www.10086.cn/index/nm/index_471_471.html","http://www.10086.cn/index/nx/index_951_951.html"
        ,"http://www.10086.cn/index/qh/index_971_971.html","http://www.10086.cn/index/sh/index_210_210.html","http://www.10086.cn/index/sc/index_280_280.html"
        ,"http://www.10086.cn/index/sd/index_531_531.html","http://www.10086.cn/index/sx/index_351_351.html","http://www.10086.cn/index/sn/index_290_290.html"
        ,"http://www.10086.cn/index/tj/index_220_220.html","http://www.10086.cn/index/xj/index_991_991.html","http://www.10086.cn/index/xz/index_891_891.html"
        ,"http://www.10086.cn/index/yn/index_871_871.html","http://www.10086.cn/index/zj/index_571_571.html"};
        return backUrl[random.nextInt(backUrl.length)];
    }

    public static void main(String[] args) {
//        getRandomParams();
//        System.out.println( getRandomParams());
//        try {
//            System.out.println(encryptPassword("147258"));
//            String password = URLEncoder.encode(encryptPassword("147258"), "utf-8");
//            System.out.println(password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String pass = "147258";
//        String pkey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsgDq4OqxuEisnk2F0EJFmw4xKa5IrcqEYHvqxPs2CHEg2kolhfWA2SjNuGAHxyDDE5MLtOvzuXjBx/5YJtc9zj2xR/0moesS+Vi/xtG1tkVaTCba+TV+Y5C61iyr3FGqr+KOD4/XECu0Xky1W9ZmmaFADmZi7+6gO9wjgVpU9aLcBcw/loHOeJrCqjp7pA98hRJRY+MML8MK15mnC4ebooOva+mJlstW6t/1lghR8WNV8cocxgcHHuXBxgns2MlACQbSdJ8c6Z3RQeRZBzyjfey6JCCfbEKouVrWIUuPphBL3OANfgp0B+QG31bapvePTfXU48TYK0M5kE+8LgbbWQIDAQAB";
//        try {
//            String password = RSAUtil.encryptByPublicKey(pass, pkey);
//            System.out.println(password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        LoginInfo loginInfo =new LoginInfo("1231");
        CMRequest cmRequest = new CMRequest();
        cmRequest.loadCaptchaCode(loginInfo);
        try {
            cmRequest.doSendLoginRandombySms(null,loginInfo);
            BufferedReader bufr1 = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
            System.out.println("输入短信验证码：");
            String smsCode = bufr1.readLine();
//            BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
//            System.out.println("输入密码：");
//            String password = bufr.readLine();
            cmRequest.login(loginInfo,smsCode,null);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String loadCaptchaCode(LoginInfo loginInfo) {
        try {
            String url = "https://login.10086.cn/html/login/login.html";
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response  = getClient(loginInfo).newCall(request).execute();
            response.body().string();


        return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void doSendLoginRandombySms(String captchaCode, LoginInfo loginInfo) {
        try {
            Thread.sleep(random.nextInt(500));
//            List<okhttp3.Cookie> cookieStore = loginInfo.getCookieList();
//            okhttp3.Cookie.Builder builder = new okhttp3.Cookie.Builder().name("CITY_INFO").value("100|10").domain("login.10086.cn").path("/");
//            cookieStore.add(builder.build());

            // 为了拿到sendflag的cookie
           String url = "https://login.10086.cn/loadSendflag.htm?timestamp=";
            Request request1 = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response1 = getClient(loginInfo).newCall(request1).execute();
            response1.body().string();

            url = "https://login.10086.cn/genqr.htm";
            Request request2 = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response2 = getClient(loginInfo).newCall(request2).execute();
            response2.body().string();

            url = "https://login.10086.cn/checkUidAvailable.action";
            RequestBody formBodyCheck = new FormBody.Builder()
                    .build();
            Request request3 = new Request.Builder()
                    .url(url)
                    .header("Referer", "https://login.10086.cn/login.html")
//                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:32.0) Gecko/20100101 Firefox/33.0")
                    .post(formBodyCheck)
                    .build();
            Response response3 =getClient(loginInfo).newCall(request3).execute();
            response3.body().string();

            Thread.sleep(random.nextInt(500));
            // 查看手机号是否需要短信
            url = "https://login.10086.cn/needVerifyCode.htm?accountType=01&account="+mobile+"&timestamp="
                    + System.currentTimeMillis();
            Request request4 = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response4 = getClient(loginInfo).newCall(request4).execute();
            String content = response4.body().string();


            // 检查手机号
            url = "https://login.10086.cn/chkNumberAction.action";
            RequestBody formBodyCheck1 = new FormBody.Builder()
                    .add("userName", mobile)
                    .add("loginMode", "01")
                    .add("channelID", channelId)
                    .build();
            Request request5 = new Request.Builder()
                    .url(url)
                    .header("Accept", "application/json, text/javascript, */*; q=0.01")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Referer", "https://login.10086.cn/login.html")
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .post(formBodyCheck1)
                    .build();
            Response response5 =getClient(loginInfo).newCall(request5).execute();
            content = response5.body().string();
            System.out.println(content);
            Thread.sleep(random.nextInt(500));
            // 检查手机号


            url = "https://login.10086.cn/loadToken.action";
            RequestBody formBodyCheck2 = new FormBody.Builder()
                    .add("userName", mobile)
                    .build();
            Request request6 = new Request.Builder()
                    .url(url)
                    .header("Accept", "application/json, text/javascript, */*; q=0.01")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Referer", "https://login.10086.cn/login.html")
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("X-Requested-With", "XMLHttpRequest")
                    .post(formBodyCheck2)
                    .build();
            Response response6 =getClient(loginInfo).newCall(request6).execute();
            content = response6.body().string();
            System.out.println(content);
            String xaBefore = JSON.parseObject(content).getString("result");
            System.out.println(xaBefore);

            // 发送短信
            Thread.sleep(random.nextInt(500));
            url = "https://login.10086.cn/sendRandomCodeAction.action";
            RequestBody formBodyCheck3 = new FormBody.Builder()
                    .add("userName", mobile)
                    .add("type", "01")
                    .add("channelID", channelId)
                    .build();
            Request request7 = new Request.Builder()
                    .url(url)
                    .header("Accept", "application/json, text/javascript, */*; q=0.01")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Referer", "https://login.10086.cn/login.html")
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("X-Requested-With", "XMLHttpRequest")
                    .header("Xa-before", xaBefore)
                    .post(formBodyCheck3)
                    .build();
            Response response7 =getClient(loginInfo).newCall(request7).execute();
            content = response7.body().string();

            System.out.println(content);
            if (content.trim().equals("0")) {
                //     https://login.10086.cn/sendflag.htm?timestamp=
                url = "https://login.10086.cn/sendflag.htm?timestamp="+System.currentTimeMillis();
                Request request8 = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
                Response response8  = getClient(loginInfo).newCall(request8).execute();
                response8.body().string();

            } else if (content.contains("4005")) {
                String msg = "手机号码有误，请重新输入!";
            } else if (content.contains("1")) {
                String msg = "对不起，短信随机码暂时不能发送，请一分钟以后再试！";
            } else if (content.contains("2")) {
                String msg = "对不起，短信随机码获取达到上限！";
            } else if (content.contains("3")) {
                String msg = "对不起，短信发送次数过于频繁！";
            } else if (content.contains("4")) {
                String msg = "对不起，渠道code不能为空！";
            } else if (content.contains("5")) {
                String msg = "对不起，渠道code异常！";
            } else {
            }
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
            String backurl = getRandomParams();
            System.out.println("adasdasdasdadsadsad"+channelId);
            System.out.println("adasdasdasdadsadsad"+backurl);
            System.out.println("adasdasdasdadsadsad"+URLEncoder.encode(backurl));
            Thread.sleep(2000);
            String pass = "147258";
            String password = encryptPassword(pass);
            String url = "https://login.10086.cn/login.htm";
            RequestBody formBodyCheck2 = new FormBody.Builder()
                    .add("accountType", "01")
                    .add("account", mobile)
                    .add("password", password)
                    .add("pwdType", "01")
                    .add("smsPwd", smsCode)
                    .add("inputCode", "")
                    .add("backUrl", backurl)
                    .add("rememberMe", "0")
                    .add("channelID", channelId)
                    .add("protocol", "https:")
                    .add("loginMode", "01")
                    .add("timestamp", String.valueOf(System.currentTimeMillis()))
                    .build();
            Request request6 = new Request.Builder()
                    .url(url)
                    .header("Accept", "application/json, text/javascript, */*; q=0.01")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                    .header("Referer", "https://login.10086.cn/login.html?channelID="+channelId+"&backUrl=http%3A%2F%2Fwww.10086.cn%2Findex%2Fhl%2Findex_451_459.html")
                    .header("Referer", "https://login.10086.cn/login.html?channelID="+channelId+"&backUrl="+URLEncoder.encode(backurl))
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
                    .header("Host", "login.10086.cn")
                    .header("Origin", "https://login.10086.cn")
                    .header("Connection", "keep-alive")
                    .header("X-Requested-With", "XMLHttpRequest")
                    .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36")
                    .post(formBodyCheck2)
                    .build();
            Response response6 =getClient(loginInfo).newCall(request6).execute();
            String content = response6.body().string();
            System.out.println(content);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private static String encryptPassword(String str) throws Exception {
        try {
            ScriptProcessor pageProcessor = ScriptProcessorBuilder.custom()
                    .scriptFromClassPathFile("js/test-final.js").thread(1).build();
            return (String) pageProcessor.processObject("encrypt", str);
        } catch (Exception e) {

        }
        return null;
    }

    private OkHttpClient getClient(final LoginInfo loginInfo) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(5,1,TimeUnit.SECONDS))
                .connectTimeout(25,TimeUnit.SECONDS)
                .readTimeout(25,TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .cookieJar(new CookieJar() {
                    public void saveFromResponse(HttpUrl httpUrl, List<okhttp3.Cookie> list) {
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
