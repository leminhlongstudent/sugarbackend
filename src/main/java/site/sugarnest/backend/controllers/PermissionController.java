package site.sugarnest.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.sugarnest.backend.dto.dto.ApiResponse;
import site.sugarnest.backend.dto.request.PermissionRequest;
import site.sugarnest.backend.dto.response.PermissionResponse;
import site.sugarnest.backend.service.Athorization.PermissionService;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@AllArgsConstructor
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .message("Permission created!")
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAllPermission() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .message("All permissions!")
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{name}")
    ApiResponse<Void> deletePermission(@PathVariable String name) {
        permissionService.delete(name);
        return ApiResponse.<Void>builder()
                .message("Permission deleted!")
                .build();
    }

}
