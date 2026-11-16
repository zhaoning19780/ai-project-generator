package com.gpjy.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CosClientConfig class
 *
 * @author Ning
 * @date 2026/11/16
 * @Description: TODO 腾讯云COS配置
 * @Saying: 又是学习编程痛苦的一天，只要学不死，就往死里学吧！
 */
@Configuration
@ConfigurationProperties(prefix = "cos.client")
@Data
public class CosClientConfig {

    private String host; // 请求域名

    private String secretId; // 秘钥ID

    private String secretKey; // 密钥

    private String region; // 地域

    private String bucket; // 桶名

    @Bean
    public COSClient cosClient(){
        // 初始化用户身份信息(secretId,secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId,secretKey);
        // 设置bucket区域
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 生成cos客户端
        return new COSClient(cred,clientConfig);
    }
}
