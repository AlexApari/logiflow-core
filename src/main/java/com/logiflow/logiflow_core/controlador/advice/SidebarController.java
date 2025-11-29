package com.logiflow.logiflow_core.controlador.advice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.logiflow.logiflow_core.dto.response.MenuItemDTO;
import com.logiflow.logiflow_core.servicio.SidebarService;

@ControllerAdvice
public class SidebarController {

    @Autowired
    private SidebarService menuService;
    
    public SidebarController(SidebarService menuService) {
		this.menuService = menuService;
	}
    
    @ModelAttribute("menu")
    public List<MenuItemDTO> populateMenu(Authentication authentication) {
        // Authentication viene de Spring Security
        Set<String> rolesUsuario = new HashSet<>();

        if (authentication != null && authentication.getAuthorities() != null) {
            rolesUsuario = authentication.getAuthorities()
                .stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority()) // "ROLE_ADMIN", etc.
                .map(r -> r.replace("ROLE_", "")) // opcional si en tu JSON no tienes "ROLE_"
                .collect(Collectors.toSet());
        }

        // Loguear roles
        System.out.println("Roles del usuario autenticado: " + rolesUsuario);

        // Obtener menu filtrado por roles
        List<MenuItemDTO> menuFiltrado = menuService.getMenuForRoles(rolesUsuario);
        System.out.println("Menu filtrado: " + menuFiltrado);

        return menuFiltrado;
    }
}
