package com.kh.quali.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ResponseData {
	
	private String status; // ���� �ڵ� ���� 200, 404, 500 ��
	private String message; // ������ �޼���
	private Object data; // �ҷ��� ���� �ڷ����� �𸣱⶧���� �����̵� �� �� �ִ� Object�� ����
}
