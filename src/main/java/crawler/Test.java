package crawler;

import okhttp3.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author candy-wind
 * @Data: 2020-08-11 10:34
 * @Version 1.0
 */
public class Test {


    public static void main(String[] args) throws Exception {
        URL url = new URL(
                "https://img.51credit.com/jifen/css/number.png");//路径换位需要的路径
        // 打开链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置请求方式为"GET"
        conn.setRequestMethod("GET");
        // 超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        // 通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        // 得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
        // new一个文件对象用来保存图片，默认保存当前工程根目录
        File imageFile = new File("/Users/finup/Documents/BeautyGirl.png");
        // 创建输出流
        FileOutputStream outStream = new FileOutputStream(imageFile);
        // 写入数据
        outStream.write(data);
        // 关闭输出流
        outStream.close();
        System.out.println("完成！");


    }


    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }

}
