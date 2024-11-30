package com.sigmaka.caseestudy.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

  private final VendorRepository vendorRepository;

  @Autowired
  public VendorService(VendorRepository vendorRepository) {
    this.vendorRepository = vendorRepository;
  }

  public Vendor createVendor(Vendor vendor) {
    return vendorRepository.save(vendor);
  }

  public List<Vendor> getAllVendors() {
    return vendorRepository.findAll();
  }

  public Vendor getVendorById(Long id) throws NotFoundException {
    return vendorRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  public Vendor updateVendor(Long id, Vendor updatedVendor) throws NotFoundException {
    Vendor existingVendor = getVendorById(id);
    existingVendor.setVendorName(updatedVendor.getVendorName());
    return vendorRepository.save(existingVendor);
  }

  public void deleteVendor(Long id) throws NotFoundException {
    if (!vendorRepository.existsById(id)) {
      throw new NotFoundException();
    }
    vendorRepository.deleteById(id);
  }
}
