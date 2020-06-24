package crawler;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import javax.net.ssl.SSLContext;
import javax.swing.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author candy-wind
 * @Data: 2020-06-14 14:51
 * @Version 1.0
 */

@Component
public class CMRequest {

    private  String defaultEncoding = "utf-8";
    private  PoolingHttpClientConnectionManager connManager = null;
//    private  String time;
    private  final int TIME_OUT = 30000;
    private static List<String> channelIds = new ArrayList<>();
    static {
        // channelIds.add("12002");
        // 根据目前的观察，下面两个渠道是可用的
        channelIds.add("12034");
//        channelIds.add("12034");
    }

    private static Random random = new Random();
    private static  String channelId = channelIds.get(random.nextInt(channelIds.size()));
    private  long lastSent = System.currentTimeMillis();
    private static  String mobile = "13709737424";
    private static  String pass = "123123";
    public CMRequest() {
//        time = CalendarUtils.getCurrentTime();
//        init();
    }

    public static void main(String[] args) {
//
//        try {
//            System.out.println(encryptPassword("34343"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        LoginInfo loginInfo =new LoginInfo("1231");
        CMRequest cmRequest = new CMRequest();
        String captcha =cmRequest.loadCaptchaCode(loginInfo);
        System.out.println(captcha);
        try {
//            BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
//            System.out.println("输入图片验证码：");
//            captcha = bufr.readLine();

            cmRequest.doSendLoginRandombySms(captcha,loginInfo);
            BufferedReader bufr1 = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
            System.out.println("输入短信验证码：");
            String smsCode = bufr1.readLine();
            cmRequest.login(loginInfo,smsCode);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public  String loadCaptchaCode(LoginInfo loginInfo) {
        try {

            String url = "https://login.10086.cn/html/login/login.html";
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response  = getClient(loginInfo).newCall(request).execute();
            response.body().string();

            HttpGet get = new HttpGet(url);
            get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            url = "https://login.10086.cn/checkUidAvailable.action";
            Request request1 = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response1  = getClient(loginInfo).newCall(request1).execute();
            String content = response1.body().string();
            System.out.println(content);
//            url = "https://login.10086.cn/captchazh.htm?type=12&timestamp=";
//            HttpGet httpGet = new HttpGet(url);
//            httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//            httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
//            httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
//            httpGet.addHeader("Connection", "keep-alive");
//            httpGet.addHeader("Host", "login.10086.cn");
//            CloseableHttpResponse response = client.execute(httpGet);
//            HttpEntity gzipentity = response.getEntity();
//            Header[] headers = response.getHeaders("Content-Encoding");
//            for (Header h : headers) {
//                if (h.getValue().contains("gzip")) {
//                    gzipentity = new GzipDecompressingEntity(gzipentity);
//                    break;
//                }
//            }
//            ByteArrayOutputStream data = new ByteArrayOutputStream(1024);
//            gzipentity.writeTo(data);
//            response.close();
//            String result="data:image/jpg;base64," + crawler.Base64.encode(data.toByteArray());
//            return result;
        return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void doSendLoginRandombySms(String captchaCode, LoginInfo loginInfo) {
        try {
            Thread.sleep(random.nextInt(500));
            String url = "http://login.10086.cn/login.html";
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response  = getClient(loginInfo).newCall(request).execute();
            response.body().string();
//            List<okhttp3.Cookie> cookieStore = loginInfo.getCookieList();
//            okhttp3.Cookie.Builder builder = new okhttp3.Cookie.Builder().name("CITY_INFO").value("100|10").domain("login.10086.cn").path("/");
//            cookieStore.add(builder.build());



            // 为了拿到sendflag的cookie
            url = "https://login.10086.cn/loadSendflag.htm?timestamp=";
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
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:32.0) Gecko/20100101 Firefox/33.0")
                    .post(formBodyCheck)
                    .build();
            Response response3 =getClient(loginInfo).newCall(request3).execute();
            response3.body().string();

            Thread.sleep(random.nextInt(500));
            // 查看手机号是否需要短信
            url = "https://login.10086.cn/needVerifyCode.htm?accountType=01&account=15776574262&timestamp="
                    + System.currentTimeMillis();
            Request request4 = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response4 = getClient(loginInfo).newCall(request4).execute();
            String content = response4.body().string();

            List<Cookie> cookies = loginInfo.getCookieList();
            for (Cookie cookie:cookies){
                System.out.println(cookie.name() +"="+cookie.value());
            }

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
                url = "https://login.10086.cn/sendflag.htm?timestamp="+System.currentTimeMillis();
                Request request8 = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
                Response response8  = getClient(loginInfo).newCall(request8).execute();
                response8.body().string();
                lastSent = System.currentTimeMillis();
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
    public  void login(LoginInfo loginInfo, String smsCode) {
        try {

            Thread.sleep(2000);
            String password = encryptPassword(pass);
//            password = URLEncoder.encode(password, "utf-8");
            String url = "https://login.10086.cn/login.htm";
            RequestBody formBodyCheck2 = new FormBody.Builder()
                    .add("accountType", "01")
                    .add("account", mobile)
                    .add("password", password)
                    .add("pwdType", "01")
                    .add("smsPwd", smsCode)
                    .add("inputCode", "01")
                    .add("backUrl", "http%3A%2F%2Fwww.10086.cn%2Findex%2Fsd%2Findex_531_535.html%3FWT.mc_id%3DfcUJOwOBpYjgmqogLooj_W9tHmHrZVBOsedSWPWE41592275991.321wm0x124d64o16t6xm0w%3FWT.mc_id%3DfcUJOwOBpYjgmqogLooj_W9tHmHrZVBOsedSWPWE41592275991.321wm0x124d64o16t6xm0w")
                    .add("rememberMe", "0")
                    .add("channelID", channelId)
                    .add("protocol", "https%3A")
                    .add("loginMode", "01")
                    .add("timestamp", String.valueOf(System.currentTimeMillis()))
                    .build();
            Request request6 = new Request.Builder()
                    .url(url)
                    .header("Accept", "application/json, text/javascript, */*; q=0.01")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Referer", "https://login.10086.cn/login.html")
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("Referer", "https://login.10086.cn/login.html?channelID=12003&backUrl=http://shop.10086.cn/i/?f=billdetailqry")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
                    .header("Host", "login.10086.cn")
                    .header("X-Requested-With", "XMLHttpRequest")
                    .post(formBodyCheck2)
                    .build();
            Response response6 =getClient(loginInfo).newCall(request6).execute();
            String content = response6.body().string();



//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//            httpPost.addHeader("Referer", "https://login.10086.cn/login.html?channelID=12003&backUrl=http://shop.10086.cn/i/?f=billdetailqry");
//            httpPost.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
//            httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
//            httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
//            httpPost.addHeader("Host", "login.10086.cn");
//            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//
//           CookieStore cookieStore= loginInfo.getCookieStore();
//            for(Cookie cookie1:cookieStore.getCookies()){
//                System.out.println(cookie1.getName()+"="+cookie1.getValue()+""+cookie1.getDomain()+cookie1.getPath());
//            }
//            CloseableHttpResponse result = client.execute(httpPost);
//            String resp = EntityUtils.toString(result.getEntity(),"utf-8");
            System.out.println(content);
//            result.close();

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


//
////    @PostConstruct
//    public void init(){
//        ConnectionConfig connConfig = ConnectionConfig.custom().setCharset(Charset.forName(defaultEncoding)).build();
//        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(TIME_OUT).build();
//        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory> create();
//        ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
//        registryBuilder.register("http", plainSF);
//        KeyStore trustStore = null;
//        try {
//            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, new AnyTrustStrategy())
//                    .build();
//            LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext,
//                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//
//            registryBuilder.register("https", sslSF);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Registry<ConnectionSocketFactory> registry = registryBuilder.build();
//        // 设置连接管理器
//        if (connManager == null) {
//            connManager = new PoolingHttpClientConnectionManager(registry);
//        }
//        connManager.setDefaultConnectionConfig(connConfig);
//        connManager.setDefaultSocketConfig(socketConfig);
//    }

//
//    private  CloseableHttpClient getHttpClient(LoginInfo loginInfo){
////        HttpProxy httpProxy = loginInfo.getHttpProxy();
////        if(null!=httpProxy&&StringUtils.isNotEmpty(httpProxy.getIp())) {
////            String proxyHost = httpProxy.getIp();
////            int proxyPort = httpProxy.getPort();
//        HttpHost httpHost = new HttpHost("223.247.92.128",4216,"http");
//            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIME_OUT).setConnectTimeout(TIME_OUT)
//                    .setConnectionRequestTimeout(TIME_OUT).setProxy(httpHost).build();
//            DefaultProxyRoutePlanner defaultProxyRoutePlanner = new DefaultProxyRoutePlanner(httpHost);
////            CredentialsProvider defaultCredentialsProvider = new BasicCredentialsProvider();
////            defaultCredentialsProvider.setCredentials(new AuthScope("223.247.92.128", 4216),
////                    new UsernamePasswordCredentials());
//            return HttpClients.custom().setDefaultRequestConfig(requestConfig).setRoutePlanner(defaultProxyRoutePlanner)
//                    .setDefaultCookieStore(loginInfo.getCookieStore())
//                    .setConnectionManager(connManager).setRedirectStrategy(new LaxRedirectStrategy()).build();
//////        }else {
////            return HttpClients.custom().setDefaultCookieStore(loginInfo.getCookieStore()).setConnectionManager(connManager)
////                    .setRedirectStrategy(new LaxRedirectStrategy()).build();
//////        }
//    }
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
