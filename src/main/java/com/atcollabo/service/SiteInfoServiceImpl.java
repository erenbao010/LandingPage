package com.atcollabo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atcollabo.entity.SiteInfo;
import com.atcollabo.repository.SiteInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class SiteInfoServiceImpl implements SiteInfoService {
	private final SiteInfoRepository siteRepository;

	@Override
	public SiteInfo getOneActive() {
		// TODO: 컨텍스트에 넣던지, AOP 쓰던지 
		List<SiteInfo> list = siteRepository.findByActive(true);
		if( list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return SiteInfo.builder()
					.siteAddress("3F, Paxsky building, 26 Ung Van Khiem, 25 Ward, Binh Thanh dist, HCMC")
					.siteEmail1("info@atcollabo.com")
					.sitePhone("036-233-7288")
					.siteTitle("ATCollabo")
					.siteWorkday("Monday - Friday")
					.siteWorktime("9:00 AM ~ 6:00 PM")
					.build();
		}
	}

	@Override
	public Object updateWithId(SiteInfo site) {
		if( site != null && site.getSitePk() != null && site.getSitePk().longValue() > 0) {
			if( site.getActive() == null) {
				site.onActive();
			}
			return siteRepository.save(site);
		}
		return null;
	}
	

}
