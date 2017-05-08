package com.mymo.transformer;

import com.mymo.transformer.annotation.*;
import com.mymo.transformer.fixtures.*;

@Configuration
public class TestConfiguration {

    @Translation(Customer.class)
    private CustomerRequest customerRequest;
}
