package it.disim.univaq.sose.project.service;

import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.JsonNode;

public interface AggregatorService {
	CompletableFuture<JsonNode> retrieveMetamodel(Long userId);
	CompletableFuture<JsonNode> retrieveModel(Long userId);
}
