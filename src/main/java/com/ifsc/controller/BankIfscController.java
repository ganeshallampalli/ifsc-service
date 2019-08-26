package com.ifsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ifsc.entity.service.IBranchDAOService;
import com.ifsc.model.BaseRequestResponse.BaseResponse;
import com.ifsc.model.FetchBankDetailsByCityRequestResponse.FetchBankDetailsByCityRequest;
import com.ifsc.model.FetchBankDetailsByCityRequestResponse.FetchBankDetailsByCityResponse;
import com.ifsc.model.FetchBankIfscRequestResponse.FetchBankIfscResponse;
import com.ifsc.util.JWTUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class BankIfscController {

	@Autowired
	private IBranchDAOService iBranchDAOService;
	
	@Autowired
	private JWTUtil jwtUtil;

	@GetMapping("/v1/banks/{ifsc}")
	@ApiOperation(value = "Fetch Bank Details by IFSC", response = FetchBankIfscResponse.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Bank details fetched successfully"),
			@ApiResponse(code = 403, message = "Unauthorized Access"),
			@ApiResponse(code = 404, message = "API Not Found"),
			@ApiResponse(code = 10001, message = "Couldn't find data for given IFSC Code") })
	public BaseResponse<FetchBankIfscResponse> fetchBankDetails(@RequestHeader("Authorization") String jwt,
			@RequestParam("ifsc") String ifsc) {
		BaseResponse<FetchBankIfscResponse> baseResponse = new BaseResponse<>();
		if(jwtUtil.validateJWT(jwt)) {
			baseResponse = iBranchDAOService.findBranchDetailsByIfscCode(ifsc);
		} else {
			baseResponse.setCode("403");
			baseResponse.setMessage("Unauthorized Access");
		}
		return baseResponse;
	}

	@GetMapping("/v1/branches")
	@ApiOperation(value = "Fetch Branch Details by name and city", response = FetchBankDetailsByCityResponse.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Branch details fetched successfully"),
			@ApiResponse(code = 403, message = "Unauthorized Access"),
			@ApiResponse(code = 404, message = "API Not Found"),
			@ApiResponse(code = 10002, message = "Couldn't find the given bank name"),
			@ApiResponse(code = 10003, message = "Offset cannot be zero") })
	public BaseResponse<FetchBankDetailsByCityResponse> fetchBranchesByBankNameAndCity(
			@RequestHeader("Authorization") String jwt,
			@RequestParam(name = "bankName") String bankName,
			@RequestParam(name = "city" ) String city,
			@RequestParam(name = "offset") Integer offset,
			@RequestParam(name = "limit") Integer limit) {
		BaseResponse<FetchBankDetailsByCityResponse> baseResponse = new BaseResponse<>();
		 FetchBankDetailsByCityRequest fetchBankDetailsByCityRequest = new FetchBankDetailsByCityRequest();
		 fetchBankDetailsByCityRequest.setBankName(bankName);
		 fetchBankDetailsByCityRequest.setCity(city);
		 fetchBankDetailsByCityRequest.setLimit(limit);
		 fetchBankDetailsByCityRequest.setOffset(offset);
		if(jwtUtil.validateJWT(jwt)) {
			baseResponse = iBranchDAOService.fetchBranchesByBankNameAndCity(fetchBankDetailsByCityRequest);
		} else {
			baseResponse.setCode("403");
			baseResponse.setMessage("Unauthorized Access");
		}
		return baseResponse;
	}

}
