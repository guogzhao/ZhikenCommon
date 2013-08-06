package zhiken.common.net;

public class Host {
	public Host() {
		super();
	}

	public Host(String name, int port) {
		super();
		this.name = name;
		this.port = port;
	}

	private String name;
	private int port;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
