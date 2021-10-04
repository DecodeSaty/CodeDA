package pages.resources;

import java.io.IOException;


public class Currency {

    private String id ;
    private String currency;

    public void setId(String id) {
        this.id = id;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getId() {
        return id;
    }
    public String getCurrency() {
        return currency;
    }
    public Currency(){}

    public Currency(String id) throws IOException {
        Currency[] currencies = JacksonUtils.deserializeJson("currency.json", Currency[].class);
        for(Currency currency: currencies){
            if(currency.getId().equals(id)){
                this.id = id;
                this.currency= currency.getCurrency();
            }
        }
    }
}
