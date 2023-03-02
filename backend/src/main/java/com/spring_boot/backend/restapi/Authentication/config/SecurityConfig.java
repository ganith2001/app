package com.spring_boot.backend.restapi.Authentication.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter authFilter;



    @Bean
    public UserDetailsService userDetailsService() {
     

       return new UserInfoUserDetailsService();

    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf().disable().cors().and()
                .authorizeHttpRequests().requestMatchers("/candidateSignup","/recruiterSignup","/recruiterLogin","/candidateLogin","/otpGenerate","/getRegisteredUser/*").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/sendMail","/getProfile/*","/authenticate","/getCandidateBySkills","/getRecruiterDetails/*","/getCandidateDetails/*","/changePassword/*","/deleteResume/*","/getAllJobs","/getJobsByEmpId/*","/getJobsByEmpId/*","/deleteJobsByJobId/*","/getResume/*","/download/*","/updateCollege/*","/deleteSkills/**","/updateSkills/*","/updateProfile/*","/getAppliedjobsIdJobAndShortLists/*","/getAppliedJobsIdByCid/*","/addJobs","/applyJobs","/updateStatus/**","/getSkills","/createProfile","/upload","/getAppliedJobsByCid/*","/getCandidatesByJobid/*","/deleteJob/*")
                .authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService());
      
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }

   

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        
        return config.getAuthenticationManager();
    }
}
