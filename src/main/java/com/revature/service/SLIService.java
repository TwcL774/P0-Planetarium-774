package com.revature.service;

import com.revature.models.SLI;
import com.revature.repository.SLIDao;

public class SLIService {

	private SLIDao dao;

	public SLIService() {
		this.dao = new SLIDao();
	}

	public SLI getSLI() {
		return this.dao.getSLI();
	}
}
