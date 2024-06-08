package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.review.CustomerVehicleReviewDTO;
import br.com.escconsulting.entity.CustomerVehicleReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleReviewMapper {

    CustomerVehicleReviewMapper INSTANCE = Mappers.getMapper( CustomerVehicleReviewMapper.class );

    @Mapping(source = "customerVehicleReview.customer", target = "customer")
    @Mapping(source = "customerVehicleReview.customerVehicleBooking", target = "customerVehicleBooking", ignore = true)
    CustomerVehicleReviewDTO toDTO(CustomerVehicleReview customerVehicleReview);
}