package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.repository.PilotDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PilotServiceImpl
 */
@Service
@Transactional
public class PilotServiceImpl implements PilotService {
    @Autowired
    private PilotDb pilotDb;

    @Override
    public Optional<PilotModel> getPilotDetailByLicenseNumber(String licenseNumber) {
        return pilotDb.findByLicenseNumber(licenseNumber);
    }

    @Override
    public PilotModel addPilot(PilotModel pilot) {
        return pilotDb.save(pilot);
    }

    @Override
    public void deletePilotByLicenseNumber(String licenseNumber) {
        pilotDb.deleteByLicenseNumber(licenseNumber);
    }

    @Override
    public Optional<PilotModel> getPilotDetailById(long id) {
        return pilotDb.findById(id);
    }

	@Override
	public void deletePilot(PilotModel pilot) {
		pilotDb.delete(pilot);
	}

	@Override
	public void updatePilot(long pilotId, PilotModel pilot) {
		PilotModel current = pilotDb.getOne(pilotId);
		List<FlightModel> pilotFlights = current.getListFlight();
		
		current.setName(pilot.getName());
		current.setFlyHour(pilot.getFlyHour());
		current.setListFlight(pilotFlights);
		
		addPilot(current);
	}
}