package com.mymo.transformer;

import com.mymo.transformer.exception.*;
import com.mymo.transformer.fixtures.*;
import org.junit.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@Ignore
public class BenchmarkTest {
    private static final int ITERATIONS = 1000000;
    private Transformer transformer;

    private CustomerRequest customerRequest;

    @Before
    public void setUp() {
        customerRequest = new CustomerRequest();
        customerRequest.setId(12345L);

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setId(1L);
        addressRequest.setCompanyName("farts");

        customerRequest.setAddress(addressRequest);

        transformer = new Transformer(new ClasspathScanningContext(TestConfiguration.class));
    }

    @Test
    public void testTransform() throws TransformException {
        Customer customer = new Customer();
        transformer.transform(customerRequest).into(customer);

        assertThat(customer.getCustomerId(), is(customerRequest.getId()));
        assertThat(customer.getAddress().getAddressId(), is(customerRequest.getAddress().getId()));
        assertThat(customer.getAddress().getCompany(), is(customerRequest.getAddress().getCompanyName()));
    }

}
