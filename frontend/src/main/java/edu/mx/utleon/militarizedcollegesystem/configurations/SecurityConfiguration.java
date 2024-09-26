package edu.mx.utleon.militarizedcollegesystem.configurations;

import edu.mx.utleon.militarizedcollegesystem.users.UserDetailsServiceImpl;
import edu.mx.utleon.militarizedcollegesystem.model.users.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry
                    .requestMatchers("/img/**", "/css/**", "/js/**", "/webjars/**", "/favicon.svg")
                    .permitAll();
            authorizationManagerRequestMatcherRegistry
                    .requestMatchers("/users/**")
                    .hasAuthority(Roles.ROLE_INFORMATION_TECHNOLOGIES.name())

                    .requestMatchers("/roles/**")
                    .hasAuthority(Roles.ROLE_INFORMATION_TECHNOLOGIES.name())

                    .requestMatchers("/periods/**")
                    .hasAuthority(Roles.ROLE_SCHOOL_SERVICES.name())

                    .requestMatchers("/applicants/**")
                    .hasAuthority(Roles.ROLE_SCHOOL_SERVICES.name())

                    .requestMatchers("/scholarships/results")
                    .hasAnyAuthority(
                            Roles.ROLE_STUDENT.name(),
                            Roles.ROLE_TEACHER.name(),
                            Roles.ROLE_SCHOOL_SERVICES.name(),
                            Roles.ROLE_HUMAN_RESOURCES.name(),
                            Roles.ROLE_INFORMATION_TECHNOLOGIES.name()
                    )

                    .requestMatchers("/scholarships/apply")
                    .hasAuthority(
                            Roles.ROLE_STUDENT.name()
                    )

                    .requestMatchers("/scholarships/applications")
                    .hasAuthority(
                            Roles.ROLE_SCHOOL_SERVICES.name()
                    )

                    .requestMatchers("/staff/**")
                    .hasAuthority(Roles.ROLE_HUMAN_RESOURCES.name())

                    .requestMatchers("/grades/**")
                    .hasAnyAuthority(
                            Roles.ROLE_STUDENT.name(),
                            Roles.ROLE_TEACHER.name(),
                            Roles.ROLE_SCHOOL_SERVICES.name()
                    )

                    .requestMatchers("/groups/**")
                    .hasAnyAuthority(
                            Roles.ROLE_TEACHER.name(),
                            Roles.ROLE_SCHOOL_SERVICES.name()
                    )

                    .anyRequest()
                    .authenticated();
        });
        http.formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer
                    .loginPage("/login")
                    .usernameParameter("username")
                    .permitAll();
        });
        http.rememberMe(httpSecurityRememberMeConfigurer -> {
            httpSecurityRememberMeConfigurer
                    .key("AbcdEfghIjklmNopQrsTuvXyz_0123456789");
            httpSecurityRememberMeConfigurer.tokenValiditySeconds(7 * 24 * 60 * 60);
        });
        http.logout(httpSecurityLogoutConfigurer -> {
            httpSecurityLogoutConfigurer
                    .permitAll();
        });
        http.headers(httpSecurityHeadersConfigurer -> {
            httpSecurityHeadersConfigurer
                    .frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin());
        });
        http.authenticationProvider(authenticationProvider());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
