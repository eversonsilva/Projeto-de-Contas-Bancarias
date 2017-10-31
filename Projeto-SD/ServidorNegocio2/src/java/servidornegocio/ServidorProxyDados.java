/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidornegocio;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:Operacoes [servidordados]<br>
 * USAGE:
 * <pre>
 *        ServidorProxyDados client = new ServidorProxyDados();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author 114751
 */
public class ServidorProxyDados {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://172.16.16.149:8080/ServidorDados/webresources";

    public ServidorProxyDados() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("servidordados");
    }

    public <T> T onLock(Class<T> responseType, String idServidorNegocio, String conta) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("onLock/{0}/{1}", new Object[]{idServidorNegocio, conta}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }

    public void putText(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_PLAIN));
    }

    public String setSaldo(String idConta, String valor, String id_negoc) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("setsaldo/{0}/{1}/{2}", new Object[]{idConta, valor, id_negoc}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String getSaldo(String idConta, String id_negoc) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("saldo/{0}/{1}", new Object[]{idConta, id_negoc}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public <T> T unLock(Class<T> responseType, String idServidorNegocio, String conta) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("unLock/{0}/{1}", new Object[]{idServidorNegocio, conta}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
