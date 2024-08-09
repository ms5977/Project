package com.blog.main;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;

public class KwtSecretKeyMaker {
	
	@Test
	public void genrateSecretKey()
	{
		SecretKey key = Jwts.SIG.HS512.key().build();
		String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());
		System.out.printf("\nKey =[%s]\n",encodedKey);
	}
}
