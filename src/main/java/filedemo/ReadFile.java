package filedemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;


/**
 * @Author candy-wind
 * @Data: 2020-04-24 11:05
 * @Version 1.0
 */
public class ReadFile {

    public static void readToFile() throws IOException {
        File file =new File("/Users/finup/Downloads/test2.txt");
        StringBuffer sb = new StringBuffer();
        if(file.exists()){
            byte[] tempbytes = new byte[1024];
            int byteread ;
            InputStream in = null;
            try {
                in = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
//            ReadFromFile1.showAvailableBytes(in);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
                //  System.out.write(tempbytes, 0, byteread);
                String str = new String(tempbytes, 0, byteread);
                sb.append(str);
            }
//            System.out.println(sb);
            JSONObject jsonObject = JSON.parseObject(sb.toString());
            String a = jsonObject.getJSONObject("extra").getString("dataPhoto");
            String dataPhotoTwo = jsonObject.getJSONObject("extra").getString("dataPhotoTwo");
            String photoZ = jsonObject.getJSONObject("extra").getString("photoZ");
//            String base64Encode = decodeToReduceToEncode(dataPhotoTwo, 500);
            byte[] bytes = dataPhotoTwo.getBytes();
            OutputStream out = new FileOutputStream("/Users/finup/Downloads/testzh1.txt");
            out.write(bytes);
            out.flush();
            out.close();
//            System.out.println(a);
//            System.out.println("1212312313");
//            System.out.println(dataPhotoTwo);
//            System.out.println(photoZ);
//            System.out.println("12131231");
        }

    }

    public static void main(String[] args) {
        try {
            readToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
