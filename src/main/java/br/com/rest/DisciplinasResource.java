package br.com.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;



import br.com.persistence.dao.DisciplinaDao;
import br.com.persistence.models.Disciplina;
import br.com.services.DisciplinaService;

@Path("/Disciplina")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DisciplinasResource {

    @Inject
    DisciplinaDao dao;

    @Inject
    DisciplinaService service;
    

    @GET
    @Operation(
        summary = "Listar Disciplinas",
        description = "Retorna uma lista dos disciplinas"
    )
    @APIResponse(
        responseCode = "200",
        description = "Disciplina",
        content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Disciplina.class, type = SchemaType.ARRAY))})
    public Response obtemDisciplinas() throws Exception{
        return Response.status(Response.Status.OK).entity(dao.buscaDisciplina()).build();
    }

    @GET
    @Path("/{prefixo}")
    @Operation(
        summary = "Listar Disciplinas por prefixo",
        description = "Retorna uma lista dos disciplinas"
    )
    @APIResponse(
        responseCode = "200",
        description = "Disciplina",
        content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Disciplina.class, type = SchemaType.ARRAY))})
    public Response buscaDisciplinasEspecificos(final @PathParam("prefixo") String prefixo) throws Exception{
        return Response.status(Response.Status.OK).entity(dao.buscaDisciplinasEspecificos(prefixo)).build();
    }

    @POST
    @Transactional
    @Operation(summary = "Inserir uma Disciplina",
            description = "Insere uma Disciplina.")
    @APIResponse(
            responseCode = "201",
            description = "Disciplina",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Disciplina.class))})
     public Response inserirDisciplina(Disciplina disciplina) throws Exception {
                        return  Response.status(Response.Status.CREATED).entity(service.inserirDisciplina(disciplina)).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Listar disciplina Por ID",
            description = "Retorna uma disciplina de acordo com id solicitado")
    @APIResponse(
            responseCode = "200",
            description = "Disciplina",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Disciplina.class, type = SchemaType.ARRAY))})
    public Response obtemDisciplinaById(final @PathParam("id") long id) throws Exception  {
        return Response.status(Response.Status.OK).entity(dao.encontrarDisciplinaId(id)).build();
    }

    @PUT
    @Path("/{id}/{nome}")
    @Operation(summary = "Atualiza disciplina por ID",
            description = "Atualiza uma disciplina de acordo com id solicitado")
    @APIResponse(
            responseCode = "200",
            description = "Disciplina",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Disciplina.class, type = SchemaType.ARRAY))})
    public Response atualizaDisciplina(final @PathParam("id") long id, final @PathParam("nome") String nome) throws Exception  {
        return Response.status(Response.Status.OK).entity(dao.mudarDisciplina(id, nome)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deleta disciplina por ID",
            description = "Deleta uma disciplina de acordo com id solicitado")
    @APIResponse(
            responseCode = "204",
            description = "Disciplina",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Disciplina.class, type = SchemaType.ARRAY))})
    public Response deletaDisciplina(final @PathParam("id") long id) throws Exception  {
        return Response.status(Response.Status.NO_CONTENT).entity(dao.deletarDisciplina(id)).build();
    }


    

    
}
