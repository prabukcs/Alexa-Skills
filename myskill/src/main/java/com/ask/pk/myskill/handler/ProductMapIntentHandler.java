package com.ask.pk.myskill.handler;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.response.ResponseBuilder;

public class ProductMapIntentHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
       return input.matches(Predicates.intentName("ProductMapIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
        
    	Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot productName = slots.get("Product");
        String speechText;
    	if(productName!=null) {
    		String product = productName.getValue();
    		speechText = "You have selected "+product;
    	}else {
    		speechText = "product is not available";
    	}
    	
    	ResponseBuilder responseBuilder = input.getResponseBuilder();
    	responseBuilder.withSimpleCard("ProductMap", speechText)
        .withSpeech(speechText);
    	return responseBuilder.build();
    }

}
