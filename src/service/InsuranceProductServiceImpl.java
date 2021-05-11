package service;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Client;
import entity.Contract;
import entity.InsuranceProduct;
import entity.InsuranceProducts;
import list.ClientListImpl;
import list.ContractListImpl;
import list.InsuranceProductListImpl;

public class InsuranceProductServiceImpl implements InsuranceProductService{
	
	private InsuranceProductListImpl insuranceProductListImpl;
	private ContractListImpl contractListImpl;
	private ClientListImpl clientListImpl;
	private Scanner sc;
	
	public InsuranceProductServiceImpl(){
		this.insuranceProductListImpl = new InsuranceProductListImpl();
		this.sc = new Scanner(System.in);
	}
	
	public void association(ClientListImpl clientListImpl) {
		this.clientListImpl = clientListImpl;
	}

	public ArrayList<InsuranceProduct> showAllList() {
		return insuranceProductListImpl.getInsuranceProductList();
	}

	public void designInsuranceProduct(InsuranceProducts insuranceProducts) {
		InsuranceProduct developedProduct = insuranceProducts.designInsurance().developInsurance();
		System.out.println(insuranceProductListImpl.add(developedProduct)? 
				"보험상품 생성이 완료되었습니다.":"보험상품 생성에 실패하였습니다.");
	}
	
	public void add(InsuranceProduct developedProduct) {
		System.out.println(insuranceProductListImpl.add(developedProduct)? 
				"보험상품 생성이 완료되었습니다.":"보험상품 생성에 실패하였습니다.");
	}
	
	public Contract selectNotApproval() {
		ArrayList<Contract> contractList = contractListImpl.searchByApproval(false);
		if(contractList.size()>0) {
			System.out.println("[인수심사 계약 목록]");
			for(int i = 0; i < contractList.size(); i++)
				System.out.println(String.format("%d.%5s%10s", i+1, contractList.get(i).getClientID(), contractList.get(i).getProductName()));
			System.out.println("인수심사할 계약의 번호를 입력해주세요.");
			int input = sc.nextInt();
			Contract contract = contractList.get(input-1);
			showClientInfo(contract.getClientID());
//			showInsuranceInfo(contract.getProductName());
			return contract;
			
		}else {
			System.out.println("현재 심사할 계약이 없습니다.");
			return null;
		}
		
	}

	private void showClientInfo(String clientID) {
		Client client = clientListImpl.search(clientID);
		System.out.println("[고객 정보]");
		System.out.println("이름: " + client.getName());
		System.out.println("나이: " + client.getAge());
		System.out.println("성별: " + (client.isGender()? "남자" : "여자"));
		System.out.println("직업: " + client.getJob().getJobName());
		System.out.println("암경력: " + client.getMedicalHistory().getClientCancerCareer().getCancerName() + "(본인)"
							+ client.getMedicalHistory().getFamilyCancerCareer().getCancerName() + "(가족)");
		System.out.println("입원내역: " + client.getMedicalHistory().getNumberOfHospitalizations());
		System.out.println("병원진료: " + client.getMedicalHistory().getNumberOfHospitalVisits());
	}
	
	
//	private void showInsuranceInfo(String productName) {
//		InsuranceProduct insuranceProduct = insuranceProductListImpl.search(productName);
//	
//	}

}
