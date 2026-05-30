package com.study.llm.domain.tool.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserIdentifierDTO {

    private UUID userId;
    private String name;
    private String serialNumberIdentifier;

}
