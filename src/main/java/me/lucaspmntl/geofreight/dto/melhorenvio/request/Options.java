package me.lucaspmntl.geofreight.dto.melhorenvio.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Options(
        boolean receipt,

        @JsonProperty("own_hand")
        boolean ownHand
) {
}
