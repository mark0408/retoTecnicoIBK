package com.demo.ibk.customer.service;

import com.demo.ibk.customer.dto.CustomerDTO;

public interface ICustomerService {
    CustomerDTO getByUniqueCode(String uniqueCode);
    CustomerDTO save(CustomerDTO request);
    CustomerDTO update(CustomerDTO request);
}
