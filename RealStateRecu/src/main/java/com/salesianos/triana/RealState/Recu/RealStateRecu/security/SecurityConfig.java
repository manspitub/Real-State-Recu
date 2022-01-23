package com.salesianos.triana.RealState.Recu.RealStateRecu.security;

import com.salesianos.triana.RealState.Recu.RealStateRecu.security.jwt.JwtAccessDeniedHandler;
import com.salesianos.triana.RealState.Recu.RealStateRecu.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.hibernate.StatelessSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;
    private final JwtAuthorizationFilter filter;



    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/register/user").anonymous()
                .antMatchers(HttpMethod.POST, "/auth/login").anonymous()
                .antMatchers(HttpMethod.GET, "/propietarios/").authenticated()
                .antMatchers(HttpMethod.GET, "/vivienda/").authenticated()
                .antMatchers(HttpMethod.GET, "/vivienda/{id}").authenticated()
                .antMatchers(HttpMethod.PUT, "/vivienda/{id}").hasAnyRole("PROPIETARIO","ADMIN")
                .antMatchers(HttpMethod.DELETE, "/vivienda/{id}").hasAnyRole("PROPIETARIO","ADMIN")
                .antMatchers(HttpMethod.POST, "/{idViv}/inmobiliaria/{idInmo}").hasAnyRole("PROPIETARIO","ADMIN")
                .antMatchers(HttpMethod.DELETE, "/{idViv}/inmobiliaria/").hasAnyRole("PROPIETARIO","ADMIN")
                .antMatchers(HttpMethod.POST, "/vivienda/").hasRole("PROPIETARIO")
                .antMatchers(HttpMethod.POST, "/inmobiliaria/").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/inmobiliaria/{id}/gestor").hasAnyRole("ADMIN","GESTOR")
                .antMatchers(HttpMethod.DELETE, "/inmobiliaria/gestor/{id}").hasAnyRole("ADMIN","GESTOR")
                .antMatchers(HttpMethod.DELETE, "/inmobiliaria/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/inmobiliaria/").authenticated()
                .antMatchers(HttpMethod.GET, "/inmobiliaria/{id}").authenticated()
                .antMatchers(HttpMethod.GET, "/inmobiliaria/{id}/gestor").hasAnyRole("ADMIN","GESTOR")
                .antMatchers(HttpMethod.GET,"/h2-console/**").anonymous()
                .antMatchers(HttpMethod.POST,"/h2-console/**").anonymous()
                .antMatchers(HttpMethod.POST,"/auth/register/gestor").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/auth/register/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/propietario/{id}").hasAnyRole("ADMIN","PROPIETARIO")
                .antMatchers(HttpMethod.POST,"/vivienda/{id}/meinteresa").hasRole("PROPIETARIO")
                .antMatchers(HttpMethod.DELETE,"/vivienda/{id}/meinteresa").hasRole("PROPIETARIO")
                .antMatchers(HttpMethod.GET,"/interesado/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/interesado/{id}").hasAnyRole("PROPIETARIO","ADMIN")
                .antMatchers(HttpMethod.GET,"/vivienda/top10").authenticated()
                .antMatchers(HttpMethod.GET,"/vivienda/enpropiedad").hasRole("PROPIETARIO")
                .anyRequest().authenticated();


        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
