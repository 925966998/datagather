package com.ky.ykt.interceptor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DeleteFileSchedul
 * @Description: TODO
 * @Author czw
 * @Date 2020/5/13
 **/
@Component
public class DeleteFileSchedul {
    public static List<String> fileNameList = new ArrayList<String>();

    //每天00：00执行
    @Scheduled(cron = "0 00 00 ? * *")
    public void deleteFile() {
        String property = System.getProperty("user.dir");
        String path = property + "**********\\20200429170147.xls";
        File file = new File(path);
        file.delete();
    }
}
