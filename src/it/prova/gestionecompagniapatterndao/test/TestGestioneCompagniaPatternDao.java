package it.prova.gestionecompagniapatterndao.test;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagniapatterndao.connection.MyConnection;
import it.prova.gestionecompagniapatterndao.gestionedao.CompagniaDAO;
import it.prova.gestionecompagniapatterndao.gestionedao.CompagniaDAOimpl;
import it.prova.gestionecompagniapatterndao.gestionedao.ImpiegatoDAO;
import it.prova.gestionecompagniapatterndao.gestionedao.ImpiegatoDaoImpl;
import it.prova.gestionecompagniapatterndao.model.Compagnia;
import it.prova.gestionecompagniapatterndao.model.Impiegato;
import it.prova.gestionepatterndao.dao.Constants;

public class TestGestioneCompagniaPatternDao {

	public static void main(String[] args) {
		CompagniaDAO compagniaDAOInstance = null;
		ImpiegatoDAO impiegatoDAOInstance = null;

		// ##############################################################################################################
		// Grande novità: la Connection viene allestista dal chiamante!!! Non è più a
		// carico dei singoli metodi DAO!!!
		// ##############################################################################################################

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			// ecco chi 'inietta' la connection: il chiamante
			compagniaDAOInstance = new CompagniaDAOimpl(connection);
			impiegatoDAOInstance = new ImpiegatoDaoImpl(connection);

			System.out.println("in tabella compagnia ci sono " + compagniaDAOInstance.list() + " elementi");
			System.out.println("in tabella impiegato ci sono " + impiegatoDAOInstance.list() + " elementi");
			
		
//			testUpdateCompagnia (compagniaDAOInstance);
//			System.out.println("in tabella sono presenti "+ compagniaDAOInstance.list().size()+ " elementi.");

//			testInsertCompagnia(compagniaDAOInstance);
//			System.out.println("in tabella sono presenti " + compagniaDAOInstance.list().size() + " elementi.");
		
//			testDeleteCompagnia (compagniaDAOInstance);
//			System.out.println("in tabella sono presenti " + compagniaDAOInstance.list().size() + " elementi.");
			
//			testUpdateImpiegato(impiegatoDAOInstance);
//			System.out.println("in tabella impiegato sono presenti "+ impiegatoDAOInstance.list().size()+" elementi.");
		
//			testInsertImpiegato(impiegatoDAOInstance);
//			System.out.println("in tabella sono presenti "+ impiegatoDAOInstance.list().size()+ " elementi.");
		
//			testDeleteImpiegato(impiegatoDAOInstance);
//			System.out.println("in tabella sono presenti "+ impiegatoDAOInstance.list().size()+ " elementi.");
		
		
//			testFindAllByDataAssunzioneMaggioreDi(compagniaDAOInstance,impiegatoDAOInstance);
//			System.out.println("In tabella compagnia ci sono "+compagniaDAOInstance.list().size()+" elementi.");

			
//			testFindAllByRagioneSocialeContiene(compagniaDAOInstance);
//			System.out.println("in tabella sono presenti "+ compagniaDAOInstance.list().size()+" elementi.");
		
//			testFindAllByCodFisImpiegatoContiene(compagniaDAOInstance, impiegatoDAOInstance);
//			System.out.println("in tabella sono presenti" + compagniaDAOInstance.list().size()+ " elementi.");
		
		
//			testFindAllByCompagnia(compagniaDAOInstance, impiegatoDAOInstance);
//			System.out.println("in tabella sono presenti" + impiegatoDAOInstance.list().size()+ " elementi.");
		
			testCountByDataFondazioneCompagniaGreaterThan(compagniaDAOInstance, impiegatoDAOInstance);
			System.out.println("in tabella sono presenti "+ impiegatoDAOInstance.list().size()+ " elementi.");
		
//			testFindAllByCompagniaConFatturatoMaggioreDi(compagniaDAOInstance, impiegatoDAOInstance);
//			System.out.println("in tabella sono presenti "+ impiegatoDAOInstance.list().size()+ " elementi.");
			
//			testFindAllErroriAssunzione(compagniaDAOInstance, impiegatoDAOInstance);
//			System.out.println("in tabella sono presenti "+ impiegatoDAOInstance.list().size()+ " elementi.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}
	
		// =================================================================================================================
		// Test DeleteCompagnia

	public static void testDeleteCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {

		System.out.println(".......testDeleteCompagnia inizio.............");
		int quantiElementiInseriti = compagniaDAOInstance.insert(new Compagnia("GameStop", 20000, LocalDate.now()));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED, user da rimuovere non inserito");

		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		int numeroElementiPresentiPrimaDellaRimozione = elencoVociPresenti.size();
		if (numeroElementiPresentiPrimaDellaRimozione < 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED, non ci sono voci sul DB");

		Compagnia ultimoDellaLista = elencoVociPresenti.get(numeroElementiPresentiPrimaDellaRimozione - 1);
		compagniaDAOInstance.delete(ultimoDellaLista);

		int numeroElementiPresentiDopoDellaRimozione = compagniaDAOInstance.list().size();
		if (numeroElementiPresentiDopoDellaRimozione != numeroElementiPresentiPrimaDellaRimozione - 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED, la rimozione non è avvenuta");

		System.out.println(".......testDeleteCompagnia fine: PASSED.............");
	}

	// ======================================================================================================================
	// Test InsertCompagnia

	private static void testInsertCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".......testInsertCompagnia inizio.............");
		int quantiElementiInseriti = compagniaDAOInstance.insert(new Compagnia("PizzaArturo", 50000, LocalDate.now()));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testInsertCompagnia : FAILED");

		System.out.println(".......testInsertCompagnia fine: PASSED.............");
	}

	// =========================================================================================================================
	// Test InsertImpiegato

	public static void testInsertImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println(".......testInsertImpiegato inizio.............");
		int quantiElementiInseriti = impiegatoDAOInstance
				.insert(new Impiegato("Marco", "Gronni", "GKR500QLM", LocalDate.of(2008, 11, 20), LocalDate.now()));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("TestInsertImpiegato : FAILED");

		System.out.println(".......testInsertImpiegato fine: PASSED.............");
	}

