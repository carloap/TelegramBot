package br.com.telegrambot.config;

/**
 * Dados de configuração (Token) de Bots do Telegram
 * @author cpereira
 */
public enum Token {
    NAME_BOT("token_id");

    private String token;

    Token(String token) {
        this.token = token;
    }

    /**
     * Retorna o valor de token referente à constante definida para determinado bot
     * @return
     */
    public String getValue() {
        return token;
    }
    
}
