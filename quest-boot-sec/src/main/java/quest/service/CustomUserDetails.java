package quest.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import quest.model.Utilisateur;

public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Utilisateur utilisateur;

	public CustomUserDetails(Utilisateur utilisateur) {
		super();
		this.utilisateur = utilisateur;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> roles = new ArrayList<>();

		utilisateur.getRoles().forEach(role -> roles.add("ROLE_" + role.name()));

		return AuthorityUtils.createAuthorityList(roles.toArray(new String[roles.size()]));
	}

	@Override
	public String getPassword() {
		return new BCryptPasswordEncoder().encode(utilisateur.getMotDePasse());
	}

	@Override
	public String getUsername() {
		return utilisateur.getIdentifiant();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return !utilisateur.isDisabled();
	}

}
