package com.study.transactionseparation.push;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.study.transactionseparation.comment.Comment;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PushEventListener {

	private final PushNotificationRepository pushNotificationRepository;

	@TransactionalEventListener
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Async
	public void handle(PushEvent event) {
		Comment comment = (Comment)event.getEntity();
		String writer = comment.getWriter();

		validateWriter(writer);

		String message = writer + "님이 댓글을 남겼습니다.";
		PushNotification pushNotification = new PushNotification(message);
		pushNotificationRepository.save(pushNotification);
	}

	private void validateWriter(String writer) {
		if (writer.equals("ex")) {
			throw new IllegalArgumentException("알림 로직에서 예외가 발생");
		}
	}
}
