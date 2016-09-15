package ch.appuio.example;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ch.appuio.example.model.Hello;

/**
 * A simple Hello CDI Service
 *
 */
@Stateless
public class HelloService {
	
	@Inject EntityManager em;

    public String createHelloMessage(String name) {
    	
    	Hello h = new Hello();
    	h.setCreated(new Date());
    	h.setFrontend(System.getenv("HOSTNAME"));
    	h.setName(name);
    	saveHello(h);
    	
        return "" + name + "! (Env "+ h.getFrontend() +")";
    }
    
    public List<Hello> getAllHellos(){
    	return em.createQuery("from Hello", Hello.class).getResultList();
    }
    
    private void saveHello(Hello hello){
    	em.persist(hello);
    }

}
