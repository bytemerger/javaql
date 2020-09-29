package com.api.graphql.exceptions;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;

public class DogNotFoundException extends RuntimeException implements GraphQLError {
    private HashMap<String,Object> extensions = new HashMap<>();

    public DogNotFoundException(String message, Long invalidId){
        super(message);
        extensions.put("invalidDogId", invalidId);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }
}
