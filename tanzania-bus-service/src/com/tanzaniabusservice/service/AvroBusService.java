package com.tanzaniabusservice.service;

import com.tanzaniabusservice.BusService;
import com.tanzaniabusservice.Company;
import com.tanzaniabusservice.data.BusServiceDao;
import org.apache.avro.AvroRemoteException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class AvroBusService implements BusService {
    private static final Logger logger = Logger.getLogger(AvroBusService.class);
    private BusServiceDao dao;

    @Inject
    public AvroBusService(BusServiceDao dao) {
        this.dao = dao;
    }

    public Company loadCompany(long companyId) throws AvroRemoteException {
        return dao.loadCompanyById(companyId);
    }

    public Company loadCompanySlow(long companyId) throws AvroRemoteException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("Error in sleep", e);
        }
        return loadCompany(companyId);
    }

    public List<Company> listCompaniesAboveRating(float rating) throws AvroRemoteException {
        return dao.listCompaniesAboveRating(rating);
    }
}
