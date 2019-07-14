package com.excavator.admin.configure;

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
       return new LdapRealm();
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
}
