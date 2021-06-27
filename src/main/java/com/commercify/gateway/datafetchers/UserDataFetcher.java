package com.commercify.gateway.datafetchers;

import com.commercify.gateway.types.User;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
@Slf4j
public class UserDataFetcher {

    private final List<User> usersList = List.of(
                new User("1", "John", "john@gmail.com", ""),
                new User("2", "Kumar", "kumar@gmail.com", ""),
                new User("3", "Patel", "patel@gmail.com", "")
            );

    @DgsQuery(field = "users")
    public List<User> shows(@InputArgument String id) {
        log.info("request reached here "+ id);
        if(id == null) {
            return usersList;
        }

        List<User> finalValue = usersList.stream().filter(u -> u.getId().equals(id)).collect(Collectors.toList());
        log.info(finalValue.toString());
        return finalValue;
    }
}
