package com.tmobile.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tmobile.auth.LoginSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private LoginSuccessHandler loginHandler;

    @Autowired
    public SecurityConfig(UserDetailsService service, LoginSuccessHandler handler) {
        this.userDetailsService = service;
        this.loginHandler = handler;
    }

    @Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/manager/**").hasRole("MANAGER")
                .antMatchers("/customer/**").hasRole("CUSTOMER")
//                .antMatchers(HttpMethod.GET, "/options/{optionId}").hasAnyRole("CUSTOMER", "MANAGER")
//                .antMatchers(HttpMethod.GET, "/options").hasAnyRole("CUSTOMER", "MANAGER")
//                .antMatchers(HttpMethod.DELETE, "/options/{optionId}").hasRole("MANAGER")
//                .antMatchers(HttpMethod.POST, "/options").hasRole("MANAGER")
//                .antMatchers(HttpMethod.PUT, "/options").hasRole("MANAGER")
//
//                .antMatchers(HttpMethod.GET, "/tariffs/{optionId}").hasAnyRole("CUSTOMER", "MANAGER")
//                .antMatchers(HttpMethod.GET, "/tariffs").hasAnyRole("CUSTOMER", "MANAGER")
//                .antMatchers(HttpMethod.DELETE, "/tariffs/{optionId}").hasRole("MANAGER")
//                .antMatchers(HttpMethod.POST, "/tariffs").hasRole("MANAGER")
//                .antMatchers(HttpMethod.PUT, "/tariffs").hasRole("MANAGER")

                .antMatchers("/options").permitAll()
                .antMatchers(HttpMethod.GET, "/tariffs").permitAll()
                .antMatchers("/options/**").permitAll()
                .antMatchers("/tariffs/**").permitAll()

                .antMatchers(HttpMethod.PUT, "/contracts/sync_new_contract_info").hasRole("MANAGER")
                .antMatchers(HttpMethod.POST, "/contracts").hasRole("MANAGER")
                .antMatchers(HttpMethod.POST, "/contracts").hasRole("MANAGER")
                .antMatchers(HttpMethod.PUT, "/contracts").hasAnyRole("CUSTOMER", "MANAGER")

                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", false)
                .successHandler(loginHandler)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();

    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
}
