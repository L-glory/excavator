package com.excavator.admin.configure;

import com.excavator.admin.security.LdapCredentialsMatcher;
import com.excavator.admin.security.LdapRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 安全配置
 *
 * @author Glory
 * @create 2019-07-14 17:14
 **/
@SpringBootConfiguration
public class SecurityConfigure {

   @Bean
   public LdapRealm ldapRealm() {
      LdapRealm ldapRealm = new LdapRealm();
      // 设置密码比较器
      ldapRealm.setCredentialsMatcher(new LdapCredentialsMatcher());
      // 设置缓存
      ldapRealm.setCachingEnabled(Boolean.TRUE);
      ldapRealm.setAuthorizationCachingEnabled(Boolean.TRUE);

      return ldapRealm;
   }

   @Bean
    public DefaultWebSecurityManager securityManager(LdapRealm ldapRealm) {
       return new DefaultWebSecurityManager(ldapRealm);
   }

   @Bean
   public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
       return new LifecycleBeanPostProcessor();
   }

   @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
       ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
       filterFactoryBean.setSecurityManager(securityManager);

       Map<String, String> filterChainMap = new HashMap<>();
       // case1: 会话接口，允许匿名访问
       filterChainMap.put("/v1/sessions", "anon");
       filterChainMap.put("/v1/sessions/*", "anon");

       // 设置拦截规则
       filterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

       return filterFactoryBean;
   }

   @Bean
   public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
      AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
      authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
      return authorizationAttributeSourceAdvisor;
   }

   @Bean
   @ConditionalOnMissingBean
   public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
      DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
      advisorAutoProxyCreator.setProxyTargetClass(true);
      return advisorAutoProxyCreator;
   }


   // 这哥们提供了一个思路，铺抓异常类型，识别认证/授权失败结果
   /**
    * 解决： 无权限页面不跳转 shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized") 无效
    * shiro的源代码ShiroFilterFactoryBean.Java定义的filter必须满足filter instanceof AuthorizationFilter，
    * 只有perms，roles，ssl，rest，port才是属于AuthorizationFilter，而anon，authcBasic，auchc，user是AuthenticationFilter，
    * 所以unauthorizedUrl设置后页面不跳转 Shiro注解模式下，登录失败与没有权限都是通过抛出异常。
    * 并且默认并没有去处理或者捕获这些异常。在SpringMVC下需要配置捕获相应异常来通知用户信息
    * @return
    */
   /*@Bean
   public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
      SimpleMappingExceptionResolver simpleMappingExceptionResolver=new SimpleMappingExceptionResolver();
      Properties properties=new Properties();
      //这里的 /unauthorized 是页面，不是访问的路径
      properties.setProperty("org.apache.shiro.authz.UnauthorizedException","/unauthorized");
      properties.setProperty("org.apache.shiro.authz.UnauthenticatedException","/unauthorized");
      simpleMappingExceptionResolver.setExceptionMappings(properties);
      return simpleMappingExceptionResolver;
   }*/
}
