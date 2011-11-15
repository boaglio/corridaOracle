package com.boaglio.corridaOracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Fernando Boaglio
 * http://www.boaglio.com
 *
 */
public class CargaDeDadosDoCEP {

	static final String todos_ceps = "select * from cep";

	static final int DRIVER_JDBC_THIN=1;

	static final int DRIVER_JDBC_OCI=2;

	public  static final int INTERNAL_JAVA_PROC=3;

	public static void runLoad(Connection conexao,int tipoDeDriver) {

		Statement stmt = null;
		ResultSet rset = null;
		try {
			int idCorrida = buscaIdCorrida(conexao);
			stmt = conexao.createStatement();
			rset = stmt.executeQuery(todos_ceps);
			while (rset.next())
				validaDados(rset,conexao,tipoDeDriver,idCorrida);
		} catch (Exception ignore) {
		} finally {
			try {
				rset.close();
				stmt.close();
				if (tipoDeDriver!=CargaDeDadosDoCEP.INTERNAL_JAVA_PROC)
				   conexao.close();
			} catch (Exception ignore) {
			}
		}
	}

	private static int buscaIdCorrida(Connection conexao) {

		Statement stmt = null;
		ResultSet rset = null;
		int idCorrida =0;
		try {
			stmt = conexao.createStatement();
			rset = stmt.executeQuery("select corrida.nextval from dual");
			while (rset.next())
				idCorrida = rset.getInt(1);
		} catch (Exception ignore) {
		} finally {
			try {
				rset.close();
				stmt.close();
			} catch (Exception ignore) {
			}
		}
		return idCorrida;
	}

	private static void validaDados(ResultSet rset,Connection conexao,int tipoDeDriver,int idCorrida) throws SQLException {

		String cidade = rset.getString("cidade");
		String busca = "paulo";

		String SQL_CARGA="";
		if (tipoDeDriver==DRIVER_JDBC_THIN) {
			SQL_CARGA="INSERT INTO TESTE_DRIVER_THIN (ID,UF,CIDADE,BAIRRO,ENDERECO_CEP,LOGRADOURO,ID_CORRIDA) "+
            "VALUES (seq2.nextval,?,?,?,?,?,?)";
		} else if (tipoDeDriver==DRIVER_JDBC_OCI) {
			SQL_CARGA="INSERT INTO TESTE_DRIVER_OCI (ID,UF,CIDADE,BAIRRO,ENDERECO_CEP,LOGRADOURO,ID_CORRIDA) "+
			"VALUES (seq3.nextval,?,?,?,?,?,?)";
		} else	if (tipoDeDriver==INTERNAL_JAVA_PROC) {
			SQL_CARGA="INSERT INTO TESTE_JAVA_PROC (ID,UF,CIDADE,BAIRRO,ENDERECO_CEP,LOGRADOURO,ID_CORRIDA) "+
	        "VALUES (seq4.nextval,?,?,?,?,?,?)";
		}

		if (cidade==null) return;

		if (cidade.toLowerCase().indexOf(busca)>=0) {
			PreparedStatement pstmt = null;
			try {

				pstmt = conexao.prepareStatement(SQL_CARGA);

				pstmt.setString(1,rset.getString("uf"));
				pstmt.setString(2,cidade);
				pstmt.setString(3,rset.getString("bairro"));
				pstmt.setString(4,rset.getString("endereco_cep"));
				pstmt.setString(5,rset.getString("logradouro"));
				   pstmt.setInt(6,idCorrida);

				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
				} catch (Exception ignore) {
				}
			}
		}
	}

}
