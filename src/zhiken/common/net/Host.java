package zhiken.common.net;

public class Host {
	public Host() {
		super();
	}

	public Host(String name) {
		super();
		this.name = name;
	}

	public Host(String name, int port) {
		super();
		this.name = name;
		this.port = port;
	}

	private String name;
	private int port = -1;

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

	@Override
	public String toString() {
		return toHttpString();
	}

	public String toHttpString() {
		if (port < 0) {
			return "http://" + name;
		} else {
			return "http://" + name + ":" + port;
		}
	}
}
