package com.usmobile.userservice.userservice.constants;

public interface UserServiceConstants {

    interface IdPrefixes {
        String USER_ID_PREFIX = "US-";
    }

    interface ModifyingType {
        String UPDATE = "updating";
        String CREATE = "creating";
        String ADD = "adding";
        String UPDATED = "updated";
        String CREATED = "created";
    }

    interface EntityType {
        String USER = "user";
        String MDN = "mdn";
    }
}
