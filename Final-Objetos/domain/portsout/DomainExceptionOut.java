package portsout;

public class DomainExceptionOut extends RuntimeException {

	public DomainExceptionOut(String msg, Exception e) {
		super(msg, e);
	}
}
