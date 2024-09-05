package com.atcollabo.service;

import java.util.List;

import com.atcollabo.entity.Hackathon;

public interface HackathonService {

	List<Hackathon> getList(Boolean active);

	Object newHackathon(Hackathon hackerthon);

}
