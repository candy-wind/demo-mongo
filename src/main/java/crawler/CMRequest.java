package crawler;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author candy-wind
 * @Data: 2020-06-14 14:51
 * @Version 1.0
 */

@Component
public class CMRequest {

    static String[] channelIds  = new String[]{"12002","12003","12034"};
    private static Random random = new Random();
    private  String channelId = channelIds[random.nextInt(channelIds.length)];
//    private  String channelId = "12002";
    //13811174026
    private static  String mobile = "";
    private static String pass = "";
    private static String backurlEncry;
    private static String backurl;

    //可以成功的   12002 ---》https://shop.10086.cn/mall_451_459.html?forcelogin=1
        //不能用的 "http://www.10086.cn/index/jx/index_791_791.html" http://www.10086.cn/index/fj/index_591_591.html http://www.10086.cn/index/sn/index_290_290.html
    //,"http://www.10086.cn/index/zj/index_571_579.html"，http://www.10086.cn/index/jl/index_431_431.html
    private static String getRandomParams(){
                        //黑龙江，北京，安徽，重庆，福建,广东，广西，甘肃,贵州, 河北，河南,海南，湖北,湖南，吉林，江苏,江西,辽宁,内蒙古，宁夏，青海,上海,四川,山东，山西，陕西
        //天津，新疆 西藏 云南 浙江
        String[] backUrl = new String[]{"http://www.10086.cn/index/hl/index_451_459.html","http://www.10086.cn/index/bj/index_100_100.html"
        ,"http://www.10086.cn/index/ah/index_551_551.html","http://www.10086.cn/index/ah/index_551_550.html","http://www.10086.cn/index/ah/index_551_552.html"
        ,"http://www.10086.cn/index/ah/index_551_553.html","http://www.10086.cn/index/ah/index_551_554.html","http://www.10086.cn/index/ah/index_551_555.html"
        ,"http://www.10086.cn/index/ah/index_551_556.html","http://www.10086.cn/index/ah/index_551_567.html","http://www.10086.cn/index/cq/index_230_230.html"
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
       ,"http://www.10086.cn/index/js/index_250_250.html"
        ,"http://www.10086.cn/index/ln/index_240_240.html","http://www.10086.cn/index/nm/index_471_471.html","http://www.10086.cn/index/nx/index_951_951.html"
        ,"http://www.10086.cn/index/qh/index_971_971.html","http://www.10086.cn/index/sh/index_210_210.html","http://www.10086.cn/index/sc/index_280_280.html"
        ,"http://www.10086.cn/index/sd/index_531_531.html","http://www.10086.cn/index/sx/index_351_351.html","http://www.10086.cn/index/sn/index_290_290.html"
        ,"http://www.10086.cn/index/tj/index_220_220.html","http://www.10086.cn/index/xj/index_991_991.html","http://www.10086.cn/index/xz/index_891_891.html","http://www.10086.cn/index/xz/index_891_892.html",
                "http://www.10086.cn/index/xz/index_891_893.html","http://www.10086.cn/index/xz/index_891_894.html","http://www.10086.cn/index/xz/index_891_895.html","http://www.10086.cn/index/xz/index_891_896.html"
                ,"http://www.10086.cn/index/xz/index_891_897.html","http://www.10086.cn/index/yn/index_871_691.html","http://www.10086.cn/index/yn/index_871_692.html","http://www.10086.cn/index/yn/index_871_870.html"
                ,"http://www.10086.cn/index/yn/index_871_872.html","http://www.10086.cn/index/yn/index_871_873.html","http://www.10086.cn/index/yn/index_871_874.html","http://www.10086.cn/index/yn/index_871_875.html"
                ,"http://www.10086.cn/index/yn/index_871_877.html","http://www.10086.cn/index/yn/index_871_878.html","http://www.10086.cn/index/yn/index_871_879.html","http://www.10086.cn/index/yn/index_871_883.html"
                ,"http://www.10086.cn/index/yn/index_871_886.html","http://www.10086.cn/index/yn/index_871_887.html","http://www.10086.cn/index/yn/index_871_888.html","http://www.10086.cn/index/zj/index_571_570.html"
                ,"http://www.10086.cn/index/zj/index_571_572.html","http://www.10086.cn/index/zj/index_571_573.html","http://www.10086.cn/index/zj/index_571_574.html","http://www.10086.cn/index/zj/index_571_575.html"
                ,"http://www.10086.cn/index/zj/index_571_576.html","http://www.10086.cn/index/zj/index_571_577.html","http://www.10086.cn/index/zj/index_571_578.html"
                ,"http://www.10086.cn/index/zj/index_571_580.html","http://www.10086.cn/index/sn/index_290_911.html","http://www.10086.cn/index/sn/index_290_912.html","http://www.10086.cn/index/sn/index_290_913.html"
                ,"http://www.10086.cn/index/sn/index_290_914.html","http://www.10086.cn/index/sn/index_290_915.html","http://www.10086.cn/index/sn/index_290_916.html","http://www.10086.cn/index/sn/index_290_917.html"
                ,"http://www.10086.cn/index/sn/index_290_919.html","http://www.10086.cn/index/sn/index_290_910.html","http://www.10086.cn/index/sx/index_351_359.html","http://www.10086.cn/index/sx/index_351_358.html"
                ,"http://www.10086.cn/index/sx/index_351_357.html","http://www.10086.cn/index/sx/index_351_356.html","http://www.10086.cn/index/sx/index_351_355.html","http://www.10086.cn/index/sx/index_351_354.html"
                ,"http://www.10086.cn/index/sx/index_351_353.html","http://www.10086.cn/index/sx/index_351_352.html","http://www.10086.cn/index/sx/index_351_350.html","http://www.10086.cn/index/sx/index_351_349.html"
                ,"http://www.10086.cn/index/sd/index_531_635.html","http://www.10086.cn/index/sd/index_531_633.html","http://www.10086.cn/index/sd/index_531_632.html","http://www.10086.cn/index/sd/index_531_631.html"
                ,"http://www.10086.cn/index/sd/index_531_546.html","http://www.10086.cn/index/sd/index_531_543.html","http://www.10086.cn/index/sd/index_531_538.html","http://www.10086.cn/index/sd/index_531_536.html"
                ,"http://www.10086.cn/index/sd/index_531_535.html","http://www.10086.cn/index/sd/index_531_534.html","http://www.10086.cn/index/sd/index_531_532.html","http://www.10086.cn/index/sd/index_531_533.html"
                ,"http://www.10086.cn/index/sd/index_531_530.html","http://www.10086.cn/index/sc/index_280_841.html"
        ,"http://www.10086.cn/index/yn/index_871_871.html","http://www.10086.cn/index/zj/index_571_571.html"};
        return backUrl[random.nextInt(backUrl.length)];
    }

