package be.yonicon.template.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

// squid:S1118 : constructor needed for Spring Configuration
// squid:S4604 : EnableAutoConfiguration needed for enabling spring security
@SuppressWarnings({"squid:S4604", "squid:S1118"})
@Configuration
public class SecurityConfig {

    @Configuration
    @EnableAutoConfiguration
    @ConditionalOnProperty(prefix = "be.yonicon.template.infra.security", name = "enabled", havingValue = "false")
    protected static class DefaultWebSecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http.authorizeHttpRequests(x -> x.anyRequest().permitAll())
                    .build();
        }

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
            return web -> web.ignoring().requestMatchers("/**");
        }
    }

    @EnableWebSecurity
    @EnableAutoConfiguration
    @ConditionalOnProperty(prefix = "be.yonicon.template.infra.security", name = "enabled")
    protected static class SsoConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(x -> x.anyRequest().authenticated())
                    .build();
        }

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
            return web -> web.ignoring().requestMatchers("/actuator/**");
        }
    }
}