package BootCamp.FinalProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import BootCamp.FinalProject.connection.ShoppingDBConnection;

/**
 * 
 * This class is responsable of Security and Login Service
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Determinates the credentials validation from checking in the Data Base
	 */
	@Autowired
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.jdbcAuthentication().dataSource(ShoppingDBConnection.getInstance().dataSource())//
				.usersByUsernameQuery("select username as principal, password as credentials, true from users where username = ?")//
				.authoritiesByUsernameQuery("select username, 'USER' from users where username=?");
	}

	/**
	 * Configure the authentication mode that will be used and the Urls that
	 * will need authentication to be accessed It also defines the logout
	 * redirect Url
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/cart/**").fullyAuthenticated()//
				.antMatchers("/users/delete/**").fullyAuthenticated()//
				.antMatchers("/users/update/**").fullyAuthenticated();
		http.csrf().disable();
		http.httpBasic();
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").clearAuthentication(true);
	}
}