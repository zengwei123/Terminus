package com.hbrd.Dao;

import com.hbrd.Model.Car;
import com.hbrd.Model.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao {
    int InputTerminusMessage(Message message);
}
