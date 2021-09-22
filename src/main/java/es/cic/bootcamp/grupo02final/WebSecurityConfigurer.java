package es.cic.bootcamp.grupo02final;

import org.springframework.context.annotation.Configuration;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.context.annotation.Profile;
=======
>>>>>>> 8b60d04f282c14916663aeed094eb681b31444fb
=======
>>>>>>> 476574c05a452fdd899b41ee3aa661fd6bce8899
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	private static final String ADMIN = "ADMIN";
	private static final String USUARIO = "USUARIO";
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
         .passwordEncoder(encoder)
         .withUser("usuario")
         .password(encoder.encode("secret2"))
         .roles(ADMIN, USUARIO);
        auth.inMemoryAuthentication()
         .passwordEncoder(encoder)
         .withUser("administrador")
         .password(encoder.encode("secret"))
         .roles("USUARIO");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers("/conector/**")
          .hasRole(ADMIN)
          .antMatchers("/instancia/**")
          .hasRole(USUARIO)
          .antMatchers("/flujo/**")
          .hasRole(ADMIN)
          .and()
          .httpBasic();
    }
}
