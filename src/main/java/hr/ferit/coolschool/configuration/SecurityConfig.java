package hr.ferit.coolschool.configuration;

import hr.ferit.coolschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/api/users/registration").permitAll()
                .antMatchers("/api/quiz-results/**/submit").hasAuthority("ROLE_STUDENT")
                .anyRequest().authenticated()
                .and()
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                    .authenticationEntryPoint(unathorizedEntryPoint())
                .and()
                    .csrf().disable().cors()
                .and()
                    .formLogin()
                    .loginProcessingUrl("/login")
                .successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {{
                        setRedirectStrategy((request, response, url) -> {
                        });
                    }})
                    .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
                        httpServletResponse.getOutputStream().println("{ \"status\":\"401\",\n \"message\": \"Prijava nije uspjela\" }");
                    })
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));
    }

    @Bean
    public AuthenticationEntryPoint unathorizedEntryPoint() {
        return (request, response, authException) -> {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getOutputStream().println("{ \"status\":\"403\",\n \"message\": \"Pristup zabranjen\" }");
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().println("{ \"status\":\"401\",\n \"message\": \"Neovlasten pristup\" }");
        };
    }
}
