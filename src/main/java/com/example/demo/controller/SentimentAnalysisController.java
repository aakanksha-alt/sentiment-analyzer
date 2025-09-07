/**
 * created by aakanksha
 */
package com.example.demo.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.SentimentAnalysisException;
import com.example.demo.service.SentimentAnalyzerService;

/**
 * RestController for Sentiment Analysis
 */
@RestController
@RequestMapping("/api/feedback")
public class SentimentAnalysisController {

    private final SentimentAnalyzerService sentimentAnalyzerService;


	private SentimentAnalysisController(SentimentAnalyzerService sentimentAnalyzerService) {
		this.sentimentAnalyzerService = sentimentAnalyzerService;
	}
	
	@PostMapping("/analyze-sentiment")
	@CrossOrigin("http://localhost:4200")
	public String analyze(@RequestBody String feedback) throws SentimentAnalysisException {
		return sentimentAnalyzerService.analyze(feedback);
	}
}
