package com.boaglio.corridaOracle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fernando Boaglio
 * http://www.boaglio.com
 *
 */
class TesteDeInternalProcedure {

	 public static void main (String[] args) throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:default:connection:");
		CargaDeDadosDoCEP.runLoad(conn,CargaDeDadosDoCEP.INTERNAL_JAVA_PROC);

	}
}