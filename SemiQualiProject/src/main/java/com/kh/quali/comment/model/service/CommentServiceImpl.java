package com.kh.quali.comment.model.service;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.kh.quali.comment.model.dao.CommentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

	private final CommentMapper mapper;
	private final ServletContext context;
	
}
