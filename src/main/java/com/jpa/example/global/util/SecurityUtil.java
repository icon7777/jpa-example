package com.jpa.example.global.util;

import java.util.Objects;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

  private static Authentication getTokenPayload() {
    return Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication());
  }

  private static String getAuthenticationToUser(final Authentication authentication) {
    return authentication.getPrincipal().toString().equals("anonymousUser") ? "" : ((UserDetails) authentication.getPrincipal()).getUsername();
  }

  public static String getAccountId() {
    return getAuthenticationToUser(getTokenPayload());
  }

}
