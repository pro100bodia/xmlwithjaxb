package com.example;

import com.example.dataprovider.DataProvider;
import com.example.model.Customer;
import com.example.model.Customers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class Application {
    public static void main(String[] args) throws JAXBException {
        List<Customer> customerList = DataProvider.getData(DataProvider.SMALL);
        Customers customers = new Customers();
        customers.setCustomers(customerList);

        outputToFile("resources/output.xml", customers);
    }

    private static void outputToFile(String filename, Customers customers) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Customers.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        File file = new File(filename);
        marshaller.marshal(customers, file);
    }
}
