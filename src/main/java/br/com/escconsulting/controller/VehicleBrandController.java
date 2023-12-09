package br.com.escconsulting.controller;

import br.com.escconsulting.model.VehicleBrand;
import br.com.escconsulting.service.VehicleBrandService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vehicle-brand")
public class VehicleBrandController {

	private final VehicleBrandService vehicleBrandService;

	public VehicleBrandController(VehicleBrandService vehicleBrandService) {
		this.vehicleBrandService = vehicleBrandService;
	}

	@GetMapping
	@PermitAll
	public ResponseEntity<List<VehicleBrand>> getAllBrands() {
		List<VehicleBrand> brands = vehicleBrandService.getAllBrands();
		return ResponseEntity.ok(brands);
	}

	@GetMapping("/{vehicleBrandId}")
	public ResponseEntity<VehicleBrand> getBrandById(@PathVariable UUID vehicleBrandId) {
		VehicleBrand brand = vehicleBrandService.getVehicleBrandById(vehicleBrandId);
		return ResponseEntity.ok(brand);
	}

	@PostMapping
	public ResponseEntity<VehicleBrand> createBrand(@RequestBody @Valid VehicleBrand vehicleBrand) {
		VehicleBrand vehicleBrandCreated = vehicleBrandService.createVehicleBrand(vehicleBrand);
		return ResponseEntity.status(HttpStatus.CREATED).body(vehicleBrandCreated);
	}

	@PutMapping("/{vehicleBrandId}")
	public ResponseEntity<VehicleBrand> updateBrand(@PathVariable UUID vehicleBrandId, @RequestBody @Valid VehicleBrand vehicleBrand) {
		VehicleBrand updatedBrand = vehicleBrandService.updateVehicleBrand(vehicleBrandId, vehicleBrand);
		return ResponseEntity.ok(updatedBrand);
	}

	@DeleteMapping("/{vehicleBrandId}")
	public ResponseEntity<Void> deleteBrand(@PathVariable UUID vehicleBrandId) {
		vehicleBrandService.deleteVehicleBrand(vehicleBrandId);
		return ResponseEntity.noContent().build();
	}
}
