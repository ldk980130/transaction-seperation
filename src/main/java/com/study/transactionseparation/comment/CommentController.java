package com.study.transactionseparation.comment;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/comments")
	public ResponseEntity<Void> create(@RequestParam String writer, @RequestParam String content) {
		Long commentId = commentService.create(writer, content);
		return ResponseEntity.created(URI.create("/comments/" + commentId)).build();
	}
}
