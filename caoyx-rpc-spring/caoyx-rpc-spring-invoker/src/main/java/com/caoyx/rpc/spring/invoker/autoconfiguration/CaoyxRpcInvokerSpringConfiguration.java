package com.caoyx.rpc.spring.invoker.autoconfiguration;

import com.caoyx.rpc.spring.invoker.CaoyxRpcSpringInvokerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: caoyixiong
 * @Date: 2019-12-26 15:09
 */
@Slf4j
@Configuration
public class CaoyxRpcInvokerSpringConfiguration {

    @ConditionalOnMissingBean(CaoyxRpcSpringInvokerFactory.class)
    @Bean
    public CaoyxRpcSpringInvokerFactory caoyxRpcSpringInvokerFactory() {
        log.info("caoyxRpcSpringInvoker init");
        return new CaoyxRpcSpringInvokerFactory();
    }
}