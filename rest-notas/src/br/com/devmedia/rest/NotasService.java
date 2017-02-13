package br.com.devmedia.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.devmedia.dao.NotaDAO;
import br.com.devmedia.entidade.Nota;

@Path("/notas")
public class NotasService {
	
	private static final String CHARSET_UTF8 = ";CHARSET=UTF-8";
	
	private NotaDAO notaDAO;
	

	//quando a classe for inicializada e a biblioteca do jersey criar o construtor dessa classe,
	//o metodo init será chamado automaticamente e o DAO será criado e disponibilizado.
	@PostConstruct  
	private void init(){
		notaDAO = new NotaDAO();
		
	}
	
	@GET
	@Path("/list")
	@Produces (MediaType.APPLICATION_JSON)
	public List<Nota> listarNotas(){
		List<Nota> lista = null;
		try{
			lista = notaDAO.listarNotas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
		}
	
	@POST
	@Path("/add")
	@Consumes (MediaType.APPLICATION_JSON + CHARSET_UTF8) // receberá JSON e nao text plain. 
	@Produces (MediaType.TEXT_PLAIN)
	public String addNota(Nota nota){
		String msg = "";
		System.out.println(nota.getTitulo());
		
		try{
			notaDAO.addNota(nota);
			msg = "Nota adicionada com sucesso";
			
		} catch (Exception e) {
			
			msg = "Erro ao adicionar nota";
			e.printStackTrace();
		}
		return msg;
		}
	
	
	@GET
	@Path("/get/{id}")
	@Consumes (MediaType.TEXT_PLAIN)
	@Produces (MediaType.APPLICATION_JSON)
	public Nota buscaPorId(@PathParam("id") int idNota) {
		Nota nota = null;
		
		try{
			nota = notaDAO.buscaNotaPorId(idNota);
						
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return nota;
		}
	
	@PUT
	@Path("/edit/{id}")
	@Consumes (MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces (MediaType.TEXT_PLAIN)
	public String editarNota(Nota nota, @PathParam("id") int idNota) {
		String msg = "";
		
		System.out.println(nota.getTitulo());
		
		
		try{
			notaDAO.editarNota(nota, idNota);
			
			msg= "Nota Editada com sucesso!!!";
			
		} catch (Exception e) {
			msg= "Erro ao editar nota!!!";
			e.printStackTrace();
		}
		return msg;
		}
	

	@DELETE
	@Path("/delete/{id}")
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces (MediaType.TEXT_PLAIN)
	public String removerNota(@PathParam("id") int idNota) {
		String msg = "";
		
		try{
			notaDAO.removerNota(idNota);
			
			msg= "Nota Removida com sucesso!!!";
			
		} catch (Exception e) {
			msg= "Erro ao remover nota!!!";
			e.printStackTrace();
		}
		return msg;
	}
	
}
	