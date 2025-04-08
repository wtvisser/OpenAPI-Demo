package com.example.demo.api;

import com.example.demo.model.Workout;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link WorkoutsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-08T12:59:20.852239Z[Etc/UTC]")
public interface WorkoutsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /workouts : Get all workouts
     *
     * @return A list of workouts (status code 200)
     * @see WorkoutsApi#workoutsGet
     */
    default ResponseEntity<List<Workout>> workoutsGet() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"duration\" : 30, \"date\" : \"2023-10-01T00:00:00.000+00:00\", \"caloriesBurned\" : 300, \"id\" : \"123\", \"type\" : \"Running\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /workouts/{id} : Get a specific workout by ID
     *
     * @param id ID of the workout (required)
     * @return A single workout (status code 200)
     *         or Workout not found (status code 404)
     * @see WorkoutsApi#workoutsIdGet
     */
    default ResponseEntity<Workout> workoutsIdGet(String id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"duration\" : 30, \"date\" : \"2023-10-01T00:00:00.000+00:00\", \"caloriesBurned\" : 300, \"id\" : \"123\", \"type\" : \"Running\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /workouts : Create a new workout
     *
     * @param workout  (required)
     * @return Workout created successfully (status code 201)
     * @see WorkoutsApi#workoutsPost
     */
    default ResponseEntity<Workout> workoutsPost(Workout workout) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"duration\" : 30, \"date\" : \"2023-10-01T00:00:00.000+00:00\", \"caloriesBurned\" : 300, \"id\" : \"123\", \"type\" : \"Running\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
