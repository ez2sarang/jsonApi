package com.fast2.zimin.user.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class ZiminUserDetails implements UserDetails, Serializable {
	private String username;
	private String password;
	private final List<? extends GrantedAuthority> authorities;
	private final boolean accountNonExpired;
	private final boolean accountNonLocked;
	private final boolean credentialsNonExpired;
	private final boolean enabled;

	private String userDisplayName;
	private String userType;
	private String cpId;

	public ZiminUserDetails(String username, String password,
			Collection<? extends GrantedAuthority> authorities,
			String userDisplayName, String userType, String cpId) {
		this.username = username;
		this.password = password;
		this.authorities = (List<? extends GrantedAuthority>) authorities;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;

		this.userDisplayName = userDisplayName;
		this.userType = userType;
		this.cpId = cpId;
	}

	public ZiminUserDetails(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,
			String userDisplayName, String userType, String cpId) {
		this.username = username;
		this.password = password;
		this.authorities = (List<? extends GrantedAuthority>) authorities;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;

		this.userDisplayName = userDisplayName;
		this.userType = userType;
		this.cpId = cpId;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
}
