package crawler;

import com.alibaba.fastjson.JSONObject;
import okhttp3.Cookie;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author candy-wind
 * @Data: 2020-06-14 14:50
 * @Version 1.0
 */
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = -8195892863997590565L;

    private String source;
    private String customerID;
    private String traceId;
    private List<Cookie> cookieList;
    private boolean isLogin;

    public LoginInfo(String traceId) {
        this.traceId = traceId;
        this.cookieList = new ArrayList<>();
        this.isLogin = false;

    }


    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getCustomerID() {
        return customerID;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    public String getTraceId() {
        return traceId;
    }
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
    public List<Cookie> getCookieList() {
        return cookieList;
    }
    public void setCookieList(List<Cookie> cookieList) {
        this.cookieList = cookieList;
    }
    public boolean isLogin() {
        return isLogin;
    }
    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
}
