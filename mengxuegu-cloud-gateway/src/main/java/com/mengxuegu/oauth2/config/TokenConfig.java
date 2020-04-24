package com.mengxuegu.oauth2.config;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.IOException;

/**
 * 使用Redis管理令牌
 * @author ys
 * @date 2020/4/22 15:06
 */
@Configuration
public class TokenConfig {

  public static final String SIGNING_KEY="mengxuegu-key";

  /**
   * JWT加密
   * @return
   */
  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter(){
    JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
    //采用对称加密进行签名令牌 资源服务器也要使用此秘钥来解密 校验令牌合法性
//    jwtAccessTokenConverter.setSigningKey(SIGNING_KEY);
    //采用非对称加密 解密校验令牌合法性
    ClassPathResource classPathResource = new ClassPathResource("publicKey.txt");//获取公钥文件
    String publicKey = null;
    try {
      //读取公钥
      publicKey = IOUtils.toString(classPathResource.getInputStream(), "UTF-8");
    } catch (IOException e) {
      e.printStackTrace();
    }
    jwtAccessTokenConverter.setVerifierKey(publicKey);
    return jwtAccessTokenConverter;
  }


  @Bean
  public TokenStore tokenStore(){
    //通过JWT 管理令牌
    return new JwtTokenStore(jwtAccessTokenConverter());
  }


}
