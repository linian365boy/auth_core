package cn.rainier.nian.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNameExistException extends AuthenticationException{

	public UserNameExistException(String msg) {
		super(msg);
	}
	
	@Deprecated
	public UserNameExistException(String msg, Object extraInformation) {
		super(msg,extraInformation);
	}
	
	public UserNameExistException(String msg, Throwable t) {
		super(msg,t);
	}
	
}
