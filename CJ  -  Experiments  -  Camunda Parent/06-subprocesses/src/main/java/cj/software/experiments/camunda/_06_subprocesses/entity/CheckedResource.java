package cj.software.experiments.camunda._06_subprocesses.entity;

import java.io.Serializable;

public class CheckedResource
		implements
		Serializable
{
	private static final long serialVersionUID = 1L;

	private String databaseConnection;

	private String user;

	private String password;

	private String selectStatement;

	private String resourceUrl;

	private long limit;

	public String getDatabaseConnection()
	{
		return this.databaseConnection;
	}

	public void setDatabaseConnection(String pDatabaseConnection)
	{
		this.databaseConnection = pDatabaseConnection;
	}

	public String getUser()
	{
		return this.user;
	}

	public void setUser(String pUser)
	{
		this.user = pUser;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String pPassword)
	{
		this.password = pPassword;
	}

	public String getSelectStatement()
	{
		return this.selectStatement;
	}

	public void setSelectStatement(String pSelectStatement)
	{
		this.selectStatement = pSelectStatement;
	}

	public long getLimit()
	{
		return this.limit;
	}

	public void setLimit(long pLimit)
	{
		this.limit = pLimit;
	}

	public String getResourceUrl()
	{
		return this.resourceUrl;
	}

	public void setResourceUrl(String pResourceUrl)
	{
		this.resourceUrl = pResourceUrl;
	}
}
