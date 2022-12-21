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



import br.com.persistence.dao.AlunoDao;
import br.com.persistence.dto.AlunoRequest;
import br.com.persistence.models.Aluno;
import br.com.services.AlunoService;

@Path("/Aluno")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunosResource {

    @Inject
    AlunoDao dao;

    @Inject
    AlunoService service;

    @GET
    @Operation(
        summary = "Listar Alunos",
        description = "Retorna uma lista dos alunos"
    )
    @APIResponse(
        responseCode = "200",
        description = "Aluno",
        content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Aluno.class, type = SchemaType.ARRAY))})
    public Response obtemAlunos() throws Exception{
        return Response.status(Response.Status.OK).entity(dao.buscaAluno()).build();
    }

    @GET
    @Path("/{prefixo}")
    @Operation(
        summary = "Listar Alunos por prefixo",
        description = "Retorna uma lista dos alunos"
    )
    @APIResponse(
        responseCode = "200",
        description = "Aluno",
        content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Aluno.class, type = SchemaType.ARRAY))})
    public Response buscaAlunosEspecificos(final @PathParam("prefixo") String prefixo) throws Exception{
        return Response.status(Response.Status.OK).entity(dao.buscaAlunosEspecificos(prefixo)).build();
    }

    @POST
    @Transactional
    @Operation(summary = "Inserir uma Aluno",
            description = "Insere uma Aluno.")
    @APIResponse(
            responseCode = "201",
            description = "Aluno",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Aluno.class))})
     public Response inserirAluno(AlunoRequest aluno) throws Exception {
                        return  Response.status(Response.Status.CREATED).entity(service.inserirAluno(aluno)).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Listar aluno Por ID",
            description = "Retorna uma aluno de acordo com id solicitado")
    @APIResponse(
            responseCode = "200",
            description = "Aluno",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Aluno.class, type = SchemaType.ARRAY))})
    public Response obtemAlunoById(final @PathParam("id") long id) throws Exception  {
        return Response.status(Response.Status.OK).entity(dao.encontrarAlunoId(id)).build();
    }

    @PUT
    @Path("/{id}/{nome}")
    @Operation(summary = "Atualiza aluno por ID",
            description = "Atualiza uma aluno de acordo com id solicitado")
    @APIResponse(
            responseCode = "200",
            description = "Aluno",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Aluno.class, type = SchemaType.ARRAY))})
    public Response atualizaAluno(final @PathParam("id") long id, final @PathParam("nome") String nome) throws Exception  {
        return Response.status(Response.Status.OK).entity(dao.mudarAluno(id, nome)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deleta aluno por ID",
            description = "Deleta uma aluno de acordo com id solicitado")
    @APIResponse(
            responseCode = "204",
            description = "Aluno",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Aluno.class, type = SchemaType.ARRAY))})
    public Response deletaAluno(final @PathParam("id") long id) throws Exception  {
        return Response.status(Response.Status.NO_CONTENT).entity(dao.deletarAluno(id)).build();
    }


    

    
}
