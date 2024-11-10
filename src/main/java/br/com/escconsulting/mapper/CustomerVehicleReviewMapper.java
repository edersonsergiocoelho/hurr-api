package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.vehicle.review.CustomerVehicleReviewDTO;
import br.com.escconsulting.entity.CustomerVehicleReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVehicleReviewMapper {

    CustomerVehicleReviewMapper INSTANCE = Mappers.getMapper( CustomerVehicleReviewMapper.class );

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "customerVehicleBooking", target = "customerVehicleBooking")
    CustomerVehicleReviewDTO toDTO(CustomerVehicleReview customerVehicleReview);

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "customerVehicleBooking", target = "customerVehicleBooking", ignore = true)
    CustomerVehicleReviewDTO toDTONoCustomerVehicleBooking(CustomerVehicleReview customerVehicleReview);

    @Mapping(source = "customer", target = "customer", ignore = true)
    @Mapping(source = "customerVehicleBooking", target = "customerVehicleBooking", ignore = true)
    CustomerVehicleReviewDTO toSimpleDTO(CustomerVehicleReview customerVehicleReview);

    void update(CustomerVehicleReview source, @MappingTarget CustomerVehicleReview target);
}