package it.prova.gestionecompagniapatterndao.gestionedao;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagniapatterndao.model.Compagnia;
import it.prova.gestionecompagniapatterndao.model.Impiegato;
import it.prova.gestionepatterndao.dao.IBaseDAO;

public interface ImpiegatoDAO extends IBaseDAO<Impiegato> {
	public List<Impiegato> findAllByCompagnia(Compagnia compagniaInput) throws Exception;
	public int countByDataFondazioneCompagniaGreaterThan(LocalDate dataDaRicercare) throws Exception;
	public List<Impiegato> findAllByCompagniaConFatturatoMaggioreDi(int fattura) throws Exception;
	public List<Impiegato> findAllErroriAssunzione() throws Exception;

}
