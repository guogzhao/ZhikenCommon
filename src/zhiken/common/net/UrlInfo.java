package zhiken.common.net;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import zhiken.common.util.ListUtil;

public class UrlInfo {
	public static final String PROTOCOL_HTTP = "http";
	private String protocol = PROTOCOL_HTTP;// pact//deal
	private Host host;
	private String path;
	private Hashtable<String, String> args;

	public UrlInfo(String spec) {
		super();
		initForUrl(spec);
	}

	private void initForUrl(String spec) {
		URL url = null;
		try {
			url = new URL(spec);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		protocol = url.getProtocol();
		host = new Host(url.getHost(), url.getPort());
		path = url.getPath();
		args = getUrlParams(url.getFile());
	}

	public Hashtable<String, String> getUrlParams(String url) {
		Hashtable<String, String> result = new Hashtable<String, String>();
		{
			String paramsStr = url.substring(url.indexOf("?"));
			String[] params = paramsStr.split("&");
			for (int i = 0; i < params.length; i++) {
				try {
					String[] argobj = params[i].split("=");
					result.put(argobj[0], argobj[1]);
				} catch (Exception ex) {
				}
			}
		}
		return result;
	}

	public UrlInfo(Host host, String path) {
		super();
		this.host = host;
		this.path = path;
	}

	public UrlInfo(Host host, String path, Hashtable<String, String> args) {
		super();
		this.host = host;
		this.path = path;
		this.args = args;
	}

	public UrlInfo(String protocol, Host host, String path, Hashtable<String, String> args) {
		super();
		this.protocol = protocol;
		this.host = host;
		this.path = path;
		this.args = args;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Hashtable<String, String> getArgs() {
		return args;
	}

	public void setArgs(Hashtable<String, String> args) {
		this.args = args;
	}

	@Override
	public String toString() {
		return toURL().toString();
	}

	public URL toURL() {
		try {
			if (args != null) {
				return new URL(protocol, host.getName(), host.getPort(), path);
			} else {
				return new URL(protocol, host.getName(), host.getPort(), path + UrlHelper.getUrlParamsString(ListUtil.tableToList(args)));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
