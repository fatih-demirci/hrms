package com.kodlamaio.hrms.business.abstracts;

import com.kodlamaio.hrms.core.utilities.result.Result;

public interface FavoriteJobAdvertisementService {
	Result add(int jobSeekerId, int jobAdvertisementId);
	Result delete(int jobSeekerId, int jobAdvertisementId);
}
