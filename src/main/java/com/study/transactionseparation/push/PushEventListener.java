package com.study.transactionseparation.push;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.study.transactionseparation.comment.Comment;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PushEventListener {

	private final PushNotificationRepository pushNotificationRepository;

	@EventListener
	public void handle(PushEvent event) {
		Comment comment = (Comment)event.getEntity();
		String message = comment.getWriter() + "님이 댓글을 남겼습니다.";
		PushNotification pushNotification = new PushNotification(message);
		pushNotificationRepository.save(pushNotification);
	}
}
