package com.liquidsoftware.order.domain.functions;

import com.liquidsoftware.order.domain.Address;
import com.liquidsoftware.order.domain.Addresses;
import com.liquidsoftware.order.domain.CustomerInfo;
import com.liquidsoftware.order.domain.CustomerInfos;
import com.liquidsoftware.order.domain.OrderId;
import com.liquidsoftware.order.domain.OrderIds;
import com.liquidsoftware.order.domain.OrderLineId;
import com.liquidsoftware.order.domain.OrderLineIds;
import com.liquidsoftware.order.domain.OrderQuantities;
import com.liquidsoftware.order.domain.OrderQuantity;
import com.liquidsoftware.order.domain.PersonalNames;
import com.liquidsoftware.order.domain.ProductCode;
import com.liquidsoftware.order.domain.ProductCodes;
import com.liquidsoftware.order.domain.VipStatus;
import com.liquidsoftware.order.domain.VipStatuses;
import com.liquidsoftware.order.domain.internaltypes.AddressValidationErrors;
import com.liquidsoftware.order.domain.internaltypes.CheckedAddress;
import com.liquidsoftware.order.domain.internaltypes.PricingMethod;
import com.liquidsoftware.order.domain.internaltypes.PricingMethods;
import com.liquidsoftware.order.domain.internaltypes.ValidatedOrder;
import com.liquidsoftware.order.domain.internaltypes.ValidatedOrderLine;
import com.liquidsoftware.order.domain.internaltypes.ValidatedOrderLines;
import com.liquidsoftware.order.domain.internaltypes.ValidatedOrders;
import com.liquidsoftware.order.domain.publictypes.UnvalidatedAddress;
import com.liquidsoftware.order.domain.publictypes.UnvalidatedAddresses;
import com.liquidsoftware.order.domain.publictypes.UnvalidatedCustomerInfo;
import com.liquidsoftware.order.domain.publictypes.UnvalidatedCustomerInfos;
import com.liquidsoftware.order.domain.publictypes.UnvalidatedOrder;
import com.liquidsoftware.order.domain.publictypes.UnvalidatedOrderLine;
import com.liquidsoftware.order.domain.publictypes.UnvalidatedOrderLines;
import com.liquidsoftware.order.domain.publictypes.UnvalidatedOrders;
import com.liquidsoftware.order.domain.simpletypes.EmailAddress;
import com.liquidsoftware.order.domain.simpletypes.EmailAddresses;
import com.liquidsoftware.order.domain.simpletypes.String50;
import com.liquidsoftware.order.domain.simpletypes.String50s;
import com.liquidsoftware.order.domain.simpletypes.UsStateCode;
import com.liquidsoftware.order.domain.simpletypes.UsStateCodes;
import com.liquidsoftware.order.domain.simpletypes.ValidationError;
import com.liquidsoftware.order.domain.simpletypes.ZipCode;
import com.liquidsoftware.order.domain.simpletypes.ZipCodes;
import io.vavr.Function3;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;

import static com.liquidsoftware.order.domain.simpletypes.ValidationErrors.validationError;
import static java.lang.String.format;

/**
 *  Uses Functional Dependency Injection
 *
 *  Dependencies:
 *      CheckProductCodeExists
 *      CheckAddressExists (async)
 *  Input:
 *      UnvalidatedOrder
 *  Output:
 *      Either<ValidationError, ValidatedOrder>
 */
