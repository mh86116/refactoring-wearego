package com.greedy.TravelWithGuid.cmmn.config;

import com.greedy.TravelWithGuid.cmmn.util.ExceptionHandlerFilter;
import com.greedy.TravelWithGuid.login.service.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private final LoginService loginService;
    private final DataSource dataSource;

    public SpringSecurityConfig(@Lazy LoginService loginService, DataSource dataSource) {
        this.loginService = loginService;
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/resources/**", "/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //SpringSecurity 사용 시  Csrf 필터 앞에 놓아야함
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        http.csrf();

        http.authorizeRequests()
                .antMatchers("/", "/**").permitAll()
                .antMatchers("/admin/", "/admin/**").hasRole("ADMIN")
                .antMatchers( "/member/", "/member/**").hasRole("MEMBER")
                .antMatchers("/guide/", "/guide/**").hasRole("GUIDE")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .failureUrl("/login?error=true")
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()

                .and()
                .logout()
//                .logoutUrl("/")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)   //세션삭제

                .and()
                .addFilterAfter(new ExceptionHandlerFilter(), SecurityContextHolderAwareRequestFilter.class);
    }

    @Bean /* Thymeleaf 에서 springSecurity 사용 */
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }



}
