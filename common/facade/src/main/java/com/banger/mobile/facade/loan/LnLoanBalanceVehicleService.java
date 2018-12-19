package com.banger.mobile.facade.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanBalanceVehicle;

public interface LnLoanBalanceVehicleService {



	List<LnLoanBalanceVehicle> selectVehicleByPrimary(
			LnLoanBalanceVehicle lnLoanBalanceVehicle);

	LnLoanBalanceVehicle insertVehicle(LnLoanBalanceVehicle lnLoanBalanceVehicle);

	LnLoanBalanceVehicle updateVehicle(LnLoanBalanceVehicle lnLoanBalanceVehicle);

	void deleteVehicle(LnLoanBalanceVehicle lnLoanBalanceVehicle);

	List<LnLoanBalanceVehicle> selectVehicleByPrimary(Integer loanBalanceId);
    }
