package owl.discovery.findwally.service;

public class ZipExploreServiceException extends RuntimeException{
	private static final long serialVersionUID = -6086963489687316145L;

	public ZipExploreServiceException() {
		super();
	}

	public ZipExploreServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ZipExploreServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ZipExploreServiceException(String message) {
		super(message);
	}

	public ZipExploreServiceException(Throwable cause) {
		super(cause);
	}
}
