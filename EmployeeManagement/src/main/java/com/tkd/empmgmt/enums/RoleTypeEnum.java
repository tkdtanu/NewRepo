package com.tkd.empmgmt.enums;

import java.util.Arrays;
import java.util.Optional;

public enum RoleTypeEnum {
	CEO(1L,"CEO"),
	VP(2L,"VP"),
	DIRECTOR(3L,"Director"),
	MANAGER(4L,"Manager"),
	SDE(5L,"SDE");
	
	private Long roleId;
	
	private String role;

	private RoleTypeEnum(Long roleId, String role) {
		this.roleId = roleId;
		this.role = role;
	}

	public Long getRoleId() {
		return roleId;
	}

	public String getRole() {
		return role;
	}
	
	public static Optional<RoleTypeEnum> getRoleById(Long roleId) {
		return Arrays.stream(values()).filter(role -> role.getRoleId() == roleId).findFirst();
	}
	
	public static Optional<RoleTypeEnum> getRoleByType(String roleType) {
		return Arrays.stream(values()).filter(role -> role.getRole().equals(roleType)).findFirst();
	}
	
}
