package br.com.telegrambot.api;

import java.util.Map;
import java.util.TreeMap;

/**
 * Classe de analise de mensagens e resposta
 * @author cpereira
 *
 */
public class MyResponse {
	
	/**
	 * Cria um mapa de respostas
	 */
	private static final Map<String, String> respMap;
	static {
		respMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
		respMap.put("Oi", "Oi");
		respMap.put("oi", "Oi");
		respMap.put("OI", "Oii");
		respMap.put("Beleza?", "de boa");
		respMap.put("teste", "testando ... 123");
		
	}
	
	private String msg;
	
	/**
	 * Construtor
	 */
	public MyResponse() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Construtor
	 * @param msg
	 */
	public MyResponse(String msg) {
		this.msg = msg;
	}

	/**
	 * Retorna o valor obtido do HashMap
	 * @return
	 */
	public String getResponse() {
		String resp = null;
		if(respMap.containsKey(msg)) {
			resp = respMap.get(msg);
		} else 
			resp = "Não entendi...";
		return resp;
	}
}
