package com.fast2.zimin.config;

import com.fast2.zimin.util.ApplicationContextProvider;
import com.fast2.zimin.util.MessageSourceAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.web.servlet.view.tiles2.TilesView;

import javax.servlet.ServletContext;
import java.util.Locale;
import java.util.Properties;

@Configuration
@Import(SecurityConfig.class)
@EnableWebMvc
@ComponentScan(basePackages = { "com.fast2.zimin" }, useDefaultFilters = false, includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.stereotype.Controller.class) })
@PropertySource("file:${catalina.home}/conf/zimin-navigator/config.properties")
@EnableTransactionManagement
public class MvcConfig {
	@Autowired
	private Environment env;

	@Autowired
	ServletContext servletContext;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/tiles.xml" });
		// tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	@Bean
	public UrlBasedViewResolver tilesViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		viewResolver.setOrder(1);
		return viewResolver;
	}

	@Bean
	public UrlBasedViewResolver jspViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(2);
		return viewResolver;
	}

	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean() {
		MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
		methodInvokingFactoryBean
				.setStaticMethod("com.fast2.zimin.util.DateUtil.initProperties");
		methodInvokingFactoryBean.setArguments(new Object[] {
				env.getProperty("date.defaultDateTimeFormat"),
				env.getProperty("date.defaultDateFormat"),
				env.getProperty("date.defaultTimeFormat") });
		return methodInvokingFactoryBean;
	}

	// @Bean
	// public LocaleResolver localeResolver() {
	// SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	//
	// String countryCode = env.getProperty("system.country", "");
	// String languageCode = env.getProperty("system.language", "en");
	//
	// localeResolver.setDefaultLocale(new Locale(languageCode, countryCode));
	// // localeResolver.setDefaultLocale(new Locale("vi", "VN"));
	// // localeResolver.setDefaultLocale(Locale.ENGLISH);
	// return localeResolver;
	// }

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		String countryCode = env.getProperty("system.country", "KR");
		String languageCode = env.getProperty("system.language", "ko");
		localeResolver.setCookieName("locale");
		localeResolver.setDefaultLocale(new Locale(languageCode, countryCode));
		localeResolver.setCookieMaxAge(604800); // 1주
		return localeResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasenames(new String[] {
				"classpath:/com/fast2/zimin/i18n/messages",
				"classpath:/com/fast2/zimin/i18n/labels" });
		bundle.setDefaultEncoding("UTF-8");
		return bundle;
	}

	@Bean
	public MessageSourceAccessor wmSource() {
		return new MessageSourceAccessor(messageSource());
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(-1);
		return resolver;
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		// @PreAuthorize("hasRole('ROLE_SYSADMIN')") 위배시
		mappings.put(
				"org.springframework.security.access.AccessDeniedException",
				"common/exception/access_denied_popup");
		resolver.setExceptionMappings(mappings);
		return resolver;
	}

	/*@Bean
	public XmlViewResolver xmlViewResolver() {
		XmlViewResolver xmlViewResolver = new XmlViewResolver();
		// xmlViewResolver.setLocation(new ClassPathResource("excelviews.xml"));
		// xmlViewResolver.setLocation(new
		// ClassPathResource("WEB-INF/excel-views.xml"));
		xmlViewResolver.setLocation(new ServletContextResource(servletContext,
				"/WEB-INF/excel-views.xml"));
		xmlViewResolver.setOrder(1);
		return xmlViewResolver;
	}*/

	@Bean
	public ApplicationContextProvider applicationContextProvider() {
		return new ApplicationContextProvider();
	}
}
