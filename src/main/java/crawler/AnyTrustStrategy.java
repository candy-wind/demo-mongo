package crawler;

import org.apache.http.conn.ssl.TrustStrategy;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Author candy-wind
 * @Data: 2020-06-14 14:18
 * @Version 1.0
 */
public class AnyTrustStrategy implements TrustStrategy {

    // 绕过安全证书！默认所有都通过
    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        return true;
    }
}
