package ch.cern.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                //.headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable())
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                        .requestMatchers(toH2Console()).permitAll()
                        .requestMatchers("/api/task/**", "/api/task-category/**").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}


