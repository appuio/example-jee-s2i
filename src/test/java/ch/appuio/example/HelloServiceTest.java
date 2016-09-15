package ch.appuio.example;



import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by tphilipona on 17.03.16.
 */
public class HelloServiceTest {

    @Test
    public void test() {
    	// given
        HelloService service = new HelloService();
        service.em = Mockito.mock(EntityManager.class);
        // when
        String appUiO = service.createHelloMessage("APPUiO");
        // then
        assertEquals("APPUiO! (Env null)", appUiO);
    }
}