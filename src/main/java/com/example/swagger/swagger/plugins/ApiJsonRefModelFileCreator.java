package com.example.swagger.swagger.plugins;

import com.example.swagger.swagger.contexts.ApiJsonRefContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.swagger.swagger.config.ApiJsonDocumentationConfiguration.TEMP_CLASS;
import static springfox.documentation.swagger.common.SwaggerPluginSupport.pluginDoesApply;

@Component
public class ApiJsonRefModelFileCreator implements ApiJsonRefModelReplacePlugin {
    private static final Logger LOG = LoggerFactory.getLogger(ApiJsonRefModelFileCreator.class);

    @Override
    public Integer order() {
        return 1;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return pluginDoesApply(delimiter);
    }

    @Override
    public void apply(ApiJsonRefContext context) {
        byte[] code = context.getCode();
        String name = context.getDocumentation().getName();

        File file = new File(TEMP_CLASS + name + ".class");
        LOG.info("apiJsonRefModelFileCreator file path: " + file.getPath());

        try {
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedOutputStream bf = null;
        try{
            bf = new BufferedOutputStream(new FileOutputStream(file));
            bf.write(code);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bf != null){
                try {
                    bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
