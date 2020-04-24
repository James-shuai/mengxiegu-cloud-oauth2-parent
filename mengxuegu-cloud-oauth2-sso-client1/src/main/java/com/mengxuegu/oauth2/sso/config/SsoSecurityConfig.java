package com.mengxuegu.oauth2.sso.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ys
 * @date 2020/4/23 14:17
 */
@EnableOAuth2Sso //开启单点登录
@Configuration
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter {


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/").permitAll()
      .anyRequest().authenticated()
      .and()
      .logout()
      .logoutSuccessUrl("http://localhost:8090/auth/logout")//请求认证服务器将用户退出
      .and()
      .csrf().disable()
    ;
  }
}
