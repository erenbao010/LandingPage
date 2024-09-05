package com.atcollabo.service;

import com.atcollabo.entity.SiteInfo;

public interface SiteInfoService {

	SiteInfo getOneActive();

	Object updateWithId(SiteInfo site);

}
