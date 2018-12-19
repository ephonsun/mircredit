package com.banger.mobile.dao.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnLoanBalanceVehicle;

public interface LnLoanBalanceVehicleDAO {

	List<LnLoanBalanceVehicle> selectVehicleByPrimary(
			LnLoanBalanceVehicle lnLoanBalanceVehicle);

	void deleteVehicle(LnLoanBalanceVehicle lnLoanBalanceVehicle);

	int updateVehicle(LnLoanBalanceVehicle asset);

	LnLoanBalanceVehicle insertVehicle(LnLoanBalanceVehicle lnLoanBalanceVehicle);
   
}