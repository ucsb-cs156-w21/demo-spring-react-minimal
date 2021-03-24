package edu.ucsb.changeme.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.auth0.jwt.interfaces.DecodedJWT;
import edu.ucsb.changeme.entities.AppUser;
import edu.ucsb.changeme.repositories.AdminRepository;

/**
 * Service object that determines whether a user is a member of the google org or not.
 */
@Slf4j
@Service
public class Auth0MembershipService implements MembershipService {
  @Value("${app.namespace}")
  private String namespace;

  @Value("${app.admin.emails}")
  final private List<String> adminEmails = new ArrayList<String>();

  @Value("${app.member.hosted-domain}")
  private String memberHostedDomain;

  @Autowired
  private AdminRepository adminRepository;

  /**
   * is current logged in user a member but NOT an admin of the google org
   *
   * @param jwt The decoded JSON Web Token that contains all of the user's claims/information
   * @return if the current jwt corresponds to a member
   */
  @Override
  public boolean isMember(DecodedJWT jwt) {
    return hasRole(jwt, "member");
  }

  /**
   * is current logged in user a member of the google org
   *
   * @param jwt The decoded JSON Web Token that contains all of the user's claims/information
   * @return if the current jwt corresponds to an admin
   */
  @Override
  public boolean isAdmin(DecodedJWT jwt) {
    return hasRole(jwt, "admin");
  }

  /**
   * is current logged in user has role
   *
   * @param roleToTest "member" or "admin"
   * @return if the current logged in user has that role
   */
  private boolean hasRole(DecodedJWT jwt, String roleToTest) {
    if (jwt == null)
      return false;

    Map<String, Object> customClaims = jwt.getClaim(namespace).asMap();
    if (customClaims == null) {
      log.error("ERROR!  customClaims is null");
      log.error("namespace = {}", namespace);
      return false;
    }

    String email = (String) customClaims.get("email");
    String hostedDomain = email.substring(email.indexOf("@") + 1);

    log.info("email=[" + email + "]");
    log.info("hostedDomain=" + hostedDomain);

    if (roleToTest.equals("admin") && isAdminEmail(email)) {
      return true;
    }

    if (roleToTest.equals("member") && memberHostedDomain.equals(hostedDomain)) {
      return true;
    }

    return false;
  }

  private boolean isAdminEmail(String email) {
    return (!adminRepository.findByEmail(email).isEmpty() || isDefaultAdmin(email));
  }

  private boolean isDefaultAdmin(String email) {
    return adminEmails.contains(email);
  }

  @Override
  public boolean isMember(AppUser user) {
    String email = user.getEmail();
    String hostedDomain = email.substring(email.indexOf("@") + 1);
    return memberHostedDomain.equals(hostedDomain);
  }

  @Override
  public boolean isAdmin(AppUser user) {
    return isAdminEmail(user.getEmail());
  }

  @Override
  public List<String> getDefaultAdminEmails() {
    return adminEmails;
  }
}
