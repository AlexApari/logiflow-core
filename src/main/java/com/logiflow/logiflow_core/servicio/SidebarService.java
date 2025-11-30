package com.logiflow.logiflow_core.servicio;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.logiflow.logiflow_core.dto.response.MenuItemDTO;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class SidebarService {

    private final List<MenuItemDTO> rawMenu;

    public SidebarService() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getClassLoader().getResourceAsStream("static/permisos_sidebar.json");
        rawMenu = mapper.readValue(is, new TypeReference<List<MenuItemDTO>>() {});
//        rawMenu.forEach(item -> {
//            System.out.println("RAW ITEM: " + item.getName() + ", " + item.getPath() + ", Submenu: " + item.getSubMenu());
//        });
    }

    public List<MenuItemDTO> getMenuForRoles(Set<String> rolesUsuario) {
        return rawMenu.stream()
                .filter(item -> hasAccess(item.getRoles(), rolesUsuario))
                .map(item -> copyWithFilteredSubItems(item, rolesUsuario))
                .toList();
    }

    private boolean hasAccess(List<String> requiredRoles, Set<String> userRoles) {
        return requiredRoles != null && requiredRoles.stream().anyMatch(userRoles::contains);
    }

    private MenuItemDTO copyWithFilteredSubItems(MenuItemDTO item, Set<String> rolesUsuario) {
        MenuItemDTO copy = new MenuItemDTO();
        copy.setName(item.getName());
        copy.setPath(item.getPath());
        copy.setIcon(item.getIcon());
        copy.setRoles(item.getRoles());

        if (item.getSubMenu() != null) {
            List<MenuItemDTO> filtered = item.getSubMenu().stream()
                    .filter(sub -> {
                        boolean access = hasAccess(sub.getRoles(), rolesUsuario);
                        //System.out.println("   Subitem: " + sub.getName() + " roles: " + sub.getRoles() + " -> access: " + access);
                        return access;
                    })
                    .toList();
            copy.setSubMenu(filtered.isEmpty() ? null : filtered);
        }
        //System.out.println("Item final: " + copy.getName() + " Submenu: " + copy.getSubMenu());
        
        return copy;
    }

    }