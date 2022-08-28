package com.study.transactionseparation.comment;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.transactionseparation.push.PushEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final ApplicationEventPublisher applicationEventPublisher;

	@Transactional
	public Long create(String writer, String content) {
		Comment comment = commentRepository.save(new Comment(writer, content));
		applicationEventPublisher.publishEvent(new PushEvent(comment));
		return comment.getId();
	}
}
