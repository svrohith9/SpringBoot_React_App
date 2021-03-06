package com.example.app.student;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum UserRole {

	ADMIN(Sets.newHashSet(
			UserPermission.COURSE_READ,
			UserPermission.COURSE_WRITE,
			UserPermission.STUDENT_READ,
			UserPermission.STUDENT_WRITE)),
	STUDENT(Sets.newHashSet()),
	ADMINTRAINEE(Sets.newHashSet(UserPermission.COURSE_READ, UserPermission.STUDENT_READ));

	private final Set<UserPermission> permissions;

	private UserRole(Set<UserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<UserPermission> getPermissions() {
		return permissions;
	}

	 public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
	        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
	                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
	                .collect(Collectors.toSet());
	        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
	        return permissions;
	    }
	
}
