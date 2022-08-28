package com.study.transactionseparation.comment;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.study.transactionseparation.push.PushNotificationRepository;

// @Transactional
@SpringBootTest
class CommentServiceTest {

	@Autowired
	private CommentService commentService;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PushNotificationRepository pushNotificationRepository;

	@AfterEach
	void clear() {
		commentRepository.deleteAll();
		pushNotificationRepository.deleteAll();
	}

	@DisplayName("댓글을 저장하면 알림도 저장된다.")
	@Test
	void createComment_saveNotification() {
		// given
		String writer = "does";
		String content = "댓글 내용입니다.";

		// when
		Long commentId = commentService.create(writer, content);

		// then
		Optional<Comment> findComment = commentRepository.findById(commentId);
		assertAll(
			() -> assertThat(findComment).isPresent(),
			() -> assertThat(findComment.get().getWriter()).isEqualTo("does"),
			() -> assertThat(pushNotificationRepository.findAll()).hasSize(1)
		);
	}

	@DisplayName("알림에 예외가 발생해도 댓글은 저장된다.")
	@Test
	void exceptionNotification_createComment() {
		// given
		String writer = "ex";
		String content = "댓글 내용입니다.";

		// when
		Long commentId = commentService.create(writer, content);

		// then
		Optional<Comment> findComment = commentRepository.findById(commentId);
		assertAll(
			() -> assertThat(findComment).isPresent(),
			() -> assertThat(findComment.get().getWriter()).isEqualTo("ex"),
			() -> assertThat(pushNotificationRepository.findAll()).hasSize(0)
		);
	}
}
