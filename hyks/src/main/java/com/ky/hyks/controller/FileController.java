package com.ky.hyks.controller;

import com.ky.hyks.utils.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @className: FileController
 * @description: TODO
 * @author: Lcj
 * @date: 2020-10-07 09:57
 */
@RestController
@RequestMapping("/ky-supplier/file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    public final static String UPLOAD_PATH_PREFIX = "public/upload/";

    @PostMapping("/uploadFile")
    public String upload(MultipartFile uploadFile, HttpServletRequest request) {
        if (uploadFile.isEmpty()) {
            //返回选择文件提示
            return "请选择上传文件";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        //构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
        logger.info("-----------上传文件保存的路径【" + realPath + "】-----------");
        String format = sdf.format(new Date());
        //存放上传文件的文件夹
        File file = new File(realPath + format);
        logger.info("-----------存放上传文件的文件夹【" + file + "】-----------");
        logger.info("-----------输出文件夹绝对路径 -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径【" + file.getAbsolutePath() + "】-----------");
        if (!file.isDirectory()) {
            //递归生成文件夹
            file.mkdirs();
        }
        //获取原始的名字  original:最初的，起始的  方法是得到原来的文件名在客户机的文件系统名称
        String oldName = uploadFile.getOriginalFilename();
        logger.info("-----------文件原始的名字【" + oldName + "】-----------");
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        logger.info("-----------文件要保存后的新名字【" + newName + "】-----------");
        try {
            //构建真实的文件路径
            File newFile = new File(file.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            uploadFile.transferTo(newFile);
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" + format + newName;
            logger.info("-----------【" + filePath + "】-----------");
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败!";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("files") MultipartFile[] files, @RequestParam String uploadFormId) throws IOException {
        /**
         * MultipartFile类是用来接收前台传过来的文件，常用的方法如下：
         * String getContentType()          //获取文件MIME类型
         * InputStream getInputStream()     //获取文件流
         * String getName()                 //获取表单中文件组件的名字
         * String getOriginalFilename()     //获取上传文件的原名
         * long getSize()                   //获取文件的字节大小，单位byte，除以1024就是kb
         * boolean isEmpty()                //是否为空
         * void transferTo(File dest)       //保存到一个目标文件中。
         */
        String filePath = PathUtil.getClasspath() + "upload/";
//        String filePath = "D://file/";
        String newFilePath = filePath + uploadFormId + "\\/";
        File filee = new File(newFilePath);
        if (!filee.exists()) {
            filee.mkdir();
        }
        for (MultipartFile file : files) { //因为有上传多个文件，所以用的是MultipartFile[]数组，所以要遍历数组保存里面的每一个文件
            // 暂时设置保存在D盘的files目录下
            if (file.getOriginalFilename() == null || file.getOriginalFilename() == "") {
                return "请选择文件";
            }
            System.out.println(" 文件名称： " + file.getOriginalFilename());
            System.out.println(" 文件大小： " + file.getSize() / 1024D + "Kb");
            System.out.println(" 文件类型： " + file.getContentType());
            //在这里执行调用文件保存的方法....
            filePath = newFilePath + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            filePath = "";
            //其他业务代码如插入数据库代码省略.........
        }
        return "添加成功";
    }

    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    public boolean deleteFile(@RequestParam String path ) {
        boolean flag = false;
        String filePath = PathUtil.getClasspath() + "upload/";
        if (path.contains(",")) {
            String[] split = path.split(",");
            for (int i = 0; i < split.length; i++) {
                String newFilePath = filePath + split[i];
                File file = new File(newFilePath);
                // 路径为文件且不为空则进行删除
                if (file.isFile() && file.exists()) {
                    file.delete();
                    flag = true;
                }
            }
        } else {
            String newFilePath = filePath + path;
            File file = new File(newFilePath);
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
                flag = true;
            }
        }
        return flag;
    }
}
