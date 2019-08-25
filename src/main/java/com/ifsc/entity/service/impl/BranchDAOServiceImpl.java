package com.ifsc.entity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ifsc.entity.Banks;
import com.ifsc.entity.Branches;
import com.ifsc.entity.repository.BankRepository;
import com.ifsc.entity.repository.BranchRepository;
import com.ifsc.entity.service.IBranchDAOService;
import com.ifsc.model.BaseRequestResponse.BaseResponse;
import com.ifsc.model.FetchBankDetailsByCityRequestResponse.Branch;
import com.ifsc.model.FetchBankDetailsByCityRequestResponse.FetchBankDetailsByCityRequest;
import com.ifsc.model.FetchBankDetailsByCityRequestResponse.FetchBankDetailsByCityResponse;
import com.ifsc.model.FetchBankIfscRequestResponse.FetchBankIfscResponse;

@Service
public class BranchDAOServiceImpl implements IBranchDAOService {

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private BankRepository bankRepository;

	@Override
	public BaseResponse<FetchBankIfscResponse> findBranchDetailsByIfscCode(String ifsc) {
		BaseResponse<FetchBankIfscResponse> response = new BaseResponse<>();
		FetchBankIfscResponse fetchBankIfscResponse = new FetchBankIfscResponse();
		Branches branches = branchRepository.findByIfsc(ifsc);

		if (null == branches) {
			response.setCode("10001");
			response.setMessage("Couldn't find data for given IFSC Code");
		} else {
			fetchBankIfscResponse.setAddress(branches.getAddress());
			fetchBankIfscResponse.setBankId(branches.getBankId());
			fetchBankIfscResponse.setBranch(branches.getBranch());
			fetchBankIfscResponse.setCity(branches.getCity());
			fetchBankIfscResponse.setDistrict(branches.getDistrict());
			fetchBankIfscResponse.setIfsc(branches.getIfsc());
			fetchBankIfscResponse.setState(branches.getState());
			response.setCode("200");
			response.setMessage("Fetched Succesfully");
			response.setResponseData(fetchBankIfscResponse);
		}
		return response;

	}

	@Override
	public BaseResponse<FetchBankDetailsByCityResponse> fetchBranchesByBankNameAndCity(
			FetchBankDetailsByCityRequest fetchBankDetailsByCityRequest) {
		BaseResponse<FetchBankDetailsByCityResponse> response = new BaseResponse<>();
		FetchBankDetailsByCityResponse fetchBankDetailsByCityResponse = new FetchBankDetailsByCityResponse();

		Integer offset = fetchBankDetailsByCityRequest.getOffset();
		Integer limit = fetchBankDetailsByCityRequest.getLimit();

		Banks banks = bankRepository.findByName(fetchBankDetailsByCityRequest.getBankName());

		if (null == banks) {
			response.setCode("10002");
			response.setMessage("Couldn't find the given bank name");

		} else if (offset == 0) {
			response.setCode("10003");
			response.setMessage("Offset cannot be zero");
		} else {

			String bankName = banks.getName();
			String city = fetchBankDetailsByCityRequest.getCity();

			Integer pageNo = offset / limit;

			Pageable pageable = PageRequest.of(pageNo, limit);

			Page<Branches> page = branchRepository.findAllByBankIdAndCity(banks.getId(), city, pageable);

			Integer totalPages = page.getTotalPages();
			Integer currentPageNumber = page.getNumber();
			Long noOfRecords = page.getTotalElements();

			List<Branches> branches = page.getContent();

			fetchBankDetailsByCityResponse.setBranches(convertToBranchDTO(branches, bankName));
			fetchBankDetailsByCityResponse.setCurrentPageNumber(currentPageNumber);
			fetchBankDetailsByCityResponse.setNoOfRecords(noOfRecords);
			fetchBankDetailsByCityResponse.setTotalPages(totalPages);
			response.setCode("200");
			response.setMessage("Fetched all branches for a given city successfully");
			response.setResponseData(fetchBankDetailsByCityResponse);
		}
		return response;
	}

	private List<Branch> convertToBranchDTO(List<Branches> branches, String bankName) {
		List<Branch> branchList = new ArrayList<>();

		if (!branches.isEmpty()) {
			branches.parallelStream().forEach(b -> {
				Branch branch = new Branch();
				branch.setIfsc(b.getIfsc());
				branch.setAddress(b.getAddress());
				branch.setBankName(bankName);
				branch.setCity(b.getCity());
				branch.setDistrict(b.getDistrict());
				branch.setState(b.getState());
				branchList.add(branch);
			});
		}

		return branchList;
	}

}
