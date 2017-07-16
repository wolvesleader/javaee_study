package com.quincy.rouse.rousejdbc;

import java.sql.Connection;

public interface IRouseJdbc {
	
	public Connection getConnectionFromThread();

}
