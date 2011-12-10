package com.tanzaniabusservice.client;

import com.tanzaniabusservice.BusService;
import com.tanzaniabusservice.Company;
import org.apache.avro.ipc.CallFuture;
import org.apache.avro.ipc.HttpTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SampleClient {
    public static void main(String[] args) throws IOException {
        final HttpTransceiver client = new HttpTransceiver(new URL("http://localhost:8080/tanzaniabusservice/tanzaniabusservice"));
        final BusService.Callback busService = SpecificRequestor.getClient(BusService.Callback.class, client);

        System.out.println("Fetched company by ID: " + busService.loadCompany(0L));
        System.out.println("Fetched company list: " + busService.listCompaniesAboveRating(5.0f));
        System.out.println("Checking null value: " + busService.loadCompany(-55L));

        System.out.println("Running async concurrent calls...");
        CallFuture<Company> companyCallFuture = new CallFuture<Company>();
        System.out.println("Starting loadCompanySlow call");
        busService.loadCompanySlow(0L, companyCallFuture);
        System.out.println("Done starting loadCompanySlow call");
        CallFuture<List<Company>> listCompaniesCallFuture = new CallFuture<List<Company>>();
        System.out.println("Starting listCompaniesAboveRating call");
        busService.listCompaniesAboveRating(4.0f, listCompaniesCallFuture);
        System.out.println("Done starting listCompaniesAboveRating call");

        try {
            System.out.println("Data fetched from async calls...");
            System.out.println(companyCallFuture.get());
            System.out.println(listCompaniesCallFuture.get());
        } catch (Exception e) {
            System.out.println("Error fetching data" + e);
        }

    }
}