	// ========================================================================================================================
	// Test UpdateCompagnia

	private static void testUpdateCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println(".........testUpdateCompagnia inizio...........");
		List<Compagnia> elencoCompagnie = compagniaDAOInstance.list();
		if (elencoCompagnie.size() < 1) {
			throw new RuntimeException("errore: la lista di compagnie è vuota!!");
		}
	}

	// =========================================================================================================================
	// test FindAllByDataAssunzioneMaggioreDi

	private static void testFindAllByDataAssunzioneMaggioreDi(CompagniaDAO compagniaDAOInstance,
			ImpiegatoDAO impiegatoDAOInstance) throws Exception {

		System.out.println(".......testFindAllByDataAssunzioneMaggioreDi inizio......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException("testFindAllByDataAssunzioneMaggioreDi : FAILED, non ci sono compagnia sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException("testFindAllByDataAssunzioneMaggioreDi : FAILED, non ci sono impiegati sul DB");

		LocalDate dataDaRicercare = LocalDate.parse("2023-01-01");
		List<Compagnia> listaCompagniaLikeExample = compagniaDAOInstance
				.findAllByDataAssunzioneMaggioreDi(dataDaRicercare);
		if (listaCompagniaLikeExample.size() < 1) {
			throw new RuntimeException("testFindAllByDataAssunzioneMaggioreDi : FAILED, non ci sono voci sul DB");
		}
		System.out.println(".......testFindAllByDataAssunzioneMaggioreDi fine: PASSED.............");
	}

	// ====================================================================================================================
	// test FindAllByRagioneSocialeContiene

	private static void testFindAllByRagioneSocialeContiene(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println("......testFindAllByRagioneSocialeContiene inizio......");
		List<Compagnia> compagnieEsistenti = compagniaDAOInstance.list();
		String parteNomeRagioneSociale = "Pi";
		List<Compagnia> result = compagniaDAOInstance.findAllByRagioneSocialeContiene(parteNomeRagioneSociale);
		System.out.println(result);
		System.out.println("......testFindAllByRagioneSocialeContiene fine......");

	}

	// ================================================================================================================================================
	// Test FindAllByCodFisImpiegatoContiene

	private static void testFindAllByCodFisImpiegatoContiene(CompagniaDAO compagniaDAOInstance,
			ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println(".......testFindAllByCodFisImpiegatoContiene inizio......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException("testFindAllByCodFisImpiegatoContiene : FAILED, non ci sono compagnia sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException("testFindAllByCodFisImpiegatoContiene : FAILED, non ci sono impiegati sul DB");

		String codFisDaCercare = "GRK";
		List<Compagnia> listaCompagniaLikeExample = compagniaDAOInstance
				.findAllByCodFisImpiegatoContiene(codFisDaCercare);
		if (listaCompagniaLikeExample.size() < 1) {
			throw new RuntimeException("testFindAllByCodFisImpiegatoContiene : FAILED, non ci sono voci sul DB");
		}
		System.out.println("Gli elementi della lista sono: " + listaCompagniaLikeExample.size());
		System.out.println(listaCompagniaLikeExample);
		System.out.println(".......testFindAllByCodFisImpiegatoContiene fine: PASSED.............");
	}

	// =====================================================================================================================
	// Test UpdateImpiegato

	private static void testUpdateImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println(".........testUpdateCompagnia inizio...........");
		List<Impiegato> elencoCompagnie = impiegatoDAOInstance.list();
		if (elencoCompagnie.size() < 1) {
			throw new RuntimeException("errore: la lista di impiegati è vuota!!");
		}
	}

	// =========================================================================================================================
	// Test DeleteImpiegato

	public static void testDeleteImpiegato(ImpiegatoDAO impiegatoDAOInstance) throws Exception {

		System.out.println(".......testDeleteCompagnia inizio.............");
		int quantiElementiInseriti = impiegatoDAOInstance.insert(new Impiegato("lucone", "calli", "grc876hujy01",
				LocalDate.of(1989, 01, 05), LocalDate.of(2010, 06, 04)));
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testDeleteImpiegato : FAILED, user da rimuovere non inserito");

		List<Impiegato> elencoVociPresenti = impiegatoDAOInstance.list();
		int numeroElementiPresentiPrimaDellaRimozione = elencoVociPresenti.size();
		if (numeroElementiPresentiPrimaDellaRimozione < 1)
			throw new RuntimeException("testDeleteImpiegato : FAILED, non ci sono voci sul DB");

		Impiegato ultimo = elencoVociPresenti.get(numeroElementiPresentiPrimaDellaRimozione);
		impiegatoDAOInstance.delete(ultimo);

		int numeroElementiPresentiDopoDellaRimozione = impiegatoDAOInstance.list().size();
		if (numeroElementiPresentiDopoDellaRimozione != numeroElementiPresentiPrimaDellaRimozione - 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED, la rimozione non è avvenuta");

		System.out.println(".......testDeleteCompagnia fine: PASSED.............");
	}

	// ====================================================================================================================
	// Test FindByExampleImpiegato

	private static void testFindByExample(ImpiegatoDAO impiegatoDAOInstance) {
		Impiegato example = null;
		int quantiSonoPresenti = -1;
		// ##########################################################//
		System.out.println("############test verifica dati #################");

		try {
			List<Impiegato> presentiSullaBaseDati = impiegatoDAOInstance.findByExample(example);
			quantiSonoPresenti = presentiSullaBaseDati.size();
			System.out.println("sono attualmente presenti " + quantiSonoPresenti + " records");
			for (Impiegato impiegatoItem : presentiSullaBaseDati) {
				System.out.println(impiegatoItem);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// ====================================================================================================================
	// Test FindByExampleCompagnia

	private static void testFindByExample(CompagniaDAO compagniaDAOInstance) {
		Compagnia example = null;
		int quantiSonoPresenti = -1;
		// ##########################################################//
		System.out.println("############test verifica dati #################");

		try {
			List<Compagnia> presentiSullaBaseDati = compagniaDAOInstance.findByExample(example);
			quantiSonoPresenti = presentiSullaBaseDati.size();
			System.out.println("sono attualmente presenti " + quantiSonoPresenti + " records");
			for (Compagnia compagniaItem : presentiSullaBaseDati) {
				System.out.println(compagniaItem);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	//======================================================================================================================================================================
	// Test CountByDataFondazioneCompagniaGreaterThan
	
	private static void testCountByDataFondazioneCompagniaGreaterThan(CompagniaDAO compagniaDAOInstance, ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println("......testCountByDataFondazioneCompagniaGreaterThan inizio........");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException(
					"errore: non sono presenti compagnie sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException(
					"errore: non sono presenti impiegati sul DB");
		LocalDate dataDaRicercare = LocalDate.parse("1980-08-24");
		int countImpiegati = impiegatoDAOInstance.countByDataFondazioneCompagniaGreaterThan(dataDaRicercare);
		System.out.println("Il contatore segna: " + countImpiegati);
		System.out.println(".......testCountByDataFondazioneCompagniaGreaterThan fine: PASSED.............");
		
	}
	
	//=======================================================================================================================================================
	// Test FindAllByCompagniaConFatturatoMaggioreDi
	
	private static void testFindAllByCompagniaConFatturatoMaggioreDi (CompagniaDAO compagniaDAOInstance, ImpiegatoDAO impiegatoDAOInstance) throws Exception{
		System.out.println(".......testFindAllByCompagniaConFatturatoMaggioreDi inizio.......");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException(
					"errore: non sono presenti compagnie sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException(
					"errore: non sono presenti impiegati sul DB");
		int fatturatoDaRicercare= 120000;
		List<Impiegato> impiegatiCompagniaConFatturatoMaggioreDi= impiegatoDAOInstance.findAllByCompagniaConFatturatoMaggioreDi(fatturatoDaRicercare);
		System.out.println("gli impiegati in compagnie con fatturato maggiore di "+ impiegatiCompagniaConFatturatoMaggioreDi.size()+ " elementi.");
		System.out.println(impiegatiCompagniaConFatturatoMaggioreDi);
		System.out.println(".......testFindAllByCompagniaConFatturatoMaggioreDi fine.........");
	}
	
	
	//=============================================================================================================================================
	// Test FindAllByCompagnia
	
	public static void testFindAllByCompagnia(CompagniaDAO compagniaDAOInstance, ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println("......testFindAllByCompagnia inizio.........");
		List<Compagnia> elencoCompagnie=compagniaDAOInstance.list();
		if(elencoCompagnie.size()<1) {
			throw new RuntimeException("errore: non sono presenti compagnie sul db.");
		}
		List<Impiegato> elencoImpiegati= impiegatoDAOInstance.list();
		if (elencoImpiegati.size()<1) {
			throw new RuntimeException ("errore: non sono presenti impiegati sul db.");
		}
		Compagnia compagnieDaRicercare= elencoCompagnie.get(0);
		List<Impiegato> impiegatiDellaCompagnia= impiegatoDAOInstance.findAllByCompagnia(compagnieDaRicercare);
		if (impiegatiDellaCompagnia.size()<1) {
			throw new RuntimeException("non è stato trovato nulla");
		}
		System.out.println("gli elementi che corrispondono sono "+impiegatiDellaCompagnia.size() );
		System.out.println(impiegatiDellaCompagnia);
		System.out.println("..........testFindAllByCompagnia fine......");
		
	}
	
	//=======================================================================================================================================
	// Test FindAllErroriAssunzione
	
	private static void testFindAllErroriAssunzione(CompagniaDAO compagniaDAOInstance, ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		System.out.println("..........testFindAllErroriAssunzione inizio........");
		List<Compagnia> elencoCompagniePresenti = compagniaDAOInstance.list();
		if (elencoCompagniePresenti.size() < 1)
			throw new RuntimeException(
					"errore: non sono presenti compagnie sul DB");
		List<Impiegato> elencoImpiegatiPresenti = impiegatoDAOInstance.list();
		if (elencoImpiegatiPresenti.size() < 1)
			throw new RuntimeException(
					"errore: non sono presenti impiegati sul DB");
		List<Impiegato> impiegatiConErroreAssunzione= impiegatoDAOInstance.findAllErroriAssunzione();
		System.out.println("gli impiegati con errori di assunzione sono " +impiegatiConErroreAssunzione.size() );
		System.out.println(impiegatiConErroreAssunzione);
		System.out.println("........testFindAllErroriAssunzione fine.........");
	}

}
