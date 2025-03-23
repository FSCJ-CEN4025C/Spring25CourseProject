package edu.fscj.cen3024c.financialclarity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    // TODO: add addtional config files, make sure this one is correct

    private static final String[] SWAGGER_WHITELIST = {
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/swagger-ui.html",
        "user/register" 
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and() .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
                // .requestMatchers(SWAGGER_WHITELIST).permitAll()
                // .anyRequest().authenticated()
            )
            .httpBasic();

        return http.build();
    }
}





// import edu.fscj.cen3024c.financialclarity.jwt.filters.JwtRequestFilter;
// import edu.fscj.cen3024c.financialclarity.jwt.services.ApplicationUserDetailsService;
// import lombok.AllArgsConstructor;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.BeanIds;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @AllArgsConstructor
// @Configuration
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// public class SecurityConfig extends WebSecurityConfigurerAdapter {

//     private final ApplicationUserDetailsService userDetailsService;
//     private final JwtRequestFilter jwtFilter;

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(userDetailsService);
//     }

//     @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//     @Override
//     public AuthenticationManager authenticationManagerBean() throws Exception {
//         return super.authenticationManagerBean();
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//                 // first chain
//                 .csrf()
//                 .disable()
//                 // second chain
//                 .antMatcher("/**")
//                 .authorizeRequests()
//                 // third chain
//                 .antMatchers("/**")
//                 .permitAll()
//                 // fourth chain
//                 .and()
//                 .sessionManagement()
//                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//     }
// }

