package com.kh.quali.application.model.service;

import java.util.List;
import java.util.Map;

import com.kh.quali.application.model.vo.Application;

public interface ApplicationService {

	Map<String, Object> insertApplication(Long examNo, Long examLocationNo, int memNo);

	Map<String, Object> selectApplicationList(int memNo, Long examLoacationNo);

}
