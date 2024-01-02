package authentification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Utilisateur;
import com.example.demo.repository.UtilisateurRepository;

@Service
public class UserDetailsServiceTP implements UserDetailsService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur utilisateur = utilisateurRepository.findByNom(username);
		if (utilisateur == null) {
			throw new UsernameNotFoundException(username);
		}
		return User.withUsername(utilisateur.getNom())
				.password(utilisateur.getPassword())
                .roles(utilisateur.getRole())
                .build();

	}

}
