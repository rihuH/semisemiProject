package com.kh.quali.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ResponseData {
	
	private String status; // 응답 코드 지정 200, 404, 500 등
	private String message; // 보여줄 메세지
	private Object data; // 불러올 값의 자료형을 모르기때문에 무엇이든 들어갈 수 있는 Object로 받음
}
