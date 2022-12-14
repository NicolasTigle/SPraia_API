package br.com.zeniti.spraia.config.Security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.httpBasic()
            .and()
            .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/api/usuario/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuario/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/usuario/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/usuario/**").authenticated()

                .antMatchers(HttpMethod.GET, "/api/praia/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/praia/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/praia/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/praia/**").authenticated()

                .antMatchers(HttpMethod.GET, "/api/denuncia/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/denuncia/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/denuncia/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/denuncia/**").authenticated()

                .anyRequest().denyAll()
            .and()
                .csrf().disable()
            ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }
    
}
