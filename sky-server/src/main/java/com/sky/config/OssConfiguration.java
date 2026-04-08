package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.ConditionalOnMissingFilterBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，用于创建阿里云文件上传的Bean对象
 */
@Configuration
@Slf4j
public class OssConfiguration {
    @Bean
    //创建时，如果容器中已经存在了AliOssProperties对象，则不创建
    @ConditionalOnMissingFilterBean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties) {
        log.info("创建阿里云文件上传客户端：{}", aliOssProperties);
        return new AliOssUtil(aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName());
    }
}
