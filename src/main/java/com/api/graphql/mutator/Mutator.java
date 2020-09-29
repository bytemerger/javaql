package com.api.graphql.mutator;

import com.api.graphql.entity.Dog;
import com.api.graphql.exceptions.BreedNotFoundException;
import com.api.graphql.exceptions.DogNotFoundException;
import com.api.graphql.repository.DogRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutator implements GraphQLMutationResolver {
    private DogRepository dogRepository;

    public Mutator(DogRepository repository){
        this.dogRepository = repository;
    }
    public Boolean deleteDogBreed(String breed){
        boolean deleted = false;
        Iterable<Dog> allDogs = dogRepository.findAll();
        // Loop through all dogs to check their breed
        for (Dog d:allDogs) {
            if (d.getBreed().equals(breed)) {
                // Delete if the breed is found
                dogRepository.delete(d);
                deleted = true;
            }
        }
        // Throw an exception if the breed doesn't exist
        if (!deleted) {
            throw new BreedNotFoundException("Breed Not Found", breed);
        }
        return deleted;
    }

    public Dog updateDogName(String name, Long id){
        Optional<Dog> option = dogRepository.findById(id);
        if (option.isPresent()){
            Dog mutaDog = option.get();
            mutaDog.setName(name);
            dogRepository.save(mutaDog);
            return mutaDog;
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}
