package dev.antoniogrillo.primoprogettospring.configuration;

import dev.antoniogrillo.primoprogettospring.entity.Ruolo;
import dev.antoniogrillo.primoprogettospring.filter.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class ConfigratorePath {
    private final JWTFilter jwtFilter;
    private final AuthenticationProvider provider;

    @Bean
    protected SecurityFilterChain chain(HttpSecurity http){
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(a->a
                        .requestMatchers(HttpMethod.POST,"/all/utente/login").permitAll()
                        .requestMatchers("/all/**").permitAll()
                        .requestMatchers("/utente/**").authenticated()
                        .requestMatchers("/superadim/**").hasRole(Ruolo.SUPERADMIN.name())
                        .requestMatchers("/admin/**").hasAnyRole(Ruolo.ADMIN.name(),Ruolo.SUPERADMIN.name())
                        .anyRequest().authenticated()
                ).authenticationProvider(provider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
