package de.aittr.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity  //имеются методы безопасности
@EnableGlobalMethodSecurity (prePostEnabled = true, jsr250Enabled = true) //то что предлагается из коробки
public class SecurityConfig {  //в этом классе через код мы конфигурируем параметры нашей безопасности

    @Value("${spring.security.debug:false}")
    boolean securityDebug;

    @Bean //вспомогательный метод, все данные будут проверены и принято решение о допуске
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()  //куда и кому можно идти и что делать можно
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE)
                .hasRole("ADMIN")
                .antMatchers("/admin/**")
                .hasAnyRole("ADMIN")
                .antMatchers("/user/**")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers("/login/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();


        return http.build();


    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(securityDebug)
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }


}