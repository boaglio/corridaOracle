package com.boaglio.corridaOracle;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Fernando Boaglio
 * http://www.boaglio.com
 *
 */
class TesteDeThin {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/boaglio1", "boaglio1", "boaglio1");
		try {
			CargaDeDadosDoCEP.runLoad(conn,CargaDeDadosDoCEP.DRIVER_JDBC_THIN);
		} finally {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}
	}
}