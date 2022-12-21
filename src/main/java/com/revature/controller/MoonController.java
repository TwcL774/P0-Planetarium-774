package com.revature.controller;

import java.util.List;
import java.util.Objects;

import com.revature.models.Moon;
import com.revature.models.User;
import com.revature.service.MoonService;

import io.javalin.http.Context;

public class MoonController {

	private MoonService mService = new MoonService();

	public void getAllMoons(Context ctx) {
		List<Moon> moons = mService.getAllMoons();

		if (!moons.isEmpty()) {
			ctx.json(moons).status(200);
		} else {
			ctx.status(404);
		}
	}

	public void getMoonByName(Context ctx) {
		User u = ctx.sessionAttribute("user");
		String moonName = ctx.pathParam("name");
		Moon m = mService.getMoonByName(u.getUsername(), moonName);

		if (Objects.nonNull(m)) {
			ctx.json(m).status(200);
		} else {
			ctx.status(404);
		}
	}

	public void getMoonById(Context ctx) {
		User u = ctx.sessionAttribute("user");
		int moonId = ctx.pathParamAsClass("id", Integer.class).get();
		Moon m = mService.getMoonById(u.getUsername(), moonId);

		if (Objects.nonNull(m)) {
			ctx.json(m).status(200);
		} else {
			ctx.status(404);
		}
	}

	public void createMoon(Context ctx) {
		Moon m = ctx.bodyAsClass(Moon.class);
		User u = ctx.sessionAttribute("user");
		Moon outGoingMoon = mService.createMoon(u.getUsername(), m);

		if (Objects.nonNull(outGoingMoon)) {
			ctx.json(outGoingMoon).status(201);
		} else {
			ctx.status(400);
		}
		;
	}

	public void deleteMoon(Context ctx) {
		int moonId = ctx.pathParamAsClass("id", Integer.class).get();
		int result = mService.deleteMoonById(moonId);

		if (result != 0) {
			ctx.json("Planet successfully deleted").status(202);
		} else {
			ctx.status(410);
		}
	}

	public void getPlanetMoons(Context ctx) {
		int planetId = ctx.pathParamAsClass("id", Integer.class).get();
		List<Moon> moons = mService.getMoonsFromPlanet(planetId);

		if (!moons.isEmpty()) {
			ctx.json(moons).status(200);
		} else {
			ctx.status(404);
		}
	}
}
