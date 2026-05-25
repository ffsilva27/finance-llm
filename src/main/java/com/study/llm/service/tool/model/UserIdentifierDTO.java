package com.study.llm.service.tool.model;

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

    private Long userId;
    private String name;
    private Long serialNumberIdentifier;

}
