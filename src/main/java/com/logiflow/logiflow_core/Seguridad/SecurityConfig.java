package com.logiflow.logiflow_core.Seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.logiflow.logiflow_core.servicio.UsuarioServicioImp;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final UsuarioServicioImp usuarioServicioImp;

    public SecurityConfig(UsuarioServicioImp usuarioServicioImp) {
        this.usuarioServicioImp = usuarioServicioImp;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(usuarioServicioImp).passwordEncoder(passwordEncoder());
        return auth.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // define a separate processing URL to avoid GET/POST conflict on the same path
        String loginPage = "/api/logiflow/login";
        String loginProcessing = "/api/logiflow/login/process";

        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests((authz) -> authz
                // public endpoints and static resources
                .requestMatchers(loginPage, loginProcessing, "/login.html", "/api/login/**", "/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                // Dashboard (allow controller path /dashboard and template path /dashboard.html)
                .requestMatchers("/dashboard", "/dashboard.html").hasAnyAuthority("ADMIN", "SUPERVISOR", "OPERATOR", "VENDEDOR", "USUARIO")
                // Catálogo
                .requestMatchers("/catalogo/productos","/catalogo/Productos.html").hasAnyAuthority("ADMIN", "SUPERVISOR", "OPERATOR", "VENDEDOR")
                .requestMatchers("/categorias").hasAnyAuthority("ADMIN", "SUPERVISOR", "OPERATOR")
                // Operaciones
                .requestMatchers("/operaciones/proveedores","/operaciones/proveedores.html").hasAnyAuthority("ADMIN", "SUPERVISOR", "OPERATOR")
                .requestMatchers("/operaciones/clientes","/operaciones/clientes.html").hasAnyAuthority("ADMIN", "SUPERVISOR", "OPERATOR", "VENDEDOR", "USUARIO")
                .requestMatchers("/operaciones/pedidos","/operaciones/pedidos.html").hasAnyAuthority("ADMIN", "SUPERVISOR", "OPERATOR", "VENDEDOR", "USUARIO")
                // Inventario y Transporte
                .requestMatchers("/Inventario.jsp", "/Transportes.jsp").hasAnyAuthority("ADMIN", "SUPERVISOR", "OPERATOR")
                // Reportes
                .requestMatchers("/Reportes.jsp").hasAnyAuthority("ADMIN", "SUPERVISOR")
                // Administración
                .requestMatchers("/rol.jsp", "/configuracion.jsp", "/auditoria.jsp", "/logs.jsp", "/backup.jsp").hasAuthority("ADMIN")
                // Personal
                .requestMatchers("/Perfil.jsp", "/cambiar-password.jsp").hasAnyAuthority("ADMIN", "SUPERVISOR", "OPERATOR", "VENDEDOR", "USUARIO")
                .requestMatchers("/error").permitAll()
                	    
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage(loginPage).permitAll()
                .loginProcessingUrl(loginProcessing)
                .usernameParameter("email")
                // after successful authentication redirect to /dashboard
                .defaultSuccessUrl("/dashboard", true)
            )
            .logout(logout -> logout.logoutUrl("/logout").permitAll());

        return http.build();
    }
}