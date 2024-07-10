package br.com.escconsulting.dto.customer.vehicle;

import br.com.escconsulting.entity.CustomerVehicle;
import br.com.escconsulting.entity.CustomerVehicleFileInsurance;
import br.com.escconsulting.entity.CustomerVehicleFilePhoto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVehicleSaveDTO {

    private CustomerVehicle customerVehicle;
    private List<CustomerVehicleFilePhoto> CustomerVehicleFilePhotos;
    private List<CustomerVehicleFileInsurance> CustomerVehicleFileInsurances;
}