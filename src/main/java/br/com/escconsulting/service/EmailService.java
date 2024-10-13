package br.com.escconsulting.service;

import br.com.escconsulting.entity.*;

public interface EmailService {

    void sendForgotPasswordVerificationCode(User user);

    void sendForgotPasswordValidated(User user);

    void sendForgotPassword(User user);

    void sendEmailVerificationCode(Customer customer);

    void sendDriverLicenseApproved(FileApproved fileApproved, Customer customer);

    void sendDriverLicenseDisapprove(FileApproved fileApproved, Customer customer);

    void sendIdentityNumberApproved(FileApproved fileApproved, Customer customer);

    void sendIdentityNumberReproved(FileApproved fileApproved, Customer customer);

    void sendProfilePictureApproved(FileApproved fileApproved, User user);

    void sendProfilePictureReproved(FileApproved fileApproved, User user);

    void sendCustomerWithdrawalRequestApproval(CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest);

    void sendCustomerVehicleCreated(CustomerVehicle customerVehicle);
}