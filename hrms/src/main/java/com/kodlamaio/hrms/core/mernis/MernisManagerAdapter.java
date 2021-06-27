package com.kodlamaio.hrms.core.mernis;

import java.rmi.RemoteException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;


public class MernisManagerAdapter implements PersonValidationService{
	
	@Override
	public boolean validate(Long identificationNumber, String name, String lastName, int yearOfBirth) {
		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();
		try {
			return kpsPublicSoapProxy.TCKimlikNoDogrula(Long.valueOf(identificationNumber), name, lastName, yearOfBirth);
		} catch (RemoteException e) {
			e.printStackTrace();	
			return false;
		}
	}
}
