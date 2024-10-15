package com.semiProject.vo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardVO {
	private Long idx;
	private Long cate; // 1000 : 공지사항, 2000 : 자유게시판, 3000 : 갤러리게시판
	private String title;
	private String content;
	private String regID;
	private LocalDateTime regDate;
}
