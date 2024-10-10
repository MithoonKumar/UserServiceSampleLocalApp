package org.example.validator;

import org.example.exception.InvalidRequestPayloadException;
import org.example.model.User;

import java.util.Objects;

public class RequestValidator {

    public static void  validateUpdateUserRequest(String userId, User user) {
        if (!Objects.equals(userId, user.getUserId())) {
            throw new InvalidRequestPayloadException("UserIds are not matching");
        }
    }
}
