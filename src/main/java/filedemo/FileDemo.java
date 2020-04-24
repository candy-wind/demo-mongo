package filedemo;

import org.jsoup.helper.StringUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author candy-wind
 * @Data: 2020-04-24 10:54
 * @Version 1.0
 */
public class FileDemo {


    /**
     * 创建文件夹
     * eg:
     * D:/train/
     * D:/train
     * 后面加不加 "/"都行
     */
    public static void createFolder(String path) {
        try {
            if (!StringUtil.isBlank(path)) {
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdirs();      //这个方法创建多层目录
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();

        }
    }

    /**
     * 创建文件
     * 需要文件路径存在，否则报异常
     */
    public static void createFile(String fileName) {
        try {
            if (!StringUtil.isBlank(fileName)) {
                File file = new File(fileName);
                if (!file.exists()) {
                    file.createNewFile();       //这个方法创建一个文件夹
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    /**
     * 创建文件夹和文件
     */
    public static void createFile(String path, String fileName) {
        createFolder(path);
        createFile(path + "/" + fileName);
    }

    /**
     * 将文字写入文件
     */
    public static void writeToFile(String path, String fileName, String content) {
        try {
            createFile(path, fileName);
            File file = new File(path + "/" + fileName);
            FileWriter fileWriter = new FileWriter(file, true);

            fileWriter.write(content);
            fileWriter.write("\r\n");

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文字写入文件
     */
    public static void writeToFile(String path, String fileName, String[] content) {
        try {
            createFile(path, fileName);
            File file = new File(path + "/" + fileName);
            FileWriter fileWriter = new FileWriter(file, true);
            for (String str : content) {
                fileWriter.write(str);
                fileWriter.write("\r\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