    private String getRandomShop(){
        String[] backUrl = new String[]{"",""};
        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        try {
            System.out.println(encryptPassword("12331"));
        } catch (Exception e) {
            e.printStackTrace();
        }

//
        LoginInfo loginInfo =new LoginInfo("1231");
        CMRequest cmRequest = new CMRequest();
         backurl = "http://www.10086.cn/index/hl/index_451_459.html";
         backurlEncry = "http%3A%2F%2Fwww.10086.cn%2Findex%2Fhl%2Findex_451_459.html";
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
            String url = "https://login.10086.cn/html/login/login.html";
            Request request1 = new Request.Builder()
                    .url(url)
                    .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                    .header("Accept-Encoding","gzip, deflate, br")
                    .header("Accept-Language","zh-CN,zh;q=0.9")
                    .header("Connection","keep-alive")
                    .header("Referer",backurl)
                    .header("Host","login.10086.cn")
                    .header("Accept-Language","zh-CN,zh;q=0.9")
                    .get()
                    .build();
            Response response1  = getClient(loginInfo).newCall(request1).execute();
            response1.body().string();


        return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void doSendLoginRandombySms(String captchaCode, LoginInfo loginInfo) {
        try {
            Thread.sleep(random.nextInt(500));
            // 为了拿到sendflag的cookie
           String url = "https://login.10086.cn/loadSendflag.htm?timestamp=";
            Request request1 = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response1 = getClient(loginInfo).newCall(request1).execute();
            response1.body().string();


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

            url = "https://login.10086.cn/genqr.htm";
            Request request2 = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response2 = getClient(loginInfo).newCall(request2).execute();
            response2.body().string();



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

            String Referer = "https://login.10086.cn/login.html?channelID="+channelId+"&backUrl="+backurlEncry;
            url = "https://login.10086.cn/loadToken.action";
            RequestBody formBodyCheck2 = new FormBody.Builder()
                    .add("userName", mobile)
                    .build();
            Request request6 = new Request.Builder()
                    .url(url)
                    .header("Accept", "application/json, text/javascript, */*; q=0.01")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Referer", Referer)
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
                    .header("Referer", Referer)
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

            String Referer = "https://login.10086.cn/login.html?channelID="+channelId+"&backUrl="+backurlEncry;
            System.out.println("adasdasdasdadsadsad"+Referer);
            System.out.println("adasdasdasdadsadsad"+channelId);
            System.out.println("adasdasdasdadsadsad"+backurl);

            Thread.sleep(2000);
            String password = encryptPassword(pass);
            System.out.println(password);
            String url = "https://login.10086.cn/login.htm";
            RequestBody formBodyCheck2 = new FormBody.Builder()
                    .add("accountType", "01")
                    .add("account", mobile)
                    .add("password", password)
                    .add("pwdType", "01")
                    .add("smsPwd", smsCode)
                    .add("inputCode", "")
                    .add("backUrl", "https://shop.10086.cn/i/")
                    .add("rememberMe", "0")
                    .add("channelID", "12003")
                    .add("protocol", "https:")
                    .add("loginMode", "01")
                    .add("timestamp", String.valueOf(System.currentTimeMillis()))
                    .build();
            Request request6 = new Request.Builder()
                    .url(url)
                    .header("Accept", "application/json, text/javascript, */*; q=0.01")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("Referer", Referer)
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
            List<Cookie> cookieList = loginInfo.getCookieList();
            for (Cookie cookie : cookieList){
                System.out.println(cookie.name()+"="+cookie.value());
            }
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
                .connectTimeout(30,TimeUnit.SECONDS)
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
