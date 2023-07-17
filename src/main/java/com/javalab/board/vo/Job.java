package com.javalab.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 직업(직무) 클래스
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Job {
	private String jobId;
	private String jobTitle;	
}
