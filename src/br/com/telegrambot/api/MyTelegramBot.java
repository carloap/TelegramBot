package br.com.telegrambot.api;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

/**
 * Classe para gerenciar instâncias de bots do telegram
 * @author cpereira
 *
 */
public class MyTelegramBot {

	private TelegramBot bot;
	private GetUpdatesResponse updatesResponse;
	private SendResponse sendResponse;
	private BaseResponse baseResponse;
	private int posMsg = 0;
	private int limitMsg = 100;
	
	/**
	 * Construtor
	 */
	public MyTelegramBot(String token) {
		this.bot = TelegramBotAdapter.build(token);
	}
	public MyTelegramBot(String token, int limitMsg) {
		this.bot = TelegramBotAdapter.build(token);
		this.limitMsg = limitMsg;
	}
	
	/**
	 * Inicializa loop para escutar as mensagens que são recebidas
	 */
	public void init() {
		while(true) {
			
			// Executa comando no Telegram para obter as mensagens pendentes a partir de um off-set (limite inicial)
			updatesResponse =  this.bot.execute(new GetUpdates().limit(this.limitMsg).offset(this.posMsg));
			
			// Monta uma lista de mensagens
			List<Update> updates = updatesResponse.updates();
			
			// Analisa uma lista de mensagens 
			//this.analytic(updates);
			// análise de cada ação da mensagem
			for (Update update : updates) {
				//atualização do off-set
				this.posMsg = update.updateId()+1;
				
				// Mensagem recebida
				Message message = update.message();
				
				// TODO
				// Entendimento
				
				// Ação tomada
				
				// Resposta
				baseResponse = this.setAction(message);
				sendResponse = this.setResponse(message);
			}
		}
	}
	
	/**
	 * Define ações de resposta para o remetente da mensagem
	 * @param message
	 * @return
	 */
	private BaseResponse setAction(Message message) {
		// Exibe mensagem "escrevendo..."
		return this.bot.execute(new SendChatAction(message.chat().id(), ChatAction.typing.name()));
	}
	
	/**
	 * Define uma resposta para a mensagem do rementente
	 * @param message
	 * @return
	 */
	private SendResponse setResponse(Message message) {
		MyResponse r = new MyResponse(message.text());
		String strResponse = r.getResponse();
		return this.bot.execute(new SendMessage(message.chat().id(), strResponse));
	}
	
}
