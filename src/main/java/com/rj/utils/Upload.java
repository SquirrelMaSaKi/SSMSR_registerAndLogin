package com.rj.utils;

import java.io.File;
import java.util.UUID;

public class Upload {
    /**
     * 根据文件后缀名更改名字
     * @param ext 文件后缀名
     */
    public static String newFileName(String ext) {
        String salt = UUID.randomUUID().toString().replace("-", "");
        return salt + "." + ext;
    }

    public static String newPath(String realPath, String filename) {
        int h = filename.hashCode();
        int dir1 = h&0xf;
        int dir2 = (h>>4)&0xf;
        String newPath = realPath + File.separator + dir1 + File.separator + dir2;
        File file = new File(newPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return newPath;
    }
}
