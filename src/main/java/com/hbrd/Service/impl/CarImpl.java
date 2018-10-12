package com.hbrd.Service.impl;

import com.hbrd.Dao.CarDao;
import com.hbrd.Service.CarService;
import com.hbrd.Util.Verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarImpl implements CarService {
    @Autowired
    CarDao carDao;
    @Override
    public int UpdateImsi(String imsi,String CarId) {
        try {
            if(Verify.CarImsi(imsi)&&Verify.CarId(CarId)){
                return carDao.UpdateImsi(imsi,CarId);
            }else {
                return 11;
            }
        }catch (Exception e){
            return 123;
        }
    }
}
