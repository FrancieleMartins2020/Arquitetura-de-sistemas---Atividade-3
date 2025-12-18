package br.edu.utfpr.td.tsi.ecommerce.loja.web;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BrowserAutoOpen {

    @EventListener(ApplicationReadyEvent.class)
    public void openBrowser() {
        try {
            Runtime.getRuntime()
              .exec("cmd /c start http://localhost:8089");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

