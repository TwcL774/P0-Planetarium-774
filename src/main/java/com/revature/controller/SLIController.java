package com.revature.controller;

import com.revature.service.SLIService;

import io.javalin.http.Context;

public class SLIController {

    private SLIService sliService = new SLIService();

    public void getSLI(Context ctx) {
        ctx.json(sliService.getSLI()).status(200);
    }
}
