package rs.ac.metropolitan.cs230.service;

import rs.ac.metropolitan.cs230.entity.SpecifikacijaProizvodnje;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Stateless
@Path("entity.specifikacijaproizvodnje")
public class SpecifikacijaProizvodnjeFacadeREST extends AbstractFacade<SpecifikacijaProizvodnje> {
    @PersistenceContext(unitName = "CS230-DZ13-NIKOLA-TASIC-3698-PU")
    private EntityManager em;

    public SpecifikacijaProizvodnjeFacadeREST() {
        super(SpecifikacijaProizvodnje.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(SpecifikacijaProizvodnje entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, SpecifikacijaProizvodnje entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public SpecifikacijaProizvodnje find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<SpecifikacijaProizvodnje> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<SpecifikacijaProizvodnje> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
