import java.util.HashMap;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class NFe_CCe {

	public static void main(String[] args){

		String login = "Token_enviado_pelo_suporte";
		
		/* Substituir pela sua identifica��o interna da nota. */
		String ref = "12345";
		
		/* Para ambiente de produ��o use a vari�vel abaixo:
		String server = "https://api.focusnfe.com.br/"; */
		String server = "http://homologacao.acrasnfe.acras.com.br/";
		
		String url = server.concat("v2/nfe/"+ref+"/carta_correcao");

		/* Aqui criamos um hashmap para receber a chave "correcao" e o valor desejado. */
		HashMap<String, String> correcao = new HashMap<String, String>();
		correcao.put("correcao", "Informe aqui os campos que foram corrigidos na NFe.");
		
		/* Criamos um objeto JSON para receber a hash com os dados esperado pela API. */
		JSONObject json = new JSONObject(correcao);
		
		/* Configura��o para realizar o HTTP BasicAuth. */
		Object config = new DefaultClientConfig();
		Client client = Client.create((ClientConfig) config);
		client.addFilter(new HTTPBasicAuthFilter(login, ""));

		WebResource request = client.resource(url);

		ClientResponse resposta = request.post(ClientResponse.class, json);

		int HttpCode = resposta.getStatus(); 

		String body = resposta.getEntity(String.class);

	   /* As tr�s linhas abaixo imprimem as informa��es retornadas pela API. 
		* Aqui o seu sistema dever� interpretar e lidar com o retorno. */
		System.out.print("HTTP Code: ");
		System.out.print(HttpCode);
		System.out.printf(body);
	}
}