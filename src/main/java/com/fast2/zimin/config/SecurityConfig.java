package com.fast2.zimin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailsService;

    @Autowired
    DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(userDetailsService).passwordEncoder(
				passwordEncoder);
	}

	protected void configureXXX(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        ;
    }
    protected void configure(HttpSecurity http) throws Exception {
		http.headers()
                .xssProtection()
                .frameOptions()
            .and()
                .authorizeRequests()
//				.antMatchers("/system/**").hasRole("ADMIN")
//				.antMatchers("/marketing/**").hasRole("ADMIN")
//				.antMatchers("/deploy/**").hasRole("ADMIN")
//				.antMatchers("/assets/**").permitAll()
                .antMatchers("/**").permitAll()//.access("hasRole('ROLE_USER')") //TEST
//				.antMatchers("/js/**").permitAll()
//				.antMatchers("/css/**").permitAll()
//				.antMatchers("/images/**").permitAll()
//				.antMatchers("/tvod/requestProgramScheduleProcess.do").permitAll()
//				.antMatchers("/serviceflow/streamingMedia.do").permitAll()
                .anyRequest().authenticated()
            /*.and()
                .formLogin()
				.loginPage("/login.do")
				.permitAll()*/
            /*
            */
            /*
            .and()
                .formLogin()
                    .successHandler(savedRequestAwareAuthenticationSuccessHandler())
                    //.loginPage("/login")
                    //.failureUrl("/login?error")
                    .loginProcessingUrl("/login.json")
                    .usernameParameter("userId")
                    .passwordParameter("userPw")
                    //.isCustomLoginPage().passwordParameter("userPw")
                .and()
                .logout()
				.permitAll()*/
            .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied.do")
                .and()
                .rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(1209600)
            .and()
                .csrf().disable()
        ;
	}

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler auth
                = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter("targetUrl");
        return auth;
    }
}