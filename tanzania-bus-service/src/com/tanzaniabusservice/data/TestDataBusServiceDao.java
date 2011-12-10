package com.tanzaniabusservice.data;

import com.tanzaniabusservice.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TestDataBusServiceDao implements BusServiceDao {
    private Map<Long, Company> fakeDb = new HashMap<Long, Company>();

    public TestDataBusServiceDao() {
        fakeDb.put(0L, makeCompany("Lupelo", 3168847350L, 0L, 4.5f));
        fakeDb.put(1L, makeCompany("Sumry High Class", 3168947350L, 1L, 7.2f));
        fakeDb.put(2L, makeCompany("Kilimanjaro Express", 3169047350L, 2L, 9.1f));
    }

    public Company loadCompanyById(long companyId) {
        return fakeDb.get(companyId);
    }

    public List<Company> listCompaniesAboveRating(float rating) {
        List<Company> results = new ArrayList<Company>();
        for (Company company : fakeDb.values()) {
            if (company.getRating() > rating) {
                results.add(company);
            }
        }

        return results;
    }

    private Company makeCompany(String name, long mills, long id, float rating) {
        return Company.newBuilder().setEstablishedDateMills(mills).setId(id).setName(name).setRating(rating).build();
    }

}
