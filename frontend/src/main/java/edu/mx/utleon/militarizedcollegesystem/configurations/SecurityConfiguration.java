package edu.mx.utleon.militarizedcollegesystem.configurations;

import edu.mx.utleon.militarizedcollegesystem.users.UserDetailsServiceImpl;
import edu.mx.utleon.militarizedcollegesystem.common.entity.users.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
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
                    .hasAuthority(Roles.INFORMATION_TECHNOLOGIES.name())

                    .requestMatchers("/roles/**")
                    .hasAuthority(Roles.INFORMATION_TECHNOLOGIES.name())

                    .requestMatchers("/periods/**")
                    .hasAuthority(Roles.SCHOOL_SERVICES.name())

                    .requestMatchers("/applicants/**")
                    .hasAuthority(Roles.SCHOOL_SERVICES.name())

                    .requestMatchers("/scholarships/results")
                    .hasAnyAuthority(
                            Roles.STUDENT.name(),
                            Roles.TEACHER.name(),
                            Roles.SCHOOL_SERVICES.name(),
                            Roles.HUMAN_RESOURCES.name(),
                            Roles.INFORMATION_TECHNOLOGIES.name()
                    )

                    .requestMatchers("/scholarships/apply")
                    .hasAuthority(
                            Roles.STUDENT.name()
                    )

                    .requestMatchers("/scholarships/applications")
                    .hasAuthority(
                            Roles.SCHOOL_SERVICES.name()
                    )

                    .requestMatchers("/staff/**")
                    .hasAuthority(Roles.HUMAN_RESOURCES.name())

                    .requestMatchers("/grades/**")
                    .hasAnyAuthority(
                            Roles.STUDENT.name(),
                            Roles.TEACHER.name(),
                            Roles.SCHOOL_SERVICES.name()
                    )

                    .requestMatchers("/groups/**")
                    .hasAnyAuthority(
                            Roles.TEACHER.name(),
                            Roles.SCHOOL_SERVICES.name()
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
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
