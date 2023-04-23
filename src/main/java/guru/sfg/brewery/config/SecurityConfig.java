package guru.sfg.brewery.config;

import guru.sfg.brewery.security.SfgPasswordEncoderFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordCustomPEFEncoder() {
        return SfgPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /*@Bean
    PasswordEncoder passwordPEFEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }*/

    /*@Bean
    PasswordEncoder passwordBcryptEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    /*@Bean
    PasswordEncoder passwordSha256Encoder() {
        return new StandardPasswordEncoder();
    }*/

    /*@Bean
    PasswordEncoder passwordLDAPEncoder() {
        return new LdapShaPasswordEncoder();
    }*/

    /*@Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http
                .authorizeRequests(authorize -> {
                    authorize
                        .antMatchers("/","/webjars/**","/login","/resources/**").permitAll()
                        .antMatchers("/beers/find","/beers").permitAll()
                        .antMatchers(HttpMethod.GET,"/api/v1/beer/**").permitAll()
                        .mvcMatchers(HttpMethod.GET,"/api/v1/beerUpc/546554").permitAll();
                })
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
    }

    //The following test works with passwordEncoderFactories
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("spring")
                //.password("guru") //There is no PasswordEncoder mapped for the id "null"
                .password("{bcrypt}$2a$16$z2Crk441IKtGs4Uf5vQK0OmwRnl8CWHD6TTKjzVsP0Jcmn2Y2Fufy")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{sha256}bc8216504211acbbdb4cba134416d1086423fcb327aea04211ae92362998605813850a80b618b369")
                .roles("USER");

        auth.inMemoryAuthentication()
                .withUser("scott")
                .password("{ldap}{SSHA}wMWl9ZH+OpMX1tIoZfP2jcp58V2KV8ZYeJ1AtQ==")
                .roles("CUSTOMER");

        auth.inMemoryAuthentication()
                .withUser("hugo")
                .password("{bcrypt15}$2a$15$D4s1/FrwhX0NCeUnjQqTzeQ4t97myyfUveeENUPc2990aYYLcyIWG")
                .roles("CUSTOMER");
    }

    /*//The following test works with passwordBcryptEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("spring")
                //.password("guru") //There is no PasswordEncoder mapped for the id "null"
                .password("guru")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("$2a$16$Q68sGMZWKlYcXCloS05Y3e0b1mjRLBW2rU.tSa3Pj5pr2haqc3xpS")
                .roles("USER");

        auth.inMemoryAuthentication()
                .withUser("scott")
                .password("tiger")
                .roles("CUSTOMER");
    }*/

    /*
    //The following test works with passwordSha256Encoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("spring")
                //.password("guru") //There is no PasswordEncoder mapped for the id "null"
                .password("guru")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("e6d9f230e60fc1db94e7a5d18bd6d192cf7db01d21d2a3deeb352a7e6d328fcc2c1595b4887a6556")
                .roles("USER");

        auth.inMemoryAuthentication()
                .withUser("scott")
                .password("tiger")
                .roles("CUSTOMER");
    }*/

    /*
    //The following test works with passwordLDAPEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("spring")
                //.password("guru") //There is no PasswordEncoder mapped for the id "null"
                .password("guru")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{SSHA}eQiwtOrDDkyoZNa/o+uIykLtsuDDdmujZs1eOQ==")
                .roles("USER");

        auth.inMemoryAuthentication()
                .withUser("scott")
                .password("tiger")
                .roles("CUSTOMER");
    }*/

    /*//The following test works with passwordEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("spring")
                //.password("guru") //There is no PasswordEncoder mapped for the id "null"
                .password("guru")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("password")
                .roles("USER");

        auth.inMemoryAuthentication()
                .withUser("scott")
                .password("tiger")
                .roles("CUSTOMER");
    }*/

    /*
    //The following test works without passwordEncoder
    //fluent configuration
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("spring")
                //.password("guru") //There is no PasswordEncoder mapped for the id "null"
                .password("{noop}guru")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{noop}password")
                .roles("USER");

        auth.inMemoryAuthentication()
                .withUser("scott")
                .password("{noop}tiger")
                .roles("CUSTOMER");
    }*/

    /*@Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("spring")
                .password("guru")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin,user);
    }*/
}
