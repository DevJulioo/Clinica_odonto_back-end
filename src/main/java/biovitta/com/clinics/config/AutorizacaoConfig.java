package biovitta.com.clinics.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays; // Importe Arrays

@Configuration
@EnableWebSecurity
public class AutorizacaoConfig implements WebMvcConfigurer {
    @Autowired
    FiltroSeguranca filtroSeguranca;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/biovitta/auth/**").permitAll() // Login e registro de paciente
                        .requestMatchers(HttpMethod.POST, "/biovitta/api/usuario/paciente/add").permitAll() // Registro de paciente
                        .requestMatchers(HttpMethod.POST, "/biovitta/api/usuario/medico/add").permitAll() // Registro de médico

                        // Rotas de Usuário (Admin)
                        .requestMatchers(HttpMethod.POST, "/biovitta/api/usuario/adm/add").hasRole("ADMIN")

                        // Rotas de Paciente
                        .requestMatchers(HttpMethod.GET, "/biovitta/api/usuario/paciente/get/all").hasAnyRole("ADMIN", "MEDICO")
                        .requestMatchers(HttpMethod.GET, "/biovitta/api/usuario/paciente/get/{id}").hasAnyRole("ADMIN", "MEDICO", "PACIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/biovitta/api/usuario/paciente/dell/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/biovitta/api/usuario/paciente/edit/{id}").hasAnyRole("ADMIN", "PACIENTE")

                        // Rotas de Médico
                        .requestMatchers(HttpMethod.GET, "/biovitta/api/usuario/medico/get/all").hasAnyRole("ADMIN", "PACIENTE")
                        .requestMatchers(HttpMethod.GET, "/biovitta/api/usuario/medico/get/{crm}").hasAnyRole("ADMIN", "MEDICO", "PACIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/biovitta/api/usuario/medico/dell/{crm}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/biovitta/api/usuario/medico/edit/{crm}").hasAnyRole("ADMIN", "MEDICO")

                        // Rotas de Medicos (especialidades)
                        .requestMatchers(HttpMethod.GET, "/biovitta/api/medicos/{crm}/especialidades").hasAnyRole("ADMIN", "MEDICO", "PACIENTE")

                        // Rotas de Consulta
                        .requestMatchers(HttpMethod.POST, "/biovitta/api/consulta/add").hasAnyRole("ADMIN", "PACIENTE")
                        .requestMatchers(HttpMethod.GET, "/biovitta/api/consulta/paciente/{id}").hasAnyRole("ADMIN", "PACIENTE")
                        .requestMatchers(HttpMethod.GET, "/biovitta/api/consulta/get/all").hasAnyRole("ADMIN", "MEDICO")
                        .requestMatchers(HttpMethod.GET, "/biovitta/api/consulta/get/{id}").hasAnyRole("ADMIN", "MEDICO", "PACIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/biovitta/api/consulta/dell/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/biovitta/api/consulta/edit/{id}").hasAnyRole("ADMIN", "MEDICO")

                        // Rotas de Especialidade
                        .requestMatchers("/biovitta/api/especialidades/**").hasRole("ADMIN")

                        // Rotas de Relatório
                        .requestMatchers("/biovitta/api/relatorio/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(filtroSeguranca, UsernamePasswordAuthenticationFilter.class )
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder CriptSenha (){
        return  new BCryptPasswordEncoder();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:*", "http://127.0.0.1:*", "http://192.168.*.*:*", "exp://*") // Padrões de origem mais abrangentes
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Use allowedOriginPatterns em vez de allowedOrigins com allowCredentials(true)
        configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:*", "http://127.0.0.1:*", "http://192.168.*.*:*", "exp://*")); // Padrões de origem mais abrangentes
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}