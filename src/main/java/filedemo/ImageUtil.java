package filedemo;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;

/**
 * @Author candy-wind
 * @Data: 2020-04-24 11:18
 * @Version 1.0
 */
public class ImageUtil {

    private ImageUtil(){}

    /**
     * 将图片base64
     *
     * @param path 文件路径
     **/
    public static String encodeImgageToBase64(String path) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(path);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * 将图片base64
     *
     **/
    public static String encodeImgageToBase64(byte[] bytes) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    /**
     * 将base64的图片解密，存入path路径下
     *
     * @param imgStr 图片base64后的字符串
     * @param path 目标路径
     **/
    public static boolean decodeBase64ToImgage(String imgStr, String path) {
        if (imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将base64的图片解密，转为二进制数组
     *
     * @param imgStr 图片base64后的字符串
     * @throws IOException
     **/
    public static byte[] decodeBase64ToByte(String imgStr) throws IOException{
        if (imgStr == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        // 解密
        byte[] bytes = decoder.decodeBuffer(imgStr);
        // 处理数据
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {
                bytes[i] += 256;
            }
        }
        return bytes;
    }

    /**
     * 将二进制数据转为Image类型
     *
     * @param bytes 二进制数组
     * @throws IOException
     * */
    public static Image byteToImage(byte[] bytes) throws IOException{
        InputStream buffin = new ByteArrayInputStream(bytes);
        Image image = ImageIO.read(buffin);
        return image;
    }

    /**
     * 指定图片宽度和高度对图片进行压缩（返回压缩后的二进制数组）
     *
     * （如果当前图片的长度/宽度已经小于长度参数，则不对长度/宽度进行压缩）
     *
     * @param src
     *            源图片
     * @param widthdist
     *            压缩后图片的宽度
     * @param heightdist
     *            压缩后图片的高度
     * @throws IOException
     **/
    public static byte[] reduceImg(Image src, int widthdist, int heightdist) throws IOException {
        // 构造一个类型为预定义图像类型之一的 BufferedImage
        BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);
        //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
        //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
        tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(tag, "jpg" , baos);
        return baos.toByteArray();
    }

    /**
     * 将base64的图片decode，再转为Image类型
     *
     * @param imgStr
     *            图片base64后字符串
     * @throws IOException
     */
    public static Image decodeBase64ToImage(String imgStr) throws IOException{
        byte[] bytes = decodeBase64ToByte(imgStr);
        return byteToImage(bytes);
    }

    /**
     * 将base64的图片decode，按宽度和高度压缩，返回图片的二进制流
     *
     * @param imgStr
     *            图片base64后字符串
     * @param widthdist
     *            压缩后图片的宽度
     * @param heightdist
     *            压缩后图片的高度
     * @throws IOException
     */
    public static byte[] decodeBase64ToByte(String imgStr, int widthdist, int heightdist) throws IOException{
        Image image = decodeBase64ToImage(imgStr);
        byte[] bytes = reduceImg(image, widthdist, heightdist);
        return bytes;
    }

    /**
     * 功能：将base64之后的大图压缩成小图后再base64返回
     * （将base64的图片decode，按宽度和高度压缩，再encode）
     *
     * @param imgStr
     *            图片base64后字符串
     * @param widthdist
     *            压缩后图片的宽度
     * @param heightdist
     *            压缩后图片的高度
     * @throws IOException
     */
    public static String decodeToReduceToEncode(String imgStr, int widthdist, int heightdist) throws IOException{
        Image image = decodeBase64ToImage(imgStr);
        byte[] bytes = reduceImg(image, widthdist, heightdist);
        String base64Encode = encodeImgageToBase64(bytes);
        return base64Encode;
    }

    /**
     * 功能：将base64之后的大图压缩成小图后再base64返回（按宽度比例压缩）
     * （将base64的图片decode，按宽度和高度压缩，再encode）
     *
     * @param imgStr
     *            图片base64后字符串
     * @param widthdist
     *            压缩后图片的宽度
     * @throws IOException
     */
    public static String decodeToReduceToEncode(String imgStr, int widthdist) throws IOException{
        Image image = decodeBase64ToImage(imgStr);
        BufferedImage bufferedImg = (BufferedImage)image;
        int imgWidth = bufferedImg.getWidth();
        int imgHeight = bufferedImg.getHeight();
        int heightdist = new BigDecimal((float)widthdist/imgWidth).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(imgHeight)).intValue();
        byte[] bytes = reduceImg(image, widthdist, heightdist);
        String base64Encode = encodeImgageToBase64(bytes);
        return base64Encode;
    }



    public static void main(String[] args) throws IOException {
        //模拟请求数据（base64图片）
        String path = "/Users/finup/Desktop/idcardF2.jpg";
        String base64 = encodeImgageToBase64(path);
        String base64Encode = decodeToReduceToEncode(base64, 500);
        decodeBase64ToImgage(base64Encode, "/Users/finup/Desktop/idcardF2Base64.jpg");
    }
}
