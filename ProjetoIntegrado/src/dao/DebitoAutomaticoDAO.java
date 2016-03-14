	package dao;
	
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	
	import debitoAutomatico.DebitoAutomatico;
	import model.Conta;
	
	public class DebitoAutomaticoDAO {
		private AcessoBD acesso;
		private Conta conta;
		private Connection conn;
		private static DebitoAutomatico debito;
	
		public DebitoAutomaticoDAO(Conta conta, DebitoAutomatico debito) throws SQLException {
			acesso = new AcessoBD();
			conn = acesso.obtemConexao();
			this.debito = debito;
			this.conta = conta;
		}
	
		public void incluir() {
			String sqlInsert = "INSERT INTO debitoAutomatico(conta, tipo, codOperadora, dataDebito, codConsumidor) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stm = null;
			java.sql.Date date = getCurrentJavaSqlDate();
			int aux = (int) (Math.random() * 10000000);
			try {
				stm = conn.prepareStatement(sqlInsert);
				stm.setInt(1, conta.getConta());
				stm.setString(2, "" + 'D');
				stm.setString(3, debito.getCodOp());
				stm.setDate(4, getCurrentJavaSqlDate());
				stm.setString(5, debito.getCodCo());
				stm.execute();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			} finally {
				if (stm != null) {
					try {
						stm.close();
					} catch (SQLException e1) {
						System.out.print(e1.getStackTrace());
					}
				}
			}
		}
	
		public static java.sql.Date getCurrentJavaSqlDate() {
			return new java.sql.Date(debito.getData().getTime());
		}
	}
