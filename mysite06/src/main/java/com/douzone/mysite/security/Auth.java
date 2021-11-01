package com.douzone.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// https://victorydntmd.tistory.com/177?category=698080
// 접근 권한이 필요한 메서드 위에 @Auth 어노테이션을 작성하면 접근 권한이 있는 사용자인지 아닌지 판별 가능
// 어노테이션으로 사용하기 위해 interface앞에 @를 붙임
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) // 존속 기간
public @interface Auth {

	// public String value() default "USER";
	public String role() default "USER"; // role이라는 말을 쓰고 싶을 때

	// 디폴트 값을 false로 하는 test()
	// public boolean test() default false;
}
