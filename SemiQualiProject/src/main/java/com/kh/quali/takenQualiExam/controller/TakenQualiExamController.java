package com.kh.quali.takenQualiExam.controller;

import org.springframework.stereotype.Controller;

import com.kh.quali.common.ModelAndViewUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TakenQualiExamController {

	private final ModelAndViewUtil mv;
	private final TakenQualiExamServiceImpl ts;
	
}
