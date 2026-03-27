package vod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class VodSecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {return NoOpPasswordEncoder.getInstance();}
    @Bean
UserDetailsService userDetailsService() {
    UserDetails user1 = User.withUsername("user1").password("user1").roles("ADMIN").build();
    UserDetails user2 = User.withUsername("user2").password("user2").roles("REGULAR").build();
    return new InMemoryUserDetailsManager(user1, user2);
    }

/*
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(request->request
                        .requestMatchers(HttpMethod.POST,"/webapi/products").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/webapi/bakeries").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/webapi/bakeries/**").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic()
                .and()
                .build();
*/

//    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // Musisz przekazać lambdę do csrf()
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/webapi/products").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/webapi/bakeries").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/webapi/bakeries/**").authenticated()
                        .anyRequest().permitAll()
                )

                // Musisz użyć Customizer.withDefaults() lub lambdy
                .httpBasic(org.springframework.security.config.Customizer.withDefaults())

                .build();
    }

}
