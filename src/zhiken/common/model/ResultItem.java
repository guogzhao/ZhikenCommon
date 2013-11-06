package zhiken.common.model;

public class ResultItem {
	private boolean success;

	public boolean success() {
		return success;
	}

	public void success(boolean success) {
		this.success = success;
	}

	private String message;

	public String message() {
		return message;
	}

	public void message(String message) {
		this.message = message;
	}
}
