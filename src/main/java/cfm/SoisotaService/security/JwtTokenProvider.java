package cfm.SoisotaService.security;

import cfm.SoisotaService.entities.AppRole;
import cfm.SoisotaService.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  /**
   * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
   * microservices environment, this key would be kept on a config-server.
   */
  @Value("${security.jwt.token.secret-key:secret-key}")
  private String secretKey;

  @Value("${security.jwt.token.private-key:private-key}")
  private String privateKey;

  @Value("${security.jwt.token.public-key:public-key}")
  private String publicKey;

  @Value("${security.jwt.token.expire-length:86400000}")
  private long validityInMilliseconds = 86400000 * 7; // 7 day

  @Autowired
  private MyUserDetails myUserDetails;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(String username, List<AppRole> appRoles)
      throws NoSuchAlgorithmException, InvalidKeySpecException {

    Claims claims = Jwts.claims().setSubject(username);
    claims.put("auth", appRoles.stream().map(s -> new SimpleGrantedAuthority(s.getRoleId()))
        .filter(Objects::nonNull).collect(Collectors.toList()));

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    //Lấy private key RSA 256
    byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey);

    PKCS8EncodedKeySpec specPK = new PKCS8EncodedKeySpec(privateKeyBytes);
    KeyFactory factoryPK = KeyFactory.getInstance("RSA");
    PrivateKey priKey = factoryPK.generatePrivate(specPK);

    return Jwts.builder()//
        .setClaims(claims)//
        .setIssuedAt(now)//
        .setHeaderParam("typ", "JWT")
        .setExpiration(validity)//
        .signWith(SignatureAlgorithm.RS256, priKey)//
        .compact();
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  @SneakyThrows
  public String getUsername(String token) {
    //Lấy public key RSA 256
    byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
    X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
    KeyFactory factory = KeyFactory.getInstance("RSA");
    PublicKey pubKey = factory.generatePublic(spec);

    return Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  public boolean validateToken(String token) {
    try {
      //Lấy public key RSA 256
      byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
      X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
      KeyFactory factory = KeyFactory.getInstance("RSA");
      PublicKey pubKey = factory.generatePublic(spec);

      Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token);

      return true;

    } catch (JwtException | NoSuchAlgorithmException e) {
      throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (IllegalArgumentException e) {
      throw new CustomException("JWT claims string is empty: {}", HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

}
