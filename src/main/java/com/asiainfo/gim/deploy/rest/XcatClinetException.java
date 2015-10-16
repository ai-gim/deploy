package com.asiainfo.gim.deploy.rest;

public class XcatClinetException extends RuntimeException
{
	private static final long serialVersionUID = -2350069973431923892L;

	public int repsonseCode;

	public XcatClinetException(int repsonseCode, String message)
	{
		super(message);
		this.repsonseCode = repsonseCode;
	}
	
	public XcatClinetException(Throwable cause)
	{
		super(cause);
		this.repsonseCode = -1;
	}

	public XcatClinetException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public int getRepsonseCode()
	{
		return repsonseCode;
	}
}
