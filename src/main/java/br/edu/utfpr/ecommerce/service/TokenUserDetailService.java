package br.edu.utfpr.ecommerce.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.edu.utfpr.ecommerce.repository.PessoaRepository;
 
@Component
public class TokenUserDetailService implements UserDetailsService {
    @Autowired
    private PessoaRepository pessoaRepository;
 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return pessoaRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
 