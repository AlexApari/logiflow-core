package com.logiflow.logiflow_core.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuItemDTO {

	    @JsonProperty("heading")
	    @JsonAlias("title") // esto permite "heading" o "title"
	    private String name;

	    @JsonProperty("url")
	    private String path;
	    
	    private String icon;

	    private List<String> roles;

	    @JsonProperty("subMenu")
	    private List<MenuItemDTO> subMenu;

	    // getters, setters y constructores

	
	public MenuItemDTO(String name, String path,String icon, List<String> roles, List<MenuItemDTO> subMenu) {
		this.name = name;
		this.path = path;
		this.setIcon(icon);
		this.roles = roles;
		this.subMenu = subMenu;
	}

	
	public MenuItemDTO() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public List<MenuItemDTO> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<MenuItemDTO> subMenu) {
		this.subMenu = subMenu;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
