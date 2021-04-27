package com.alquiler.reservas.security.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.alquiler.reservas.service.UserDetailsServiceImpl;


@Configuration		//Indica que esta clase es de configuracion y necesita ser cargada durante el inicio del server
@EnableWebSecurity	//Indica que esta clase sobreescribira la implmentacion de seguridad web
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
    String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
    };
	
	/* Auth in Memory
	@Override		
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user").password("{noop}123").roles("cl")
		.and()
		.withUser("admin").password("{noop}123").roles("ad");
	}
	*/
	
	/*
	 * 
	 *  Previo a la restructuración de la seguridad
	 *  
	 
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
        	.authorizeRequests()
        	.antMatchers("/admin/dashboard").hasAnyRole("ad")
        	.antMatchers("/user/dashboard").hasAnyRole("cl","ad")
            .antMatchers("/").hasAnyRole("cl","ad")
            .and().formLogin()
            .defaultSuccessUrl("/userForm",true)
			//.successHandler(successHandler)
            .loginPage("/login").permitAll()
			.and().logout().and().exceptionHandling().accessDeniedPage("/accessdenied")
			;

    }
    */
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests()
        .antMatchers(resources).permitAll()  
        .antMatchers("/","/index").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .permitAll()
            .defaultSuccessUrl("/userForm")
            .failureUrl("/login?error=true")
            .usernameParameter("username")
            .passwordParameter("password")
            .and()
            .csrf().disable()
        .logout()
            .permitAll()
            .logoutSuccessUrl("/login?logout");
    }
    
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }
    
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;   
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
    	//Especificar el encargado del login y encriptacion del password
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
