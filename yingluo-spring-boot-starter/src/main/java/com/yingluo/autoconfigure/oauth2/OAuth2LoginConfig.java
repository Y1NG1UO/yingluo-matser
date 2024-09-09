package com.yingluo.autoconfigure.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientPropertiesMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 自定义OAuth2授权服务提供方配置
 * 支持gitee码云OAuth2.0授权方式
 *
 * @author techa03
 * @since 2021/3/21
 */
@Configuration(proxyBeanMethods = false)
//@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration")
@EnableConfigurationProperties(OAuth2ClientProperties.class)
public class OAuth2LoginConfig {

    @Value("${spring.security.oauth2.client.registration.yingluo.client-id}")
    private String yingluoClientId;

    @Value("${spring.security.oauth2.client.registration.yingluo.client-secret}")
    private String yingluoClientSecret;

    @Value("${spring.security.oauth2.client.registration.gitee.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.gitee.client-secret}")
    private String clientSecret;

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(OAuth2ClientProperties properties) {
        properties.getRegistration().remove("gitee");
        properties.getRegistration().remove("yingluo");
        OAuth2ClientPropertiesMapper oAuth2ClientPropertiesMapper = new OAuth2ClientPropertiesMapper(properties);
        Collection<ClientRegistration> values = oAuth2ClientPropertiesMapper.asClientRegistrations().values();
        List<ClientRegistration> registrations = new ArrayList<>(values);
        registrations.add(giteeClientRegistration());
        registrations.add(yingluoClientRegistration());
        return new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration giteeClientRegistration() {
        return ClientRegistration.withRegistrationId("gitee")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .scope("user_info")
                .authorizationUri("https://gitee.com/oauth/authorize")
                .tokenUri("https://gitee.com/oauth/token")
                .userInfoUri("https://gitee.com/api/v5/user")
                .userNameAttributeName("id")
                .clientName("Gitee")
                .build();
    }

    private ClientRegistration yingluoClientRegistration() {
        return ClientRegistration.withRegistrationId("yingluo")
                .clientId(yingluoClientId)
                .clientSecret(yingluoClientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .scope("resource:read")
                .authorizationUri("http://www.yingluo.com/oauth/authorize")
                .tokenUri("http://www.yingluo.com/oauth/token")
                .userInfoUri("http://www.yingluo.com/api/v5/user")
                .userNameAttributeName("id")
                .clientName("yingluo")
                .build();
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setyingluoClientId(String yingluoClientId) {
        this.yingluoClientId = yingluoClientId;
    }

    public void setyingluoClientSecret(String yingluoClientSecret) {
        this.yingluoClientSecret = yingluoClientSecret;
    }
}
