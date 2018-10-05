package com.ask.pk.myskill.handler;

import java.util.HashMap;
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

import com.ask.pk.myskill.pojo.*;

public class ProductMapIntentHandler implements RequestHandler {

	private static final HashMap<String, ProductDetails> map = new HashMap<String, ProductDetails>();

	static {

		map.put("apple", new ProductDetails("6", "4", "100"));
		map.put("milk", new ProductDetails("1", "2", "100 Liters"));
		map.put("chocolate", new ProductDetails("2", "2", "1000"));
		map.put("soap", new ProductDetails("2", "5", "100"));
		map.put("mobile", new ProductDetails("3", "6", "100"));

	}

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
		if (productName != null) {
			String product = productName.getValue();
			ProductDetails productObj = map.get(product);
			if (productObj != null) {
				speechText = product + " is there in row " + productObj.getRow() + " and column " + productObj.getColumn()
						+ ". Available quantity is " + productObj.getAvailableQty();
			} else {
				speechText = "product is not available";
			}
		} else {
			speechText = "product is not available";
		}

		ResponseBuilder responseBuilder = input.getResponseBuilder();
		responseBuilder.withSimpleCard("ProductMap", speechText).withSpeech(speechText).withShouldEndSession(false);;
		return responseBuilder.build();
	}

}
