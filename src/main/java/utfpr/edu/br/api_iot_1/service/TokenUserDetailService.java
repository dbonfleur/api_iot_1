/*package utfpr.edu.br.api_iot_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class TokenUserDetailService implements UserDetailsService {
    @Autowired
    private PessoaService pessoaService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) pessoaService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}*/

package utfpr.edu.br.api_iot_1.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import utfpr.edu.br.api_iot_1.model.Pessoa;

@Component
public class TokenUserDetailService implements UserDetailsService {
    @Autowired
    private PessoaService pessoaService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pessoa pessoa = pessoaService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                pessoa.getEmail(), pessoa.getSenha(), new ArrayList<>()
        );
    }
}

