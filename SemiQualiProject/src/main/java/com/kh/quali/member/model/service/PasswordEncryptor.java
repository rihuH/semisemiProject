package com.kh.quali.member.model.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PasswordEncryptor {

	// ��й�ȣ ��ȣȭ, ���� ������ ���� Ŭ����
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	
	public String encode(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
	
	public boolean matches(String rawPassword, String encodedPassword) {

		// System.out.println(rawPassword);
		// System.out.println(encodedPassword);
		
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
	
}
