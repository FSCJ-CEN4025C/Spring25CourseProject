package edu.fscj.cen3024c.financialclarity.jwt.models;

import edu.fscj.cen3024c.financialclarity.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class UserPrincipal implements UserDetails {

  private final User userEntity;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }


  public Integer getId() {
    return (this.userEntity != null) ? this.userEntity.getId() : null;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return this.userEntity.getUsername();

  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
