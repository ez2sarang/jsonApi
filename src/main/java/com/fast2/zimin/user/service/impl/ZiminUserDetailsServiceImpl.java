package com.fast2.zimin.user.service.impl;

import com.fast2.zimin.user.dao.UserDao;
import com.fast2.zimin.user.entity.Role;
import com.fast2.zimin.user.entity.ZiminUserDetails;
import com.fast2.zimin.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@Transactional(propagation = Propagation.SUPPORTS)
public class ZiminUserDetailsServiceImpl implements UserDetailsService {
	
//	@Value("${system.siteDefinition}")
	private String siteDefinition;
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String userId)
			throws UsernameNotFoundException {
		com.fast2.zimin.user.entity.User user = null;
		List<SimpleGrantedAuthority> authorities = null;
		boolean enabled = false;
		try {
			user = userDao.getUser(userId);
			authorities = new ArrayList<SimpleGrantedAuthority>();

			if (user == null)
				throw new UsernameNotFoundException(userId + " Not Found");

			for (Role role : user.getRoleList()) {
				String roleStr = "ROLE_" + role.getRoleId();
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
						roleStr);
				authorities.add(authority);
			}

			if (user.getState().intValue() == Constants.COMMONCODE.USER_STATE_NOMAL) {
				enabled = true;
			}
			
			// site definition check
			List<String> authorityNameString = new ArrayList<String>();
			for(int i = 0 ; i < authorities.size() ; i++)
			{
				authorityNameString.add(authorities.get(i).getAuthority());
			}
			
			if(authorityNameString.indexOf(Constants.COMMONCODE.USER_TYPE_ROLE_ROLE_SYSADMIN_STRING) < 0)
			{
				if(siteDefinition.equals("CP"))
				{
					if(authorityNameString.indexOf(Constants.COMMONCODE.USER_TYPE_ROLE_USER_STRING) < 0)
					{
						throw new UsernameNotFoundException(userId + " Not Found");
					}
				}
				else if(siteDefinition.equals("OP"))
				{
					if((authorityNameString.indexOf(Constants.COMMONCODE.USER_TYPE_ROLE_ROLE_ADMIN_STRING) < 0)
							&& (authorityNameString.indexOf(Constants.COMMONCODE.USER_TYPE_ROLE_ROLE_QC) < 0))
					{
						throw new UsernameNotFoundException(userId + " Not Found");
					}
				}
			}
			
		} catch (Exception e) {
			throw new UsernameNotFoundException("Exception=" + e.getMessage());
		}

		return new ZiminUserDetails(user.getUserId(), user.getPassword(),
				enabled, true, true, true, authorities, user.getUserName(),
				user.getUserType(), user.getCpId());
	}
}
