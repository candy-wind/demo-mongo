package niukewang;

import java.util.HashMap;

/**
 * @Author candy-wind
 * @Data: 2020-04-22 10:52
 * @Version 1.0
 */


public class IndexOfDemo {

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("we");
        stringBuffer.append(" ");
        stringBuffer.append("we");
        stringBuffer.append(" ");
        stringBuffer.append("we");
        while (stringBuffer.toString().contains(" ")) {

            int index = stringBuffer.toString().indexOf(" ");
            System.out.println(index);
            System.out.println(index+1);
            stringBuffer.replace(index, index + 1, "%20");
        }
        System.out.println(stringBuffer);


        HashMap hashMap =new HashMap();

    }
}
