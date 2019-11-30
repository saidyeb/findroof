package findroof.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean(name = "passwordEncoder2")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
    	http.authorizeRequests()
    			.antMatchers("/resources**").permitAll()
                .antMatchers("/posts**").authenticated()
                .antMatchers("/possessions**").authenticated()
                .antMatchers("/requests**").authenticated()
                .anyRequest().permitAll()
            .and()
            .formLogin().loginPage("/login").defaultSuccessUrl("/posts").failureUrl("/login")
            .usernameParameter("username").passwordParameter("password")
        .and()
            .logout().invalidateHttpSession(true)
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
        .and()
            .csrf()
        .and()
            .sessionManagement().maximumSessions(1).expiredUrl("/login");
    }
}
