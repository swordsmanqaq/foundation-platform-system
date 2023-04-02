package com.heng.car.service;

import com.heng.car.domain.Car;
import com.heng.base.service.IBaseService;
import com.heng.car.dto.CarAuditDTO;
import com.heng.org.domain.Employee;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jarvis-Smith
 * @since 2023-03-29
 */
public interface ICarService extends IBaseService<Car> {

    void saveCars(Car car, Employee loginUser);

    void saveAuditCommit(CarAuditDTO carAuditDTO);

}
