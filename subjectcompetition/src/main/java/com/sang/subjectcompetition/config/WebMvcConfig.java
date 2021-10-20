 /*
package com.sang.subjectcompetition.config;


import com.sang.subjectcompetition.service.util.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file-upload.image.winUrl}")
    private String winUrl;

    @Value("${file-upload.image.linuxUrl}")
    private String linuxUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String OS = System.getProperty("os.name").toLowerCase();
        String localDirectory;
        if(OS.startsWith("win")){
            localDirectory= "file:"+winUrl;
        }else {
            localDirectory= "file:"+linuxUrl;
        }
        registry.addResourceHandler("/**").addResourceLocations(localDirectory+ File.separator);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
*/
