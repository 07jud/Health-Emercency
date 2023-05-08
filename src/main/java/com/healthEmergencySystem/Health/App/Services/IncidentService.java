package com.healthEmergencySystem.Health.App.Services;

import com.healthEmergencySystem.Health.App.Domain.Incident;
import com.healthEmergencySystem.Health.App.Repositories.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository repo;

    public List<Incident> listAll(){
        return repo.findAll();
    }

    public void save(Incident incident){
        repo.save(incident);
    }

    public Incident get(Long id){
        return repo.findById(id).get();
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

}
