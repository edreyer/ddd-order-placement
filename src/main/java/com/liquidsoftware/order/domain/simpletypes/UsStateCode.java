package com.liquidsoftware.order.domain.simpletypes;

import io.vavr.control.Either;
import org.derive4j.Data;
import org.derive4j.Derive;
import org.derive4j.ExportAsPublic;
import org.derive4j.FieldNames;
import org.derive4j.Visibility;

import java.util.function.Function;
import java.util.regex.Pattern;

@Data(@Derive(withVisibility = Visibility.Smart))
public abstract class UsStateCode {

    UsStateCode() {}

    public abstract <R> R match(@FieldNames("stateCode") Function<String, R> stateCode);

    @ExportAsPublic
    static Either<ValidationError, UsStateCode> create(String stateCode) {
        String statesRegex = "^(A[KLRZ]|C[AOT]|D[CE]|FL|GA|HI|I[ADLN]|K[SY]|LA|M[ADEINOST]|N[CDEHJMVY]|O[HKR]|P[AR]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY])$";
        Pattern statesPattern = Pattern.compile(statesRegex);
        return ConstrainedTypes.createLike("stateCode", stateCode, statesPattern)
            .map(validStateCode -> UsStateCodes.stateCode0(validStateCode));
    }

}
