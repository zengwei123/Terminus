package com.hbrd.Dao;

import org.springframework.stereotype.Repository;

@Repository
public interface CarDao {
    int UpdateImsi(String imsi,String CarId);
}
