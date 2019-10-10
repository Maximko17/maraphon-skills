package com.maraphon.maraphonskills.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${upload.path}")
    private String uploadPath;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SimpleAuthenticationSuccessHandler successHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/runner/**").hasAnyRole("RUNNER", "ADMIN")
                .antMatchers("/coordinator/**").hasAnyRole("COORDINATOR", "ADMIN")
                .antMatchers(HttpMethod.POST, "/saveRunner").permitAll()
                .antMatchers(HttpMethod.POST, "/flushSponsorMoney").permitAll()
                .antMatchers(pages).permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler)
                .permitAll().and()
                .logout()
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/images/**", "/css/**", "/fonts/**", "/js/**", "/error");
    }


    private String[] pages = {
            "/aboutEvent",
            "/marathonCompare",
            "/charityOrgList",
            "/calculatorBMI",
            "/calculatorBMR",
            "/learnMore",
            "/registerAsRunner",
            "/becomeSponsor",
            "/",
            "/allResults",
            "/interactiveMap",
            "/becomeRunner",
            "/index",
            "/sortAllRunners",
            "/calcBMR",
            "/calcBMI",
            "/img/**",
            "/webjars/**"
    };
}
