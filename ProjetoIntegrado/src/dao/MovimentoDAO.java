package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import extrato.Extrato;
import extrato.ExtratoTO;
import model.Conta;

public class MovimentoDAO {
	private AcessoBD acesso;
	private Conta conta;
	private Connection conn;

	public MovimentoDAO(Conta conta) throws SQLException {
		acesso = new AcessoBD();
		conn = acesso.obtemConexao();
		this.conta = conta;
	}

	public void incluir(char tipo, double valor) {
		String sqlInsert = "INSERT INTO HISTORICO(conta, dataLancamento, tipo, numeroDoc, valor) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement stm = null;
		java.sql.Date date = getCurrentJavaSqlDate();
		int aux = (int) (Math.random() * 10000000);
		try {
			stm = conn.prepareStatement(sqlInsert);
			stm.setInt(1, conta.getConta());
			stm.setDate(2, getCurrentJavaSqlDate());
			stm.setString(3, "" + tipo);
			stm.setInt(4, aux);
			stm.setDouble(5, valor);
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
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
	public static java.sql.Date getCurrentJavaSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}
	
	public ArrayList<Extrato> carregarExtrato(Date de, Date ate) {
		String sqlSelect = "SELECT * FROM historico WHERE conta = ? and dataLancamento between (?) and (?)";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<Extrato> lp = new ArrayList<Extrato>();
		try {
			stm = conn.prepareStatement(sqlSelect);
			stm.setInt(1, conta.getConta());
			stm.setDate(2, getCurrentJavaSqlDate(de));
			stm.setDate(3, getCurrentJavaSqlDate(ate));
			rs = stm.executeQuery();
			while (rs.next()) {
				int idH = rs.getInt(1);
				Date d = rs.getDate(3);
				char t = rs.getString(4).toUpperCase().charAt(0);
				int n = rs.getInt(5);
				double v = rs.getDouble(6);
				Extrato p = new Extrato(idH, d, t, n, v);
				lp.add(p);
			}
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
		return (lp);
	}
	public ArrayList<ExtratoTO> carregarExtratoTO(Date de, Date ate) {
		String sqlSelect = "SELECT * FROM historico WHERE conta = ? and dataLancamento between (?) and (?)";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<ExtratoTO> lp = new ArrayList<ExtratoTO>();
		try {
			stm = conn.prepareStatement(sqlSelect);
			stm.setInt(1, conta.getConta());
			stm.setDate(2, getCurrentJavaSqlDate(de));
			stm.setDate(3, getCurrentJavaSqlDate(ate));
			rs = stm.executeQuery();
			while (rs.next()) {
				int idH = rs.getInt(1);
				Date d = rs.getDate(3);
				char t = rs.getString(4).toUpperCase().charAt(0);
				int n = rs.getInt(5);
				double v = rs.getDouble(6);
				ExtratoTO p = new ExtratoTO(idH, d, t, n, v);
				lp.add(p);
			}
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
		return (lp);
	}
}
