package com.excelr.cms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excelr.cms.entity.User;
import com.excelr.cms.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService
{
	@Autowired
	UserRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				User user=userrepo.findByUsername(username);
				if(user==null)
					throw new UsernameNotFoundException("User does not exist");
				
				return new MyDecoratedUser(user);
	}

}
