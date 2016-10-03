package com.tapthis.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.tapthis.model.Beer;

@Service
public class BeerServiceImpl implements BeerService {

	@Override
	public Beer lookupBeer(String beerName) {
		final String uri = "https://api.untappd.com/v4/search/beer?q=" + beerName
				+ "&client_id=B2397B44662D900D4265BF03E035CE16D5CF4846&client_secret=2AE5A2667723D53FDDDCF2410EB5412F91FE433A";
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(uri, Beer.class);
	}
}