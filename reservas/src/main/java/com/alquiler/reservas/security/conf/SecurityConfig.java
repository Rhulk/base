package com.alquiler.reservas.security.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration	
@EnableWebSecurity	
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	/* Auth in Memory*/
	@Override		
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user").password("{noop}123").roles("cl")
		.and()
		.withUser("admin").password("{noop}123").roles("ad");
	}
	
	
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
        	.authorizeRequests()
        	.antMatchers("/admin/dashboard").hasAnyRole("ad")
        	.antMatchers("/user/dashboard").hasAnyRole("cl","ad")
            .antMatchers("/").hasAnyRole("cl","ad")
            .and().formLogin()
           // .defaultSuccessUrl("/userForm",true)
			//.successHandler(successHandler)
            //.loginPage("/login").permitAll()
			.and().logout().and().exceptionHandling().accessDeniedPage("/accessdenied")
			;

    }

}
