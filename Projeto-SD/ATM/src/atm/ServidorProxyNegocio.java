/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:Negocio [servidornegocio]<br>
 * USAGE:
 * <pre>
 *        ServidorProxyNegocio client = new ServidorProxyNegocio();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author 41487532
 */
public class ServidorProxyNegocio {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ServidorNegocio2/webresources";

    public ServidorProxyNegocio() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("servidornegocio");
    }

    public static String getBASE_URI() {
        return BASE_URI;
    }
    
    public String getsaldo(String idConta, String id_negoc) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("saldo/{0}/{1}", new Object[]{idConta, id_negoc}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String transfer( String idConta1, String idConta2, String valor, String id_negoc) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("transfer/{0}/{1}/{2}/{3}", new Object[]{idConta1, idConta2, valor, id_negoc}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void putText(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_PLAIN));
    }

    public String setSaldo(String idConta, String valor, String id_negoc) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("setsaldo/{0}/{1}/{2}", new Object[]{idConta, valor, id_negoc}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String deposit(String idConta, String valor, String id_negoc) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("deposit/{0}/{1}/{2}", new Object[]{idConta, valor, id_negoc}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String withdraw(String idConta, String valor, String id_negoc) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("withdraw/{0}/{1}/{2}", new Object[]{idConta, valor, id_negoc}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
