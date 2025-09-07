/**
 * created by aakanksha
 */
package com.example.demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import com.example.demo.constants.SentimentAnalyzerConstants;
import com.example.demo.exception.SentimentAnalysisException;

/**
 * This class implements a customer feedback sentiment analyzer.
 * 
 * created on: 10th August 2025
 */
@Service
public class SentimentAnalyzerService {

	private final ChatClient chatClient;

	public SentimentAnalyzerService(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}

	/**
	 * this method implements sentiment analyzer logic by calling OpenAI API
	 * 
	 * @throws SentimentAnalysisException
	 */
	public String analyze(String feedback) throws SentimentAnalysisException {

		String promptText = SentimentAnalyzerConstants.PROMPT_TEXT + feedback;
		UserMessage userMessage = new UserMessage(promptText);
		Prompt prompt = new Prompt(userMessage);
		String result = SentimentAnalyzerConstants.ERROR;
		try {
			result = chatClient.prompt(prompt).call().chatResponse().getResult().getOutput().getText();
		} catch (Exception e) {
			if (e.getMessage() != null) {
				throw new SentimentAnalysisException(e.getMessage());
			}
		}

		return result;
	}

}
