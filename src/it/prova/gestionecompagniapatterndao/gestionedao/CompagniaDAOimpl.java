package it.prova.gestionecompagniapatterndao.gestionedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import it.prova.gestionecompagniapatterndao.model.Compagnia;
import it.prova.gestionepatterndao.dao.AbstractMySQLDAO;


public class CompagniaDAOimpl extends AbstractMySQLDAO implements CompagniaDAO {
	
	public CompagniaDAOimpl(Connection connection) {
		super(connection);
	}

	
	public List<Compagnia> list() throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("Select * from compagnia")) {

			while (rs.next()) {
				Compagnia compagniaItem = new Compagnia();
				compagniaItem.setRagionesociale(rs.getString("ragionesociale"));
				compagniaItem.setFatturatoAnnuo(rs.getDouble("fatturatoannuo"));
				compagniaItem.setDataFondazione(
						rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);
				compagniaItem.setId(rs.getLong("id"));
				result.add(compagniaItem);
			}
		}
		return result;
	}

	
	public Compagnia get(Long idInput) throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Compagnia result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from compagnia where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Compagnia();
					result.setRagionesociale(rs.getString("ragionesociale"));
					result.setFatturatoAnnuo(rs.getDouble("fatturatoannuo"));
					result.setDataFondazione(
							rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);

					result.setId(rs.getLong("ID"));
				} else {
					result = null;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	
	public int update(Compagnia input) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE compagnia SET ragionesociale=?, fatturatoannuo=?, datafondazione=? where id=?;")) {
			ps.setString(1, input.getRagionesociale());
			ps.setDouble(2, input.getFatturatoAnnuo());

			ps.setDate(3, java.sql.Date.valueOf(input.getDataFondazione()));
			ps.setLong(4, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	
	public int insert(Compagnia input) throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO impiegato (ragionesociale, fatturatoannuo, datafondazione) VALUES (?, ?, ?);")) {
			ps.setString(1, input.getRagionesociale());
			ps.setDouble(2, input.getFatturatoAnnuo());
			ps.setDate(3, java.sql.Date.valueOf(input.getDataFondazione()));

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	
	public int delete(Compagnia input) throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM compagnia WHERE ID=?")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	public List<Compagnia> findByExample(Compagnia input) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
				if (isNotActive())
					throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

				if (input == null)
					throw new Exception("Valore di input non ammesso.");

				ArrayList<Compagnia> result = new ArrayList<Compagnia>();
				Compagnia compagniaTemp = null;

				String query = "select * from compagnia where 1=1 ";
				if (input.getRagionesociale() != null && !input.getRagionesociale().isEmpty()) {
					query += " and ragionesociale like '" + input.getRagionesociale() + "%' ";
				}
				if (input.getFatturatoAnnuo() != 0) {
					query += " and fatturatoannuo like '" + input.getFatturatoAnnuo() + "%' ";
				}

				
				if (input.getDataFondazione() != null) {
					query += " and datafondazione='" + java.sql.Date.valueOf(input.getDataFondazione()) + "' ";
				}

				try (Statement ps = connection.createStatement()) {
					ResultSet rs = ps.executeQuery(query);

					while (rs.next()) {
						compagniaTemp = new Compagnia();
						compagniaTemp.setRagionesociale(rs.getString("ragioneSociale"));
						compagniaTemp.setFatturatoAnnuo(rs.getDouble("fatturatoAnnuo"));
						compagniaTemp.setDataFondazione(
								rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);
						compagniaTemp.setId(rs.getLong("ID"));
						result.add(compagniaTemp);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
				return result;
		
	}

	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(LocalDate dataInput) throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva");

		if (dataInput == null)
			throw new Exception("Valore non ammesso");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();

		try (PreparedStatement ps = connection.prepareStatement(
				"select * from compagnia c inner join impiegato i on c.id=i.id_compagnia where i.dataassunzione > ? ;")) {
			ps.setDate(1, java.sql.Date.valueOf(dataInput));

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					Compagnia compagniaItem = new Compagnia();
					compagniaItem.setRagionesociale(rs.getString("ragionesociale"));
					compagniaItem.setFatturatoAnnuo(rs.getDouble("fatturatoannuo"));
					compagniaItem.setDataFondazione(
							rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);
					compagniaItem.setId(rs.getLong("id"));

					result.add(compagniaItem);
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return result;
		}		
	}

	public List<Compagnia> findAllByRagioneSocialeContiene(String ragioneSocialeInput) throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();

		try (PreparedStatement ps = connection.prepareStatement(
				"select * from compagnia C  where c.ragionesociale like ? ")) {
			ps.setString(1, "%" + ragioneSocialeInput + "%");

			try (ResultSet rs = ps.executeQuery();) {

				while (rs.next()) {
					Compagnia compagniaItem = new Compagnia();
					compagniaItem.setRagionesociale(rs.getString("ragionesociale"));
					compagniaItem.setFatturatoAnnuo(rs.getDouble("fatturatoannuo"));
					compagniaItem.setDataFondazione(
							rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);
					compagniaItem.setId(rs.getLong("id"));
					result.add(compagniaItem);
				}
			}
			return result;
		}

	}

	public List<Compagnia> findAllByCodFisImpiegatoContiene(String parteCodiceFiscaleInput) throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();

		try (PreparedStatement ps = connection.prepareStatement(
				"select * from compagnia c inner join impiegato i on c.id=i.id_compagnia where i.codicefiscale like ? ;")) {
			ps.setString(1, "%" + parteCodiceFiscaleInput+ "%");

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					Compagnia compagniaItem = new Compagnia();
					compagniaItem.setRagionesociale(rs.getString("ragionesociale"));
					compagniaItem.setFatturatoAnnuo(rs.getDouble("fatturatoannuo"));
					compagniaItem.setDataFondazione(
							rs.getDate("datafondazione") != null ? rs.getDate("datafondazione").toLocalDate() : null);
					compagniaItem.setId(rs.getLong("id"));
					result.add(compagniaItem);
				}
			}
			return result;

		}
	}


	
	
	


	
}
