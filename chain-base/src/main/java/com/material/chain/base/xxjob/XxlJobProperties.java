package com.material.chain.base.xxjob;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * xxl-job配置
 */
@Data
@ConfigurationProperties(prefix = "xxl.job")
public class XxlJobProperties implements InitializingBean, EnvironmentAware {
	private boolean enabled=true;
	private Environment environment;

	private XxlAdminProperties admin = new XxlAdminProperties();

	private XxlExecutorProperties executor = new XxlExecutorProperties();

	@Override
	public void afterPropertiesSet() {
		// 若是没有设置appname 则取 application Name
		if (!StringUtils.hasText(executor.getAppname())) {
			executor.setAppname(environment.getProperty("spring.application.name"));
		}
	}

	/**
	 * Set the {@code Environment} that this component runs in.
	 */
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
