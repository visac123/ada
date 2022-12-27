package br.com.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
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



import br.com.persistence.dao.ProfessorDao;
import br.com.persistence.dto.ProfessorRequest;
import br.com.persistence.models.Professor;
import br.com.services.ProfessorService;

@Path("/Professor")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfessoresResource {

    @Inject
    ProfessorDao dao;

    @Inject
    ProfessorService service;

    @GET
    @Operation(
        summary = "Listar Professores",
        description = "Retorna uma lista dos professores"
    )
    @APIResponse(
        responseCode = "200",
        description = "Professor",
        content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Professor.class, type = SchemaType.ARRAY))})
    public Response obtemProfessores() throws Exception{
        return Response.status(Response.Status.OK).entity(dao.buscaProfessor()).build();
    }

    @GET
    @Path("/{prefixo}")
    @Operation(
        summary = "Listar Professores por prefixo",
        description = "Retorna uma lista dos professores"
    )
    @APIResponse(
        responseCode = "200",
        description = "Professor",
        content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Professor.class, type = SchemaType.ARRAY))})
    public Response buscaProfessoresEspecificos(final @PathParam("prefixo") String prefixo) throws Exception{
        return Response.status(Response.Status.OK).entity(dao.buscaProfessoresEspecificos(prefixo)).build();
    }

    @POST
    @Transactional
    @Operation(summary = "Inserir uma Professor",
            description = "Insere uma Professor.")
    @APIResponse(
            responseCode = "201",
            description = "Professor",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Professor.class))})
     public Response inserirProfessor(ProfessorRequest professor) throws Exception {
                        return  Response.status(Response.Status.CREATED).entity(service.inserirProfessor(professor)).build();
    }

    @PATCH
    @Path("/{id}/titular/{idProfessor}")
    @Operation(summary = "add tutorando",
            description = "add tutorando")
    @APIResponse(
            responseCode = "200",
            description = "Professor",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Professor.class, type = SchemaType.ARRAY))})
    public Response updateTitular(@PathParam("id") long idProfessor, @PathParam("idProfessor") long idAluno) throws Exception{
        return Response.status(Response.Status.OK).entity(dao.mudarTutorando(idProfessor, idAluno)).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Listar professor Por ID",
            description = "Retorna uma professor de acordo com id solicitado")
    @APIResponse(
            responseCode = "200",
            description = "Professor",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Professor.class, type = SchemaType.ARRAY))})
    public Response obtemProfessorById(final @PathParam("id") long id) throws Exception  {
        return Response.status(Response.Status.OK).entity(dao.encontrarProfessorId(id)).build();
    }

    @PUT
    @Path("/{id}/{nome}")
    @Operation(summary = "Atualiza professor por ID",
            description = "Atualiza uma professor de acordo com id solicitado")
    @APIResponse(
            responseCode = "200",
            description = "Professor",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Professor.class, type = SchemaType.ARRAY))})
    public Response atualizaProfessor(final @PathParam("id") long id, final @PathParam("nome") String nome) throws Exception  {
        return Response.status(Response.Status.OK).entity(dao.mudarProfessor(id, nome)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deleta professor por ID",
            description = "Deleta uma professor de acordo com id solicitado")
    @APIResponse(
            responseCode = "204",
            description = "Professor",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Professor.class, type = SchemaType.ARRAY))})
    public Response deletaProfessor(final @PathParam("id") long id) throws Exception  {
        return Response.status(Response.Status.NO_CONTENT).entity(dao.deletarProfessor(id)).build();
    }


    

    
}
