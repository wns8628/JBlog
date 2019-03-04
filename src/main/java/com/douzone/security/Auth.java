package com.douzone.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD , ElementType.TYPE}) //메소드에만붙을수있따.
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	public enum Role{ ADMIN, USER }
	
	Role value() default Role.USER;
	
	
	//text 어노테이션안에 값넣는법
	//String value() default "USER";
	//int method() default 1;
}
