package rs.ac.metropolitan.cs230.service;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.KolicinaproizvodaFacadeREST.class);
        resources.add(service.NarudzbenicaOtpremnicaFacadeREST.class);
        resources.add(service.ProizvodFacadeREST.class);
        resources.add(service.ProizvodnaJedinicaFacadeREST.class);
        resources.add(service.ReceptFacadeREST.class);
        resources.add(service.SkladisteFacadeREST.class);
        resources.add(service.SpecifikacijaProizvodnjeFacadeREST.class);
        resources.add(service.StavkaFacadeREST.class);
    }
    
}
