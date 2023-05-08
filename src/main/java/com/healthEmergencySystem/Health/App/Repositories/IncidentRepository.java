package com.healthEmergencySystem.Health.App.Repositories;

import com.healthEmergencySystem.Health.App.Domain.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
