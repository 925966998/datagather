package com.ky.redwood.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

@Component
public class TemplateUtils {

    Configuration configuration;

    public void process(String tempName, Map data, String savePath) throws TemplateException, IOException {
        configuration = new Configuration();
        String templatePath = this.getClass().getClassLoader().getResource("").getPath();
        //设置模板所在目录
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        Template template = configuration.getTemplate(tempName);
        OutputStream os = new FileOutputStream(savePath);
        Writer writer = new OutputStreamWriter(os);
        template.process(data, writer);
        //process("templates/" + templateName + ".tpl", data, savePath);
    }
}
