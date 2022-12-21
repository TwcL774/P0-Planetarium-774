package com.revature.controller;

import java.util.List;
import java.util.Objects;

import com.revature.models.Planet;
import com.revature.models.User;
import com.revature.service.PlanetService;

import io.javalin.http.Context;

public class PlanetController {

	private PlanetService pService = new PlanetService();

	public void getAllPlanets(Context ctx) {
		List<Planet> planets = pService.getAllPlanets();

		if (!planets.isEmpty()) {
			ctx.json(planets).status(200);
		} else {
			ctx.status(404);
		}

	}

	public void getPlanetByName(Context ctx) {
		User u = ctx.sessionAttribute("user");
		String planetName = ctx.pathParam("name");
		Planet p = pService.getPlanetByName(u.getUsername(), planetName);

		if (Objects.nonNull(p)) {
			ctx.json(p).status(200);
		} else {
			ctx.status(404);
		}
	}

	public void getPlanetByID(Context ctx) {
		User u = ctx.sessionAttribute("user");
		int planetId = ctx.pathParamAsClass("id", Integer.class).get();
		Planet p = pService.getPlanetById(u.getUsername(), planetId);

		if (Objects.nonNull(p)) {
			ctx.json(p).status(200);
		} else {
			ctx.status(404);
		}
	}

	public void createPlanet(Context ctx) {
		Planet planetToBeCreated = ctx.bodyAsClass(Planet.class);
		User u = ctx.sessionAttribute("user");
		Planet createdPlanet = pService.createPlanet(u.getUsername(), planetToBeCreated);

		if (Objects.nonNull(planetToBeCreated)) {
			ctx.json(createdPlanet).status(201);
		} else {
			ctx.status(400);
		}
	}

	public void deletePlanet(Context ctx) {
		int planetId = ctx.pathParamAsClass("id", Integer.class).get();
		int result = pService.deletePlanetById(planetId);

		if (result != 0) {
			ctx.json("Planet successfully deleted").status(202);
		} else {
			ctx.status(404);
		}
	}
}
