package com.company.core.concretes;

import com.MernisService.EPTKPSPublicSoap;
import com.company.core.abstracts.IUserCheckAdapter;
import com.company.entities.abstracts.User;

import java.util.Locale;

public class UserCheckMernis implements IUserCheckAdapter {
    @Override
    public boolean CheckIfRealPerson(User user) {
        EPTKPSPublicSoap publicSoap = new EPTKPSPublicSoap();
        try {
            return publicSoap.TCKimlikNoDogrula(Long.valueOf(user.getIdentificationNumber()),
                    user.getName().toUpperCase(Locale.ROOT),
                    user.getSurname().toUpperCase(Locale.ROOT),
                    user.getDateOfBirth().getYear());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
