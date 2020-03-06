package assJava5.service;


import assJava5.model.Bill;
import assJava5.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill finById(Long id) {
        return billRepository.findById(id);
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public void remove(Long id) {
        billRepository.remove(id);
    }

    @Override
    public List<Bill> saveAll(List<Bill> bill) {
        return billRepository.saveAll(bill);
    }

    @Override
    public List<Bill> findByTop10Product() {
        return billRepository.findByTop10Product();
    }

    @Override
    public List<Bill> findByTop10Bill() {
        return billRepository.findByTop10Bill();
    }

}
