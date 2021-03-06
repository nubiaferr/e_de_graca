package com.EDG.BackEndEDG.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.EDG.BackEndEDG.model.Usuario;
import com.EDG.BackEndEDG.model.UsuarioLogin;
import com.EDG.BackEndEDG.repository.UsuarioRepository;

@Service
public class UsuarioService {

	//regras de negócio: senha criptografada e token pra acessar
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//cadastrar
	public Optional <Usuario> cadastrarUsuario (Usuario usuario){
		
		if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) 
			return Optional.ofNullable(null);
					
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return Optional.of(usuarioRepository.save(usuario));
	} 
	
	//atualizar 
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		
		if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			
			return Optional.of(usuarioRepository.save(usuario));

		} else {
			return Optional.ofNullable(null);
		}
	}
	
	//logar - onde vamos gerar token
	public Optional<UsuarioLogin> loginUsuario(Optional<UsuarioLogin> usuarioLogin){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioLogin.get().getEmail());
		
		if (usuario.isPresent()) {
			
			if(encoder.matches(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				
				//vou gerar o token
				String auth = usuarioLogin.get().getEmail() + ":" + usuarioLogin.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodeAuth); //ISSO É O TOKEN
				
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				usuarioLogin.get().setEmpresa(usuario.get().isEmpresa());
				usuarioLogin.get().setToken(authHeader);
				
				return usuarioLogin;
			}
		}
		return Optional.ofNullable(null);
	}
	
	
}
