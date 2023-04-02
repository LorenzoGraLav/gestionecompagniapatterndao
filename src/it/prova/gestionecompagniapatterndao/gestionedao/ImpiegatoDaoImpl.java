package it.prova.gestionecompagniapatterndao.gestionedao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import it.prova.gestionecompagniapatterndao.model.Compagnia;
import it.prova.gestionecompagniapatterndao.model.Impiegato;
import it.prova.gestionepatterndao.dao.AbstractMySQLDAO;

public class ImpiegatoDaoImpl extends AbstractMySQLDAO implements ImpiegatoDAO {
	public ImpiegatoDaoImpl(Connection connection) {
		super(connection);
	
	}
	
	public List<Impiegato> list() throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva");

		ArrayList<Impiegato> result = new ArrayList<Impiegato>();

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("Select * from impiegato")) {

			while (rs.next()) {
				Impiegato impiegatoTemp = new Impiegato();
				
				impiegatoTemp.setNome(rs.getString("nome"));
				impiegatoTemp.setCognome(rs.getString("Cognome"));
				impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
				impiegatoTemp.setDataDiNascita(
						rs.getDate("datanascita") != null ? rs.getDate("datanascita").toLocalDate() : null);
				impiegatoTemp.setDataAssunzione(
						rs.getDate("dataassunzione") != null ? rs.getDate("dataassunzione").toLocalDate() : null);
				impiegatoTemp.setId(rs.getLong("id"));
				result.add(impiegatoTemp);			
			
				
				result.add(impiegatoTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	
	
	public Impiegato get(Long idInput) throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Impiegato result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from impiegato where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Impiegato();
					result.setNome(rs.getString("NOME"));
					result.setCognome(rs.getString("COGNOME"));
					result.setCodiceFiscale(rs.getString("codicefiscale"));
					result.setDataDiNascita(rs.getDate("DATECREATED") != null ? rs.getDate("DATECREATED").toLocalDate() : null);
					result.setDataAssunzione(
							rs.getDate("DATECREATED") != null ? rs.getDate("DATECREATED").toLocalDate() : null);
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
	
	public int update(Impiegato input) throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE compagnia SET nome=?, cognome=?, codice fiscale=? ,datanascita=? , dataassunzione=? , where id=?;")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getCodiceFiscale());
			ps.setDate(4, java.sql.Date.valueOf(input.getCodiceFiscale()));
			ps.setDate(5, java.sql.Date.valueOf(input.getDataAssunzione()));
			ps.setLong(6, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	public int insert(Impiegato input) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
				if (isNotActive())
					throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

				if (input == null)
					throw new Exception("Valore di input non ammesso.");

				int result = 0;
				try (PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO impiegato (nome, cognome, codicefiscale , datanascita, dataassunzione) VALUES (?, ?, ?, ?, ?);")) {
					ps.setString(1, input.getNome());
					ps.setString(2, input.getCognome());
					ps.setString(3, input.getCodiceFiscale());
					ps.setDate(4, java.sql.Date.valueOf(input.getDataDiNascita()));
					ps.setDate(5, java.sql.Date.valueOf(input.getDataAssunzione()));
					
					result = ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
		return result;
	}
	
	public int delete(Impiegato input) throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM impiegato WHERE ID=?")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public List<Impiegato> findByExample(Impiegato input) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Impiegato> result = new ArrayList<Impiegato>();
		

		String query = "select * from compagnia where 1=1 ";
		if (input.getNome() != null && !input.getNome().isEmpty()) {
			query += " and nome like '" + input.getNome() + "%' ";
		}
		if (input.getCognome() != null && !input.getCognome().isEmpty()) {
			query += " and cognome like '" + input.getCognome() + "%' ";
		}
		if (input.getCodiceFiscale() != null && !input.getCodiceFiscale().isEmpty()) {
			query += " and codicefiscale like '" + input.getCodiceFiscale() + "%' ";
		}

		if (input.getDataDiNascita() != null) {
			query += " and datanascita='" + java.sql.Date.valueOf(input.getDataDiNascita()) + "' ";
		}
		

		if (input.getDataAssunzione() != null) {
			query += " and dataassunzione='" + java.sql.Date.valueOf(input.getDataAssunzione()) + "' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				Impiegato impiegatoTemp = new Impiegato();
				impiegatoTemp.setNome(rs.getString("nome"));
				impiegatoTemp.setCognome(rs.getString("cognome"));
				impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
				impiegatoTemp.setDataDiNascita(
						rs.getDate("datanascita") != null ? rs.getDate("datanascita").toLocalDate() : null);
				impiegatoTemp.setDataAssunzione(
						rs.getDate("dataassunzione") != null ? rs.getDate("dataassunzione").toLocalDate() : null);
				
				impiegatoTemp.setId(rs.getLong("ID"));
				result.add(impiegatoTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	public List<Impiegato> findAllByCompagnia(Compagnia compagniaInput) throws Exception {
		
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		List<Impiegato> result = new ArrayList<Impiegato>();
		if (compagniaInput == null)
			throw new RuntimeException("Impossibile caricare Compagnia: id mancante!");
		
		try (
				PreparedStatement ps = connection.prepareStatement("select* from impiegato i inner join compagnia c on c.id=i.id_compagnia where id_compagnia=?; ")) {
			ps.setLong(1, compagniaInput.getId());
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Impiegato temp = new Impiegato();
					temp.setId(rs.getLong("id"));
					temp.setNome(rs.getString("nome"));
					temp.setCognome(rs.getString("cognome"));
					temp.setCodiceFiscale(rs.getString("codicefiscale"));
					temp.setDataDiNascita(rs.getDate("datanascita")!= null ? rs.getDate("datanascita").toLocalDate() : null);
					temp.setDataAssunzione(rs.getDate("dataassunzione")!= null ? rs.getDate("dataassunzione").toLocalDate() : null);
					result.add(temp);
				}
				compagniaInput.setImpiegati(result);;
			} // niente catch qui
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;

	}


	public int countByDataFondazioneCompagniaGreaterThan(LocalDate dataInput) throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (dataInput == null)
			throw new Exception("Valore di input non ammesso.");

		int count = 0;

		try (PreparedStatement ps = connection.prepareStatement(
				"select count(datafondazione) from compagnia c inner join impiegato i on c.id=i.id_compagnia where datafondazione > ? ;")) {
			ps.setDate(1, java.sql.Date.valueOf(dataInput));

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					count = rs.getInt("count(datafondazione)");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return count;
	}

	public List<Impiegato> findAllByCompagniaConFatturatoMaggioreDi(int fattura) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		List<Impiegato> result = new ArrayList<Impiegato>();
		if (fattura <= 1)
			throw new RuntimeException("Impossibile caricare Compagnia: fatturato mancante!");
		
		try (
				PreparedStatement ps = connection.prepareStatement("select* from impiegato i inner join compagnia c on c.id=i.compagnia_id where compagnia_id=? and c.fatturatoannuo > ?; ")) {
			ps.setInt(1, fattura);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Impiegato temp = new Impiegato();
					temp.setId(rs.getLong("id"));
					temp.setNome(rs.getString("nome"));
					temp.setCognome(rs.getString("cognome"));
					temp.setCodiceFiscale(rs.getString("codicefiscale"));
					temp.setDataDiNascita(rs.getDate("datanascita")!= null ? rs.getDate("datanascita").toLocalDate() : null);
					temp.setDataAssunzione(rs.getDate("dataassunzione")!= null ? rs.getDate("dataassunzione").toLocalDate() : null);
					result.add(temp);
				}
			
			} // niente catch qui
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
		
	}

	public List<Impiegato> findAllErroriAssunzione() throws Exception  {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		List<Impiegato> result = new ArrayList<Impiegato>();
		
		
		try (
				PreparedStatement ps = connection.prepareStatement("select* from impiegato i inner join compagnia c on c.id=i.id_compagnia where id_compagnia=? and i.dataassunzione > c.datafondazione; ")) {
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Impiegato temp = new Impiegato();
					temp.setId(rs.getLong("id"));
					temp.setNome(rs.getString("nome"));
					temp.setCognome(rs.getString("cognome"));
					temp.setCodiceFiscale(rs.getString("codicefiscale"));
					temp.setDataDiNascita(rs.getDate("datanascita")!= null ? rs.getDate("datanascita").toLocalDate() : null);
					temp.setDataAssunzione(rs.getDate("dataassunzione")!= null ? rs.getDate("dataassunzione").toLocalDate() : null);
					result.add(temp);
				}
			
			} // niente catch qui
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
	
	

}
