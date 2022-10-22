package com.votoElectronico.beans;

public class Credenciales {
	
	private String url;
	private String username;
	private String password;
	private Integer timeout;
	private boolean read_only;
	private Integer maximunPoolSize;
	private String pool_name;
	
	public Credenciales() {
	}

	public Credenciales(String url, String username, String password, Integer timeout, boolean read_only,
			Integer maximunPoolSize, String pool_name) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.timeout = timeout;
		this.read_only = read_only;
		this.maximunPoolSize = maximunPoolSize;
		this.pool_name = pool_name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public boolean isRead_only() {
		return read_only;
	}

	public void setRead_only(boolean read_only) {
		this.read_only = read_only;
	}

	public Integer getMaximunPoolSize() {
		return maximunPoolSize;
	}

	public void setMaximunPoolSize(Integer maximunPoolSize) {
		this.maximunPoolSize = maximunPoolSize;
	}

	public String getPool_name() {
		return pool_name;
	}

	public void setPool_name(String pool_name) {
		this.pool_name = pool_name;
	}
	
	/*
	 * gcp.gcpcupon.connection-timeout=20
gcp.gcpcupon.read-only=false
gcp.gcpcupon.maximunPoolSize=20
gcp.gcpcupon.jdbc-url=jdbc:mysql://34.75.150.144/cupon?useSSL=false&serverTimezone=America/Lima&noAccessToProcedureBodies=true
gcp.gcpcupon.username=sistema-cupon-dev
gcp.gcpcupon.password=s1st3m4-cvp0n.D3v
gcp.gcpcupon.pool-name=GENERAR-POOL
	 * */
}
