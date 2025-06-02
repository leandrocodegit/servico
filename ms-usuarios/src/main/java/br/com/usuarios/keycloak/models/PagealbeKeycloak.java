package br.com.usuarios.keycloak.models;

import org.springframework.data.domain.*;
import org.springframework.util.Assert;

import java.util.Optional;

public interface PagealbeKeycloak {


    default boolean isPaged() {
        return true;
    }

    default boolean isUnpaged() {
        return !this.isPaged();
    }

    int getPageNumber();

    int getPageSize();

    long getOffset();

    Sort getSort();

    default Sort getSortOr(Sort sort) {
        Assert.notNull(sort, "Fallback Sort must not be null");
        return this.getSort().isSorted() ? this.getSort() : sort;
    }

    PagealbeKeycloak next();

    PagealbeKeycloak previousOrFirst();

    PagealbeKeycloak first();

    PagealbeKeycloak withPage(int pageNumber);

    boolean hasPrevious();

    default Optional<PagealbeKeycloak> toOptional() {
        return this.isUnpaged() ? Optional.empty() : Optional.of(this);
    }

    default Limit toLimit() {
        return this.isUnpaged() ? Limit.unlimited() : Limit.of(this.getPageSize());
    }

    default OffsetScrollPosition toScrollPosition() {
        if (this.isUnpaged()) {
            throw new IllegalStateException("Cannot create OffsetScrollPosition from an unpaged instance");
        } else {
            return ScrollPosition.offset(this.getOffset());
        }
    }
}
