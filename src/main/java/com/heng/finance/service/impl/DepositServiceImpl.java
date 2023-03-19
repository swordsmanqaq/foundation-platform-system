package com.heng.finance.service.impl;

import com.heng.finance.domain.Deposit;
import com.heng.finance.mapper.DepositMapper;
import com.heng.finance.service.IDepositService;
import com.heng.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jarvis-Smith
 * @since 2023-03-16
 */
@Service
public class DepositServiceImpl extends BaseServiceImpl<Deposit> implements IDepositService {

    @Autowired
    private DepositMapper depositMapper;

    @Override
    public Deposit getOrderDeposit(Long id) {
        return depositMapper.getOrderDeposit(id);

    }
}