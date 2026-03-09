package spring_security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import spring_security.services.MyuserDetailService;

@Configuration
@EnableWebSecurity
public class Config {
    @Autowired
    private MyuserDetailService userDetailService;

    @Autowired
    private JwtFilter jwtFilter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(token -> token.disable());
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/register","/login")
            .permitAll()
            .anyRequest().authenticated());
        
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // @Bean
    // public UserDetailsService userDetailedService(){
    //     UserDetails user1 = org.springframework.security.core.userdetails.User
    //         .withDefaultPasswordEncoder()
    //         .username("kiran")
    //         .password("hat00")
    //         .roles("USER")
    //         .build();
    //     UserDetails user2 = org.springframework.security.core.userdetails.User
    //         .withDefaultPasswordEncoder()
    //         .username("hatim")
    //         .password("hatim00")
    //         .roles("ADMIN")
    //         .build();

    //     return new InMemoryUserDetailsManager(user1,user2);
    // }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailService);  
        provider.setPasswordEncoder(new BCryptPasswordEncoder());      
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}
