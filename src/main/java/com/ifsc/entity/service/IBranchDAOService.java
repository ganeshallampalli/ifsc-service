package com.ifsc.entity.service;

import com.ifsc.model.BaseRequestResponse.BaseResponse;
import com.ifsc.model.FetchBankDetailsByCityRequestResponse.FetchBankDetailsByCityRequest;
import com.ifsc.model.FetchBankDetailsByCityRequestResponse.FetchBankDetailsByCityResponse;
import com.ifsc.model.FetchBankIfscRequestResponse.FetchBankIfscResponse;

public interface IBranchDAOService {

	BaseResponse<FetchBankIfscResponse> findBranchDetailsByIfscCode(String ifsc);

	BaseResponse<FetchBankDetailsByCityResponse> fetchBranchesByBankNameAndCity(
			FetchBankDetailsByCityRequest fetchBankDetailsByCityRequest);
}