public interface ValidateOrder
    extends Function3<CheckProductCodeExists, CheckAddressExists, UnvalidatedOrder, Either<ValidationError, ValidatedOrder>> {

    ValidateOrder validateOrder =
        (checkProductCodeExists, checkAddressExists, unvalidatedOrder) -> {

            Either<ValidationError, OrderId> orderIdE =
                OrderIds.create(UnvalidatedOrders.getOrderId(unvalidatedOrder));

            Either<ValidationError, CustomerInfo> customerInfoE =
                toCustomerInfo(UnvalidatedOrders.getUnvalidatedCustomerInfo(unvalidatedOrder));

            Either<ValidationError, Address> shippingAddressE =
                toCheckedAddress(checkAddressExists, UnvalidatedOrders.getShippingAddress(unvalidatedOrder))
                    .flatMap(ValidateOrder::toAddress);

            Either<ValidationError, Address> billingAddressE =
                toCheckedAddress(checkAddressExists, UnvalidatedOrders.getBillingAddress(unvalidatedOrder))
                    .flatMap(ValidateOrder::toAddress);

            Either<ValidationError, List<ValidatedOrderLine>> linesE =
                Either.sequenceRight(UnvalidatedOrders.getLines(unvalidatedOrder)
                    .map(line -> toValidatedOrderLine(checkProductCodeExists, line)))
                .map(seq -> seq.toList());

            PricingMethod pricingMethod =
                PricingMethods.create(Option.of(UnvalidatedOrders.getPromotionCode(unvalidatedOrder)));

            return orderIdE.flatMap(orderId ->
                customerInfoE.flatMap(customerInfo ->
                shippingAddressE.flatMap(shippingAddress ->
                billingAddressE.flatMap(billingAddress ->
                linesE.map(lines ->
                    ValidatedOrders.validatedOrder(orderId, customerInfo, shippingAddress, billingAddress, lines, pricingMethod)
                )))));
        };

    private static Either<ValidationError, ValidatedOrderLine> toValidatedOrderLine(
        CheckProductCodeExists checkProductCodeExists, UnvalidatedOrderLine unvalidatedOrderLine) {

        Either<ValidationError, OrderLineId> orderLineIdE =
            OrderLineIds.create(UnvalidatedOrderLines.getOrderLineId(unvalidatedOrderLine));

        Either<ValidationError, ProductCode> productCodeE =
            ProductCodes.create(UnvalidatedOrderLines.getProductCode(unvalidatedOrderLine))
                .flatMap(pc -> toProductCode(checkProductCodeExists, pc));

        Either<ValidationError, OrderQuantity> quantityE =
            productCodeE.flatMap(pc -> OrderQuantities.create(
                pc, UnvalidatedOrderLines.getQuantity(unvalidatedOrderLine))
            );

        return orderLineIdE.flatMap(orderLineId ->
            productCodeE.flatMap(productCode ->
            quantityE.map(quantity -> ValidatedOrderLines.validatedOrderLine(
                orderLineId, productCode, quantity)
            )));
    }

    private static Either<ValidationError, ProductCode> toProductCode(
        CheckProductCodeExists checkProductCodeExists, ProductCode productCode) {

        return checkProductCodeExists.apply(productCode)
            ? Either.right(productCode)
            : Either.left(validationError(format("Invalid: %s", productCode)));
    }

    private static Either<ValidationError, Address> toAddress(CheckedAddress checkedAddress) {

        Either<ValidationError, String50> addressLine1E =
            String50s.create(UnvalidatedAddresses.getAddressLine1(checkedAddress.toUnvalidated()));

        Option<String50> addressLine2 =
            String50s.createOption(UnvalidatedAddresses.getAddressLine1(checkedAddress.toUnvalidated()));

        Option<String50> addressLine3 =
            String50s.createOption(UnvalidatedAddresses.getAddressLine1(checkedAddress.toUnvalidated()));

        Option<String50> addressLine4 =
            String50s.createOption(UnvalidatedAddresses.getAddressLine1(checkedAddress.toUnvalidated()));

        Either<ValidationError, String50> cityE =
            String50s.create(UnvalidatedAddresses.getCity(checkedAddress.toUnvalidated()));

        Either<ValidationError, UsStateCode> stateE =
            UsStateCodes.create(UnvalidatedAddresses.getState(checkedAddress.toUnvalidated()));

        Either<ValidationError, ZipCode> zipCodeE =
            ZipCodes.create(UnvalidatedAddresses.getZipCode(checkedAddress.toUnvalidated()));

        Either<ValidationError, String50> countryE =
            String50s.create(UnvalidatedAddresses.getCountry(checkedAddress.toUnvalidated()));

        return addressLine1E.flatMap(line1 ->
            cityE.flatMap(city ->
            stateE.flatMap(state ->
            zipCodeE.flatMap(zip ->
            countryE.map(country ->
                Addresses.Address(
                    line1, addressLine2, addressLine3, addressLine4, city, zip, state, country
                )
            )))));
    }

    private static Either<ValidationError, CheckedAddress> toCheckedAddress(
        CheckAddressExists checkAddressExists, UnvalidatedAddress unvalidatedAddress) {

        return checkAddressExists.apply(unvalidatedAddress)
            .mapLeft(ave ->
                AddressValidationErrors.caseOf(ave)
                    .invalidFormat(() -> validationError("Address has bad format"))
                    .addressNotFound(() -> validationError("Address not found"))
            );
    }

    private static Either<ValidationError, CustomerInfo> toCustomerInfo(UnvalidatedCustomerInfo uci) {

        Either<ValidationError, String50> firstNameE =
            String50s.create(UnvalidatedCustomerInfos.getFirstName(uci));

        Either<ValidationError, String50> lastNameE =
            String50s.create(UnvalidatedCustomerInfos.getLastName(uci));

        Either<ValidationError, EmailAddress> emailE =
            EmailAddresses.create(UnvalidatedCustomerInfos.getEmailAddress(uci));

        Either<ValidationError, VipStatus> vipStatusE =
            VipStatuses.fromString(UnvalidatedCustomerInfos.getVipStatus(uci));

        return firstNameE.flatMap(firstName ->
            lastNameE.flatMap(lastName ->
            emailE.flatMap(email ->
            vipStatusE.map(vipStatus ->
                CustomerInfos.CustomerInfo(
                    PersonalNames.PersonalName(firstName, lastName),
                    email,
                    vipStatus
                )
            ))));
    }
}
