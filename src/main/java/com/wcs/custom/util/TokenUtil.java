package com.wcs.custom.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wcs.mall.user.entity.MallUser;

import java.util.Calendar;
import java.util.Date;

/**
 * @author wcs
 */
public class TokenUtil {

    public static void main(String[] args) {
        MallUser mallUser = new MallUser();
        mallUser.setId("123123L");
        mallUser.setUsername("13047911112");
        mallUser.setName("wcs");
        String token = getToken(mallUser);
        System.out.println(token);
    }

    public static String getToken(MallUser mallUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("mall");
            // 设置token过期时间 30天后过期
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();
            calendar.add(Calendar.DATE, 30);
            return JWT.create()
                    .withIssuer("wcs")
                    .withExpiresAt(calendar.getTime())
                    .withClaim("id", mallUser.getId())
                    .withClaim("username", mallUser.getUsername())
                    .withIssuedAt(currentDate)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            throw new JWTCreationException(exception.getMessage(), exception.getCause());
        }
    }

    public static DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("mall");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("wcs")
                    .build(); //Reusable verifier instance
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            return null;
        }
    }

    public static String getUserId(String token) {
        DecodedJWT decodedJwt = TokenUtil.verifyToken(token);
        assert decodedJwt != null;
        return decodedJwt.getClaim("id").asString();
    }
}