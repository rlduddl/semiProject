package com.semiProject.vo;

import java.time.LocalDateTime;

import com.semiProject.vo.MemberVO;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberVO {
	private Long idx;
	private int isAdmin;
	private String userID;
	private String password;
	private String username;
	private String email;
	private LocalDateTime regDate;
	private int isUse;
	private LocalDateTime dropDate;
	
	@Builder
	public MemberVO(Long idx, int isAdmin, String userID, String password, String username, String email,
			LocalDateTime regDate, int isUse, LocalDateTime dropDate) {
		this.idx = idx;
		this.isAdmin = isAdmin;
		this.userID = userID;
		this.password = password;
		this.username = username;
		this.email = email;
		this.regDate = regDate;
		this.isUse = isUse;
		this.dropDate = dropDate;
	}

	
}




