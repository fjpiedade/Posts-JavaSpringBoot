package ao.phi.posts.security.config;

import ao.phi.posts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                //.anyRequest().permitAll() //all request are permitted
                .antMatchers(HttpMethod.GET,"/api/v1/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/v1/user").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/api/v1/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/v1/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/post/**").hasRole("OWNER")
                .antMatchers(HttpMethod.POST,"/api/v1/post/**").hasRole("OWNER")
                .anyRequest().authenticated() //all request need authentications
                .and()
                .csrf().disable() //Permit Post, Put, and Delete
                //.formLogin()
        ;

//                .csrf().disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .antMatchers("/api/v1/**")
//                .permitAll()
//                .antMatchers("/api/v1/**")
//                .hasAnyRole("ROLE_ADMIN");
                //.formLogin().loginPage("/login").permitAll();
    }

    //Authentication in Memory
   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("phi")
                //.password(passwordEncoder().encode("raiz"))
                .password("{noop}raiz") //it works
                //.roles("ADMIN")
                .authorities("ROLE_USER", "ROLE_ADMIN");

                //.withUser("user").password("{noop}password").roles("USER")
                //.and()
                //.withUser("admin").password("{noop}password").roles("ADMIN");
    }*/

    //using security in database
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder);
//        provider.setUserDetailsService(userService);
//        return provider;
//    }

}
