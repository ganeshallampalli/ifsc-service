package com.ifsc.model;

import com.ifsc.model.BaseRequestResponse.BaseRequest;

/**
 * <h2>Interface for the Fetch Bank IFsc Request Response.</h2> <br>
 * 
 * @author ganeshallampalli
 *
 */
public interface FetchBankIfscRequestResponse {

	/**
	 * 
	 * <h2>Fetch Bank Ifsc Request class.
	 * <h2><br>
	 * 
	 * @author ganeshallampalli
	 *
	 */
	public class FetchBankIfscRequest extends BaseRequest {
	}

	/**
	 * <h2>Fetch Bank Ifsc Response.</h2> <br>
	 * 
	 * @author ganeshallampalli
	 *
	 */
	public class FetchBankIfscResponse {

		private String ifsc;

		private Long bankId;

		private String branch;

		private String address;

		private String city;

		private String district;

		private String state;

		public String getIfsc() {
			return ifsc;
		}

		public void setIfsc(String ifsc) {
			this.ifsc = ifsc;
		}

		public Long getBankId() {
			return bankId;
		}

		public void setBankId(Long bankId) {
			this.bankId = bankId;
		}

		public String getBranch() {
			return branch;
		}

		public void setBranch(String branch) {
			this.branch = branch;
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

		@Override
		public String toString() {
			return "FetchBankIfscResponse [ifsc=" + ifsc + ", bankId=" + bankId + ", branch=" + branch + ", address="
					+ address + ", city=" + city + ", district=" + district + ", state=" + state + "]";
		}

	}
}
