package com.REST.example.dto;

import com.REST.example.validation.OnCreate;
import com.REST.example.validation.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PollRequest {
    @NotNull(message = "Question must be not empty", groups = {OnCreate.class, OnUpdate.class})
    @Schema(
            description = "Question text",
            example = "Choose programming language"
    )
    private String question;

    @Size(min = 2, max = 10,
            message = "It must be at least 2 options. And less than(or equal) 10",
            groups = {OnCreate.class, OnUpdate.class})
    @Schema(
            description = "available option list",
            example = "[\"Java\", \"Python\", \"JavaScript\"]"
    )
    private List<OptionRequest> options;
}
