/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yingluo.oauth2.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * OAuth2.0授权服务器
 * 参考官方文档：
 * https://docs.spring.io/spring-authorization-server/docs/current/reference/html/getting-started.html
 * https://github.com/spring-projects/spring-security/wiki/OAuth-2.0-Features-Matrix
 * https://github.com/spring-projects/spring-security/wiki/OAuth-2.0-Migration-Guide
 *
 * @author techa03
 * @since 2022/1/28
 *
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OAuth2AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuth2AuthorizationServerApplication.class, args);
	}
}
