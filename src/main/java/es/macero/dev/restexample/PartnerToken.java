package es.macero.dev.restexample;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Date;

class PartnerToken{

    private String _partnerId;
    private String _token;

    // Required for JSON deserialization
    PartnerToken() {
    }


    public String getPartnerId() {
        return this._partnerId;
    }

    public void setPartnerId(String partnerId) {
        this._partnerId = partnerId;
    }

    public String getToken() {
        return this._token;
    }
    
    public void setToken(String token) {
        this._token = token;
    }

    public String generateJWTToken(String partnerId) {
        String signatureSecret = "robinghosh";
        Algorithm algorithm = Algorithm.HMAC256(signatureSecret);

        Calendar c = Calendar.getInstance();
        Date currentDate = c.getTime();

        c.add(Calendar.HOUR, 24);
        Date expireDate = c.getTime();

        String jwtToken =  JWT.create()
        .withIssuer("smoothprogramming")
        .withSubject("demo")
        .withAudience("techgeeks")
        .withIssuedAt(currentDate)
        .withExpiresAt(expireDate)
        .withClaim("Claim1", partnerId)
        .withClaim("Claim2", "Value2")
        .sign(algorithm);
     

        return jwtToken;
    }

    /**
     * Verifies the token and decode the content.
     * @param jwtToken
     */
    public String verifyJWTToken(String jwtToken, String partnerId) {
        String signatureSecret = "robinghosh";
        Algorithm algorithm = Algorithm.HMAC256(signatureSecret);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("smoothprogramming")
                .withSubject("demo")
                .build();

        DecodedJWT decodedJWT = verifier.verify(jwtToken);
        String partnerIdFromToken=decodedJWT.getClaim(partnerId).asString();
        return partnerIdFromToken;
    }

    /**
     * Decode the JWT Token content.
     * @param jwtToken
     */
    public void decodeJWTToken(String jwtToken,String partnerId) {
        DecodedJWT decodedJWT = JWT.decode(jwtToken);
        System.out.println("Claim1 is "+ decodedJWT.getClaim(partnerId).asString());
        System.out.println("Claim2 is "+ decodedJWT.getClaim("Claim2").asString());
    }


}
