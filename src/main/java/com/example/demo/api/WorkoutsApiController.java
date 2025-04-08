package com.example.demo.api;

import com.example.demo.model.Workout;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-08T12:59:20.852239Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.healthAndFitness.base-path:/api/v1}")
public class WorkoutsApiController implements WorkoutsApi {

    private final WorkoutsApiDelegate delegate;

    public WorkoutsApiController(@Autowired(required = false) WorkoutsApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new WorkoutsApiDelegate() {});
    }

    @Override
    public WorkoutsApiDelegate getDelegate() {
        return delegate;
    }

}
