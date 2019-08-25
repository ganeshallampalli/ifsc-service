package com.ifsc.model;

import java.util.List;

import com.ifsc.model.BaseRequestResponse.BaseRequest;

public interface FetchBankDetailsByCityRequestResponse {

	public class FetchBankDetailsByCityRequest extends BaseRequest {

		private String bankName;

		private String city;

		private Integer offset;

		private Integer limit;

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public Integer getOffset() {
			return offset;
		}

		public void setOffset(Integer offset) {
			this.offset = offset;
		}

		public Integer getLimit() {
			return limit;
		}

		public void setLimit(Integer limit) {
			this.limit = limit;
		}

		@Override
		public String toString() {
			return "FetchBankDetailsByCityRequest [bankName=" + bankName + ", city=" + city + ", offset=" + offset
					+ ", limit=" + limit + "]";
		}

	}

	public class FetchBankDetailsByCityResponse {

		private List<Branch> branches;

		private Integer totalPages;

		private Integer currentPageNumber;

		private Long noOfRecords;

		public List<Branch> getBranches() {
			return branches;
		}

		public void setBranches(List<Branch> branches) {
			this.branches = branches;
		}

		public Integer getTotalPages() {
			return totalPages;
		}

		public void setTotalPages(Integer totalPages) {
			this.totalPages = totalPages;
		}

		public Integer getCurrentPageNumber() {
			return currentPageNumber;
		}

		public void setCurrentPageNumber(Integer currentPageNumber) {
			this.currentPageNumber = currentPageNumber;
		}

		public Long getNoOfRecords() {
			return noOfRecords;
		}

		public void setNoOfRecords(Long noOfRecords) {
			this.noOfRecords = noOfRecords;
		}

	}

	public class Branch {

		private String bankName;

		private String ifsc;

		private String branchName;

		private String address;

		private String city;

		private String district;

		private String state;

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getIfsc() {
			return ifsc;
		}

		public void setIfsc(String ifsc) {
			this.ifsc = ifsc;
		}

		public String getBranchName() {
			return branchName;
		}

		public void setBranchName(String branchName) {
			this.branchName = branchName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

	}
}
