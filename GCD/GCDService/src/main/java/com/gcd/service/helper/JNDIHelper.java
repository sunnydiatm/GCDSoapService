package com.gcd.service.helper;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDIHelper {
	private static JNDIHelper instance = null;
	private int ssoMaxThresold;

	private static final String jNDI_LOOKUP = "jndi/ssoMaxThresold";

	protected JNDIHelper() {
		try {
			InitialContext  ctx = new InitialContext();
			String thresold = (String)ctx.lookup(jNDI_LOOKUP);
			if(thresold != null && !thresold.equals("")){
				this.ssoMaxThresold = Integer.parseInt(thresold);
			}
		}catch(NamingException ex) {
			ssoMaxThresold = 0;
		}
	}

	public static JNDIHelper getInstance() {
		if (instance == null) {
			instance = new JNDIHelper();
		}
		return instance;
	}

	public int getSsoMaxThresold() {
		return this.ssoMaxThresold;
	}

	public void setSsoMaxThresold(int ssoMaxThresold) {
		this.ssoMaxThresold = ssoMaxThresold;
	}
}
