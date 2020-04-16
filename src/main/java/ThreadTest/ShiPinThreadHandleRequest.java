package ThreadTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Author candy-wind
 * @Data: 2020-04-08 10:49
 * @Version 1.0
 */
public class ShiPinThreadHandleRequest implements Callable<JSONObject> {

    private  JSONObject parameter;

    public ShiPinThreadHandleRequest(JSONObject parameter) throws JSONException, IOException {
        this.parameter=parameter;

        try
        {

            String HtmlJson=httpGetRequest(this.parameter.getString("requestUrl"));
            JSONObject object=JSONObject.parseObject(HtmlJson);
            //请求的爱奇艺接口
            if(this.parameter.get("type").equals("shipin"))
            {
                JSONArray returnArray=object.getJSONArray("data");

                if(this.parameter.getString("dataType").equals("firstSegmentData"))
                {

                    JSONArray againArray=new JSONArray();
                    for (int j=0;j<returnArray.size();j++)
                    {
                        JSONArray tempArrray=new JSONArray();
                        tempArrray.add(returnArray.getJSONObject(j));
                        tempArrray.add(returnArray.getJSONObject(j+1));

                        againArray.add(tempArrray);
                        j=j+1;

                    }
                    this.parameter.put("returnData",againArray);
                }
                else
                {
                    this.parameter.put("returnData",returnArray);
                }


            }
            //请求的淘宝客接口
            else
            {

            }

        }
        catch(Exception e)
        {

        }



    }

    //数据回调
    public JSONObject call() throws Exception {


        return this.parameter;
    }

    String httpGetRequest(String urlString1) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(urlString1);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {

            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;

    }

}
