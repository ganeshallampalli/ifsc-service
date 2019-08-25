package com.ifsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsc.entity.service.IBranchDAOService;
import com.ifsc.model.BaseRequestResponse.BaseResponse;
import com.ifsc.model.FetchBankDetailsByCityRequestResponse.FetchBankDetailsByCityRequest;
import com.ifsc.model.FetchBankDetailsByCityRequestResponse.FetchBankDetailsByCityResponse;
import com.ifsc.model.FetchBankIfscRequestResponse.FetchBankIfscResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class BankIfscController {

	@Autowired
	private IBranchDAOService iBranchDAOService;

	@GetMapping("/v1/banks/{ifsc}")
	@ApiOperation(value = "Fetch Bank Details by IFSC", response = FetchBankIfscResponse.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Bank details fetched successfully"),
			@ApiResponse(code = 404, message = "API Not Found"),
			@ApiResponse(code = 10001, message = "Couldn't find data for given IFSC Code") })
	public BaseResponse<FetchBankIfscResponse> fetchBankDetails(@PathVariable("ifsc") String ifsc) {
		return iBranchDAOService.findBranchDetailsByIfscCode(ifsc);
	}

	@PostMapping("/v1/branches")
	@ApiOperation(value = "Fetch Branch Details by name and city", response = FetchBankDetailsByCityResponse.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Branch details fetched successfully"),
			@ApiResponse(code = 404, message = "API Not Found"),
			@ApiResponse(code = 10002, message = "Couldn't find the given bank name"),
			@ApiResponse(code = 10003, message = "Offset cannot be zero") })
	public BaseResponse<FetchBankDetailsByCityResponse> fetchBranchesByBankNameAndCity(
			@RequestBody FetchBankDetailsByCityRequest fetchBankDetailsByCityRequest) {
		return iBranchDAOService.fetchBranchesByBankNameAndCity(fetchBankDetailsByCityRequest);
	}

}
