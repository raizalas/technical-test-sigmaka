package com.sigmaka.caseestudy.vendor;

import com.sigmaka.caseestudy.common.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendors")
public class VendorController {

  private final VendorService vendorService;

  @Autowired
  public VendorController(VendorService vendorService) {
    this.vendorService = vendorService;
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Vendor>> createVendor(@RequestBody Vendor vendor) {
    Vendor createdVendor = vendorService.createVendor(vendor);
    ApiResponse<Vendor> response = new ApiResponse<>(HttpStatus.CREATED.value(),
        HttpStatus.CREATED.getReasonPhrase(), createdVendor);
    return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<Vendor>>> getAllVendors() {
    List<Vendor> vendors = vendorService.getAllVendors();
    ApiResponse<List<Vendor>> response = new ApiResponse<>(HttpStatus.OK.value(),
        HttpStatus.OK.getReasonPhrase(), vendors);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Vendor>> getVendorById(@PathVariable Long id)
      throws NotFoundException {
    Vendor vendor = vendorService.getVendorById(id);
    ApiResponse<Vendor> response = new ApiResponse<>(HttpStatus.OK.value(),
        HttpStatus.OK.getReasonPhrase(), vendor);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<Vendor>> updateVendor(@PathVariable Long id,
      @RequestBody Vendor vendor) throws NotFoundException {
    Vendor updatedVendor = vendorService.updateVendor(id, vendor);
    ApiResponse<Vendor> response = new ApiResponse<>(HttpStatus.OK.value(),
        HttpStatus.OK.getReasonPhrase(), updatedVendor);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteVendor(@PathVariable Long id) throws NotFoundException {
    vendorService.deleteVendor(id);
    return ResponseEntity.noContent().build();
  }
}
