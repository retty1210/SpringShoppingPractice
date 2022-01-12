package com.shoppingmall.web.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//어노테이션을 내 마음대로 만들기
@Target(ElementType.METHOD)//어노테이션 적용 타입
@Retention(RetentionPolicy.RUNTIME) //어노테이션에 대한 라이프 사이클
public @interface Perm {
	
	/*
	 * table name
	 */
	String table();
	
	/*
	 * crud
	 * 	 - 조회: select
	 * 	 - 추가: insert
	 * 	 - 수정: update
	 * 	 - 삭제: delete
	 */
	String crud();
	
	/*
	 * default는 일부러 안 넣은 상태
	 */
}
