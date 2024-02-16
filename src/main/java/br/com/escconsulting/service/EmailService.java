package br.com.escconsulting.service;

import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.FileApproved;

public interface EmailService {

    void sendEmailVerification(Customer customer);

    void sendDriverLicenseApproved(FileApproved fileApproved, Customer customer);

    void sendDriverLicenseDisapprove(FileApproved fileApproved, Customer customer);

    void sendIdentityNumberApproved(FileApproved fileApproved, Customer customer);

    void sendIdentityNumberReproved(FileApproved fileApproved, Customer customer);
}