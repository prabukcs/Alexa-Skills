package com.ask.pk.myskill.handler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

public class LaunchRequestHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.requestType(LaunchRequest.class));
	}

	public Optional<Response> handle(HandlerInput input) {
		String welcomeWish = getWelcomeWish();
		String speechText = "Hey!!" + welcomeWish
				+ ".. Welcome to PK super market. I can help to find product location and availablity. Tell me the product name";
		return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("ProductMap", speechText)
				.withReprompt(speechText).build();
	}

	private String getWelcomeWish() {
		String wishMsg = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		String[] timeArr = time.split(":");
		int hour = Integer.valueOf(timeArr[0]);

		if (hour > 12) {
			wishMsg = "Good Morning";
		} else if (hour >= 12 && hour < 13) {
			wishMsg = "Good Noon";
		} else if (hour > 13 && hour <= 16) {
			wishMsg = "Good afternoon";
		} else if (hour > 16 && hour <= 19) {
			wishMsg = "Good Evening";
		} else if (hour > 19 && hour < 24) {
			wishMsg = "Good night";
		}

		return wishMsg;
	}

}
