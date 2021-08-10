package com.EDG.BackEndEDG.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.EDG.BackEndEDG.model.Usuario;
import com.EDG.BackEndEDG.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException{
		
		Optional<Usuario> usuario = userRepository.findByEmail(userName);
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
		
		return usuario.map(UserDetailsImpl::new).get();
	}

	
}
