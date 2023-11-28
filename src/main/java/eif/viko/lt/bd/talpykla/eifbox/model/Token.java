package eif.viko.lt.bd.talpykla.eifbox.model;

import eif.viko.lt.bd.talpykla.eifbox.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// TODO MAKE DATABASE TABLE
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
  public Integer id;
  //@Column(unique = true)
  public String token;
  //@Enumerated(EnumType.STRING)
  public TokenType tokenType = TokenType.BEARER;
  public boolean revoked;
  public boolean expired;
  //@ManyToOne(fetch = FetchType.LAZY)
  //@JoinColumn(name = "user_id")
  public User user;
}