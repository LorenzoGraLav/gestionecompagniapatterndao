package it.prova.gestionecompagniapatterndao.gestionedao;


import java.time.LocalDate;
import java.util.List;

import it.prova.gestionecompagniapatterndao.model.Compagnia;
import it.prova.gestionepatterndao.dao.IBaseDAO;

public interface CompagniaDAO extends IBaseDAO<Compagnia> {

	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(LocalDate dataInput) throws Exception;
	public List<Compagnia> findAllByRagioneSocialeContiene(String ragioneSocialeInput) throws Exception;
	public List<Compagnia> findAllByCodFisImpiegatoContiene(String parteCodiceFiscaleInput) throws Exception;
	
}
